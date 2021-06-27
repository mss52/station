package com.base.service.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.base.base.Failure;
import com.base.base.Return;
import com.base.base.Success;
import com.base.bean.SessionBean;
import com.base.lib.db.dao.StationDao;
import com.base.lib.db.model.auth.ModelLocation;
import com.base.service.admin.requests.RequestLocation;

public class LocationService {
	@Autowired
	private StationDao stationDao;

	@Autowired
	private SessionBean session;

	@Transactional
	public Return<ModelLocation> put(RequestLocation request) {
		if(request==null) {
			return new Failure<>("Invalid Location");
		}
		if (request.getDescription() == null) {
			return new Failure<>("Invalid Location Description");
		}
		
		ModelLocation u = new ModelLocation();
		u.setDescription(request.getDescription());
		u.setLatitude(u.getLatitude());
		u.setLongitude(u.getLongitude());
		u.setCreatedAt(new Date());
		stationDao.save(u);
		return new Success<ModelLocation>(u);
	}

}
