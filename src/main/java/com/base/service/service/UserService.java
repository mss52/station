package com.base.service.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.base.base.Constants;
import com.base.base.Failure;
import com.base.base.Return;
import com.base.base.Success;
import com.base.bean.InsightsBean;
import com.base.bean.SessionBean;
import com.base.lib.db.dao.AuthDao;
import com.base.lib.db.dao.UserDao;
import com.base.lib.db.model.ModelStatus;
import com.base.lib.db.model.auth.ModelStation;
import com.base.lib.db.model.auth.ModelUser;
import com.base.service.admin.requests.RequestSignUp;
import com.base.service.admin.requests.RequestUpdateUser;
import com.base.service.response.ResponseInsights;
import com.base.utils.DateUtils;
import com.base.utils.SaltedMD5;

public class UserService {
	@Autowired
	private AuthDao authDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SessionBean session;

	@Transactional
	public Return<ModelUser> signUp(RequestSignUp request) {
		if (request.getName() == null) {
			return new Failure<>("invalid.user.name");
		}
		if (request.getPhone() == null) {
			return new Failure<>("invalid.phone.number");
		}
		if (request.getUsername() == null) {
			return new Failure<>("invalid.username");
		}
		if (request.getPassword() == null) {
			return new Failure<>("invalid.password");
		}
		if (request.getStation() == null || request.getStation().getId() == null) {
			return new Failure<>("invalid.station");
		}
		ModelUser user = authDao.getUserByUserName(request.getUsername().toLowerCase());
		if (user != null) {
			return new Failure<>("username.already.exists");
		}
		ModelStation s = authDao.getValueById(ModelStation.class, request.getStation().getId());
		if (s == null) {
			return new Failure<>("invalid.station");
		}
		ModelUser u = new ModelUser();
		u.setName(request.getName());
		u.setPassword(SaltedMD5.getSecurePassword(request.getPassword(), null));
		u.setStation(s);
		u.setStatus(new ModelStatus(Constants.PENDING_APPROVAL));
		u.setUserType(request.getType());
		u.setUsername(request.getUsername().toLowerCase());
		u.setPhone(request.getPhone());
		authDao.save(u);
		return new Success<ModelUser>(u);
	}

	@Transactional
	public Return<ModelUser> update(RequestUpdateUser request) {
		if (request.getName() == null) {
			return new Failure<>("invalid.user.name");
		}
		if (request.getPhone() == null) {
			return new Failure<>("invalid.phone.number");
		}
		if (request.getPassword() == null) {
			return new Failure<>("invalid.password");
		}
		if (request.getStation() == null || request.getStation().getId() == null) {
			return new Failure<>("invalid.station");
		}
		ModelUser user = session.getUser();
		
		ModelStation s = authDao.getValueById(ModelStation.class, request.getStation().getId());
		if (s == null) {
			return new Failure<>("invalid.station");
		}
		user.setName(request.getName());
		user.setPhone(request.getPhone());
		user.setPassword(SaltedMD5.getSecurePassword(request.getPassword(), null));
		user.setStation(s);
		authDao.update(user);
		return new Success<ModelUser>(user);
	}
	
	@Transactional
	public Return checkUserName(String username) {
		ModelUser user=null;
		if(username!=null) {
			user = authDao.getUserByUserName(username.toLowerCase());
		}
		if (user != null) {
			return new Failure<>("username.already.exists");
		}
		return new Success();
	}
	
	@Transactional
	public Return<ResponseInsights> insights(long userId,Date fromDate,Date toDate) {
		List<InsightsBean> result = userDao.getInsights(userId,DateUtils.clearDate(fromDate),
				DateUtils.midnigth(toDate));
		ResponseInsights response=new ResponseInsights();
		response.setList(result);;
		return new Success<ResponseInsights>(response);
	}
}
