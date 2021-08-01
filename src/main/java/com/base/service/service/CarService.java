package com.base.service.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.base.base.Failure;
import com.base.base.Return;
import com.base.base.Success;
import com.base.bean.SessionBean;
import com.base.lib.db.dao.CarDao;
import com.base.lib.db.dao.ConfigDao;
import com.base.lib.db.model.ModelCar;
import com.base.lib.db.model.ModelCarCode;
import com.base.lib.db.model.ModelCarFillingHistory;
import com.base.lib.db.model.ModelConfig;
import com.base.service.admin.requests.RequestCar;
import com.base.service.admin.requests.RequestFillCar;
import com.base.service.response.ResponseCarLastFilling;
import com.base.utils.DateUtils;

public class CarService {
	@Autowired
	private CarDao carDao;

	@Autowired
	private ConfigDao configDao;

	@Autowired
	private SessionBean session;

	@Transactional
	public Return<ModelCar> getCar(String plateNumber, String plateCode) {
		ModelCar car = carDao.getCarByPlateNumberAndPlateCode(plateNumber, plateCode, false);
		if (car == null) {
			return new Failure<>("car.not.found");
		}
		return new Success<ModelCar>(car);
	}

	@Transactional
	public Return<ResponseCarLastFilling> getCarLastFill(String plateNumber, String plateCode) {
		ModelCar car = carDao.getCarByPlateNumberAndPlateCode(plateNumber, plateCode, false);
		if (car == null) {
			return new Failure<>("car.not.found");
		}
		ResponseCarLastFilling response = new ResponseCarLastFilling();
		response.setCarOwnerName(car.getCarOwnerName());
		response.setAllowedAfterDayCount(car.getAllowedAfterDayCount());
		response.setLast(car.getLastFilled());
		Calendar c = Calendar.getInstance();
		if (car.getLastFilled() != null && car.getAllowedAfterDayCount() != null) {
			c.setTime(car.getLastFilled().getDate());
			c.add(Calendar.DAY_OF_MONTH, car.getAllowedAfterDayCount().intValue());
		}
		response.setNextFillDate(DateUtils.midnigth(c.getTime()));
		response.setCanFill(response.getNextFillDate().compareTo(DateUtils.clearDate(new Date())) <= 0);
		return new Success<ResponseCarLastFilling>(response);
	}

	@Transactional
	public Return<List<ModelCarCode>> getCarCode() {
		return new Success<List<ModelCarCode>>(carDao.getCarCodes());
	}

	@Transactional
	public Return<ModelCar> addCar(RequestCar carInfo) {
		if (carInfo.getPlateNumber() == null || carInfo.getPlateNumber().length() < 2) {
			return new Failure<ModelCar>("invalid.plate.number");
		}
		if (carInfo.getPlateCode() == null || carInfo.getPlateCode().length() != 1) {
			return new Failure<ModelCar>("invalid.plate.code");
		}
		ModelCarCode c = carDao.getCarCodeByCode(carInfo.getPlateCode());
		if (c == null) {
			return new Failure<ModelCar>("invalid.plate.code");
		}
		ModelCar car = carDao.getCarByPlateNumberAndPlateCode(carInfo.getPlateNumber(), carInfo.getPlateCode(), true);
		if (car != null) {
			return new Failure<ModelCar>("car.already.exsts");
		}
		car = new ModelCar();
		car.setPlateCode(carInfo.getPlateCode());
		car.setPlateNumber(carInfo.getPlateNumber());
		car.setAddedByUser(session.getUser().getId());
		car.setCarOwnerName(carInfo.getCarOwnerName());
		car.setAllowedAfterDayCount(carInfo.getAllowedAfterDayCount() == null ? c.getAllowedAfterDayCount()
				: carInfo.getAllowedAfterDayCount());
		car.setNote(carInfo.getNote());
		carDao.saveOrUpdate(car);
		return new Success<>(car);
	}

	@Transactional
	public Return<ModelCar> update(RequestCar carInfo) {
		if (carInfo.getPlateNumber() == null || carInfo.getPlateNumber().length() < 2) {
			return new Failure<ModelCar>("invalid.plate.number");
		}
		if (carInfo.getPlateCode() == null || carInfo.getPlateCode().length() != 1) {
			return new Failure<ModelCar>("invalid.plate.code");
		}
		ModelCarCode c = carDao.getCarCodeByCode(carInfo.getPlateCode());
		if (c == null) {
			return new Failure<ModelCar>("invalid.plate.code");
		}
		ModelCar car = carDao.getCarByPlateNumberAndPlateCode(carInfo.getPlateNumber(), carInfo.getPlateCode(), true);
		if (car == null) {
			car = new ModelCar();
			car.setPlateCode(carInfo.getPlateCode());
			car.setPlateNumber(carInfo.getPlateNumber());
			car.setAddedByUser(session.getUser().getId());
		}
		car.setCarOwnerName(carInfo.getCarOwnerName() == null ? car.getCarOwnerName() : carInfo.getCarOwnerName());
		car.setNote(carInfo.getNote() == null ? car.getNote() : carInfo.getNote());
		car.setAllowedAfterDayCount(carInfo.getAllowedAfterDayCount() == null ? c.getAllowedAfterDayCount()
				: carInfo.getAllowedAfterDayCount());
		carDao.saveOrUpdate(car);
		return new Success<>(car);
	}

	@Transactional
	public Return<ModelCar> fillCar(RequestFillCar carInfo) {
		if (carInfo.getAmount() == null || carInfo.getAmount() < 0) {
			return new Failure<>("invalid.filling.amount");
		}
		ModelCar car = null;
		if (carInfo.getCar() == null) {
			return new Failure<ModelCar>("invalid.car");
		}
		if (carInfo.getCar().getId() != null) {
			car = carDao.getValueByIdForUpdate(ModelCar.class, carInfo.getCar().getId());
			if (car == null) {
				return new Failure<ModelCar>("invalid.car.id");
			}
		}
		if (car == null) {
			Return<ModelCar> addCar = update(carInfo.getCar());
			if (!addCar.isSuccess()) {
				return new Failure<ModelCar>(addCar.getCode());
			}
			car = addCar.getData();
		}

		if (car.getLastFilled() != null && car.getLastFilled().getDate() != null) {
			Calendar c = Calendar.getInstance();
			Date now = DateUtils.midnigth(c.getTime());
			c.setTime(DateUtils.midnigth(car.getLastFilled().getDate()));
			int nextDay = car.getAllowedAfterDayCount() == null ? 3 : car.getAllowedAfterDayCount().intValue();
			if (car.getAllowedAfterDayCount() == null) {
				Integer conf = getIntegerConf("key");
				if (conf != null) {
					nextDay = conf;
				}
				c.add(Calendar.DAY_OF_MONTH, nextDay);
				Date lastDate = c.getTime();
				if (lastDate.after(now)) {
					return new Failure<>("filling.not.allowed");
				}
			}
		}
		ModelCarFillingHistory fill = new ModelCarFillingHistory();
		fill.setAmount(carInfo.getAmount());
		fill.setCarId(car.getId());
		fill.setDate(new Date());
		fill.setFillingUser(session.getUser());
		fill.setNote(carInfo.getNote());
		carDao.save(fill);
		car.setLastFilled(fill);
		carDao.saveOrUpdate(car);
		return new Success<>(car);
	}

	@Transactional
	private Integer getIntegerConf(String key) {
		try {
			ModelConfig conf = configDao.getConfigByKey(key);
			return Integer.parseInt(conf.getValue());
		} catch (Exception e) {
		}
		return null;
	}

}
