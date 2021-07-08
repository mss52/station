package com.base.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.base.base.Constants;
import com.base.base.Failure;
import com.base.base.Return;
import com.base.base.Success;
import com.base.bean.SessionBean;
import com.base.lib.db.dao.AuthDao;
import com.base.lib.db.model.ModelStatus;
import com.base.lib.db.model.auth.ModelStation;
import com.base.lib.db.model.auth.ModelUser;
import com.base.service.admin.requests.RequestSignUp;
import com.base.service.admin.requests.RequestUpdateUser;
import com.base.utils.SaltedMD5;

public class UserService {
	@Autowired
	private AuthDao authDao;

	@Autowired
	private SessionBean session;

	@Transactional
	public Return<ModelUser> signUp(RequestSignUp request) {
		if (request.getName() == null) {
			return new Failure<>("Invalid Name");
		}
		if (request.getPhone() == null) {
			return new Failure<>("Invalid Phone Number");
		}
		if (request.getUsername() == null) {
			return new Failure<>("Invalid Username");
		}
		if (request.getPassword() == null) {
			return new Failure<>("Invalid Password");
		}
		if (request.getStation() == null || request.getStation().getId() == null) {
			return new Failure<>("Invalid Station");
		}
		ModelUser user = authDao.getUserByUserName(request.getUsername());
		if (user != null) {
			return new Failure<>("Username Alredy exists");
		}
		ModelStation s = authDao.getValueById(ModelStation.class, request.getStation().getId());
		if (s == null) {
			return new Failure<>("Station Not Found");
		}
		ModelUser u = new ModelUser();
		u.setName(request.getName());
		u.setPassword(SaltedMD5.getSecurePassword(request.getPassword(), null));
		u.setStation(s);
		u.setStatus(new ModelStatus(Constants.PENDING_APPROVAL));
		u.setUserType(request.getType());
		u.setUsername(request.getUsername());
		u.setPhone(request.getPhone());
		authDao.save(u);
		return new Success<ModelUser>(u);
	}

	@Transactional
	public Return<ModelUser> update(RequestUpdateUser request) {
		if (request.getName() == null) {
			return new Failure<>("Invalid Name");
		}
		if (request.getPhone() == null) {
			return new Failure<>("Invalid Phone Number");
		}
		if (request.getPassword() == null) {
			return new Failure<>("Invalid Password");
		}
		if (request.getStation() == null || request.getStation().getId() == null) {
			return new Failure<>("Invalid Station");
		}
		ModelUser user = session.getUser();
		
		ModelStation s = authDao.getValueById(ModelStation.class, request.getStation().getId());
		if (s == null) {
			return new Failure<>("Station Not Found");
		}
		user.setName(request.getName());
		user.setPhone(request.getPhone());
		user.setPassword(SaltedMD5.getSecurePassword(request.getPassword(), null));
		user.setStation(s);
		authDao.update(user);
		return new Success<ModelUser>(user);
	}
	
}
