package com.base.service.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.base.base.Constants;
import com.base.base.Failure;
import com.base.base.Return;
import com.base.base.Success;
import com.base.lib.db.dao.StationDao;
import com.base.lib.db.model.ModelStatus;
import com.base.lib.db.model.auth.ModelLocation;
import com.base.lib.db.model.auth.ModelStation;
import com.base.service.admin.requests.RequestStation;

public class StationService {
	@Autowired
	private StationDao stationDao;

//	@Autowired
//	private SessionBean session;

	@Autowired
	private LocationService locationService;

	@Transactional
	public Return<List<ModelStation>> get(String searchName,Long statusId) {
		return new Success<>(stationDao.getStations(searchName,statusId));
	}

	@Transactional
	public Return<ModelStation> get(long id) {
		return new Success<>(stationDao.getValueById(ModelStation.class, id));
	}

	
	@Transactional
	public Return<ModelStation> put(RequestStation request) {
		if (request.getName() == null) {
			return new Failure<>("Invalid Name");
		}
		ModelStation station = stationDao.getStationByName(request.getName());
		if (station != null) {
			return new Failure<>("Station Already Exists");
		}
		if (request.getPhone() == null) {
			return new Failure<>("Invalid Phone Number");
		}

		Return<ModelLocation> location = locationService.put(request.getLocation());
		if (!location.isSuccess()) {
			return new Failure<>(location.getCode());
		}

		ModelStation u = new ModelStation();
		u.setStatus(new ModelStatus(Constants.PENDING_APPROVAL));
		u.setCreatedAt(new Date());
		u.setLocation(location.getData());
		u.setName(request.getName());
		u.setPhone(request.getPhone());
		u.setClosed(request.getClosed()==null?true:request.getClosed());
		stationDao.save(u);
		return new Success<ModelStation>(u);
	}

	@Transactional
	public Return<ModelStation> update(RequestStation request) {
		if (request == null) {
			return new Failure<>("Invalid Station");
		}
		ModelStation station = stationDao.getValueById(ModelStation.class,
				request.getId() == null ? -1 : request.getId());
		if (station == null) {
			return new Failure<>("Station Not Exists");
		}
		if (request.getLocation() != null) {
			Return<ModelLocation> location = locationService.put(request.getLocation());
			if (!location.isSuccess()) {
				return new Failure<>(location.getCode());
			}
			station.setLocation(location.getData());
		}
		if (request.getName() != null && !StringUtils.isEmpty(request.getName())) {
			station.setName(request.getName());
		}
		if (request.getPhone() == null && !StringUtils.isEmpty(request.getPhone())) {
			station.setPhone(request.getPhone());
		}
		station.setClosed(request.getClosed()==null?station.isClosed():request.getClosed());
		stationDao.update(station);
		return new Success<ModelStation>(station);
	}
}
