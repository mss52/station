package com.base.service.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.base.base.Failure;
import com.base.base.Return;
import com.base.base.Success;
import com.base.bean.SessionBean;
import com.base.lib.db.dao.CarDao;
import com.base.lib.db.dao.ConfigDao;
import com.base.lib.db.model.ModelCar;
import com.base.lib.db.model.ModelCarFillingHistory;
import com.base.lib.db.model.ModelConfig;
import com.base.service.admin.requests.RequestCar;
import com.base.service.admin.requests.RequestFillCar;

public class CarService {
	@Autowired
	private CarDao carDao;

	@Autowired
	private ConfigDao configDao;
	
	@Autowired
	private SessionBean session;

	@Transactional
	public Return<ModelCar> getCar(String plateNumber, String plateCode) {
		ModelCar car = carDao.getCarByPlateNumberAndPlateCode(plateNumber, plateCode,false);
		if (car == null) {
			return new Failure<>("car.not.found");
		}
		return new Success<ModelCar>(car);
	}

	@Transactional
	public Return<ModelCar> addCar(RequestCar carInfo) {
		if(carInfo.getPlateNumber()==null || carInfo.getPlateNumber().length()<4) {
			return new Failure<ModelCar>("Invalid Plate Number");
		}
		if(carInfo.getPlateCode()==null || carInfo.getPlateCode().length()!=1) {
			return new Failure<ModelCar>("Invalid Plate Code");
		}
		ModelCar car = carDao.getCarByPlateNumberAndPlateCode(carInfo.getPlateNumber(), carInfo.getPlateCode(),true);
		if(car!=null) {
			return new Failure<ModelCar>("Car Already Exists");
		}
		car=new ModelCar();
		car.setPlateCode(carInfo.getPlateCode());
		car.setPlateNumber(carInfo.getPlateNumber());
		car.setAddedByUser(session.getUser().getId());
		car.setCarOwnerName(carInfo.getCarOwnerName());
		car.setAllowedAfterDayCount(carInfo.getAllowedAfterDayCount());
		car.setNote(carInfo.getNote());
		carDao.saveOrUpdate(car);
		return new Success<>(car);
	}

	@Transactional
	public Return<ModelCar> update(RequestCar carInfo) {
		if(carInfo.getPlateNumber()==null || carInfo.getPlateNumber().length()<4) {
			return new Failure<ModelCar>("Invalid Plate Number");
		}
		if(carInfo.getPlateCode()==null || carInfo.getPlateCode().length()!=1) {
			return new Failure<ModelCar>("Invalid Plate Code");
		}
		ModelCar car = carDao.getCarByPlateNumberAndPlateCode(carInfo.getPlateNumber(), carInfo.getPlateCode(),true);
		if(car==null) {
			car=new ModelCar();
			car.setPlateCode(carInfo.getPlateCode());
			car.setPlateNumber(carInfo.getPlateNumber());
			car.setAddedByUser(session.getUser().getId());
		}
		car.setCarOwnerName(carInfo.getCarOwnerName()==null?car.getCarOwnerName():carInfo.getCarOwnerName());
		car.setNote(carInfo.getNote()==null?car.getNote():carInfo.getNote());
		car.setAllowedAfterDayCount(carInfo.getAllowedAfterDayCount());
		carDao.saveOrUpdate(car);
		return new Success<>(car);
	}
	
	@Transactional
	public Return<ModelCar> fillCar(RequestFillCar carInfo) {
		if(carInfo.getAmount()==null || carInfo.getAmount()<0) {
			return new Failure<>("Invalid Filling Amount");
		}
		ModelCar car=null;
		if(carInfo.getCar()==null) {
			return new Failure<ModelCar>("Invalid Car");
		}
		if(carInfo.getCar().getId()!=null) {
			car=carDao.getValueByIdForUpdate(ModelCar.class, carInfo.getCar().getId());
			if(car==null) {
				return new Failure<ModelCar>("Invalid Car Id");
			}
		}
		if(car==null) {
			Return<ModelCar> addCar = update(carInfo.getCar());
			if(!addCar.isSuccess()) {
				return new Failure<ModelCar>(addCar.getCode());
			}
			car=addCar.getData();
		}
		
		if(car.getLastFilled()!=null && car.getLastFilled().getDate()!=null) {
			Calendar c=Calendar.getInstance();
			clearTime(c);
			Date now = c.getTime();
			c.setTime(car.getLastFilled().getDate());
			clearTime(c);
			int nextDay=car.getAllowedAfterDayCount()==null?3:car.getAllowedAfterDayCount().intValue();
			if(car.getAllowedAfterDayCount()==null) {
				Integer conf=getIntegerConf("key");
				if(conf!=null) {
					nextDay=conf;
				}
			}
			c.add(Calendar.DAY_OF_MONTH, nextDay);
			Date lastDate=c.getTime();
			if(lastDate.after(now)) {
				return new Failure<>("Car Not Allowed For Filling");
			}
		}
		ModelCarFillingHistory fill=new ModelCarFillingHistory();
		fill.setAmount(carInfo.getAmount());
		fill.setCarId(car.getId());
		fill.setDate(new Date());
		fill.setFillingUserId(session.getUser().getId());
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
		}catch (Exception e) {
		}
		return null;
	}
	private void clearTime(Calendar c) {
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
	}
}
