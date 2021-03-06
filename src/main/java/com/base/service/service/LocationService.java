package com.base.service.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.base.base.Failure;
import com.base.base.Return;
import com.base.base.Success;
import com.base.lib.db.dao.StationDao;
import com.base.lib.db.model.auth.ModelLocation;
import com.base.service.admin.requests.RequestLocation;

public class LocationService {
	@Autowired
	private StationDao stationDao;

//	@Autowired
//	private SessionBean session;

	@Transactional
	public Return<ModelLocation> put(RequestLocation request) {
		if(request==null) {
			return new Failure<>("invalid.location");
		}
		if (request.getDescription() == null) {
			return new Failure<>("invalid.location.description");
		}
		
		ModelLocation u = new ModelLocation();
		u.setDescription(request.getDescription());
		u.setLatitude(request.getLatitude());
		u.setLongitude(request.getLongitude());
		u.setCreatedAt(new Date());
		stationDao.save(u);
		return new Success<ModelLocation>(u);
	}

}
