package com.base.service.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.base.base.Constants;
import com.base.base.Failure;
import com.base.base.Return;
import com.base.base.Success;
import com.base.bean.SessionBean;
import com.base.lib.db.dao.AuthDao;
import com.base.lib.db.model.auth.ModelDevice;
import com.base.lib.db.model.auth.ModelLoginVerification;
import com.base.lib.db.model.auth.ModelSession;
import com.base.lib.db.model.auth.ModelUser;
import com.base.service.response.ResponseLogin;
import com.base.service.response.ResponseSession;
import com.base.utils.NumberUtils;
import com.base.utils.SaltedMD5;

public class AuthenticationService {
	@Autowired
	private AuthDao authDao;
	
	@Autowired
	private SessionBean session;
	
	@Transactional
	public Return<ResponseLogin> login(String username, String password, Long verificationId, String code) {
		if(StringUtils.isEmpty(code)) {
			return loginFirstStep(username,password);
		}else {
			return loginVerification(username,password,verificationId,code);
		}
	}

	private Return<ResponseLogin> loginVerification(String username, String password, Long verificationId,
			String code) {
		ModelUser user =null;
		if(verificationId==null){
			if(StringUtils.isEmpty(password)) {
				return new Failure<>("auth.password.empty");
			}
//			if(!Objects.equals(password1, password2)) {
//				return new Failure<>("auth.password.miss_match");
//			}
			user = authDao.getUserByUserName(username.toLowerCase());
			if(user==null) {
				return new Failure<>("auth.username_not_found");
			}
			password=SaltedMD5.getSecurePassword(password, null);
			if(!Objects.equals(password, user.getPassword())) {
				return new Failure<>("auth.wrong_password");
			}
		}
		List<ModelLoginVerification> verification = authDao.getVerification(verificationId,username,code);
		boolean verified = false;
		String error="auth.verification_failed";
		for(ModelLoginVerification v:verification) {
			if(Objects.equals(v.getCode(), code) && v.getExpireAt().after(new Date())) {
				verified=true;
				user = authDao.getUserByUserName(v.getUsername().toLowerCase());
				break;
			}else if(Objects.equals(v.getCode(), code)){
				error="auth.verification_code_expired";
			}
		}
		if(!verified) {
			return new Failure<>(error);
		}
		ModelDevice device = null;
		return createSession(user,device);
	}

	private Return<ResponseLogin> loginFirstStep(String username, String password) {
		if(StringUtils.isEmpty(password)) {
			return new Failure<>("auth.not_valid_login");
		}
		if(StringUtils.isEmpty(username)) {
			return new Failure<>("auth.not_valid_login");
		}
//		if(!Objects.equals(password1, password2)) {
//			return new Failure<>("auth.password.miss_match");
//		}
		ModelUser user = authDao.getUserByUserName(username.toLowerCase());
		if(user==null) {
			return new Failure<>("auth.not_valid_login");
		}
		password=SaltedMD5.getSecurePassword(password, null);
		if(!Objects.equals(password, user.getPassword())) {
			return new Failure<>("auth.not_valid_login");
		}
		if(user.getStatus()==null ||user.getStatus().getId()!=Constants.ACTIVE) {
			return new Failure<>("auth.user_not_active");
		}
//		if(false){
//			ModelLoginVerification verification=new ModelLoginVerification();
//			verification.setUsername(username);
//			verification.setCode(String.valueOf(CodeUtils.generateCode(6)));
//			Calendar c=Calendar.getInstance();
//			verification.setCreatedAt(c.getTime());
//			c.add(Calendar.HOUR, 1);
//			verification.setExpireAt(c.getTime());
//			authDao.save(verification);
//			return new Success<ResponseLogin>(new ResponseLogin(verification.getId()));
//		}
		ModelDevice device = session.getDevice();
		return createSession(user,device);
	}

	private Return<ResponseLogin> createSession(ModelUser user,ModelDevice device) {
		ModelSession session=new ModelSession();
		session.setDateCreation(new Date());
		session.setDevice(device);
		session.setUser(user);
		authDao.save(session);
		return new Success<>(new ResponseLogin(ResponseSession.fromModel(session)));
	}

    private ReentrantLock mutex = new ReentrantLock();

	@Transactional
	public Return<ModelDevice> createDevice(String deviceId, String deviceUid, String deviceModel, String deviceBrand, String deviceOsVersion,
			String deviceOperatingSystem, String deviceOperatingName,String appVersion,String language) {
		Long id = NumberUtils.parseLong(deviceId);
		if(id==null && StringUtils.isEmpty(deviceUid)) {
			return new Failure<>("device.identifier_empty");
		}
		ModelDevice device=authDao.getDevice(id,deviceUid);
		if(device==null) {
			try{
				boolean recheckDevice=mutex.isLocked();
				mutex.lock();
				if(recheckDevice) {
					device=authDao.getDevice(id,deviceUid);
				}
				if(device==null) {
					device=new ModelDevice();
					device.setActive(true);
					device.setAppVersion(appVersion);
					device.setBrand(deviceBrand);
					device.setDateCreation(new Date());
					device.setModel(deviceModel);
					device.setOperatingName(deviceOperatingName);
					device.setOperatingSystem(NumberUtils.pasrseInteger(deviceOperatingSystem));
					device.setOsVersion(deviceOsVersion);
					device.setUid(deviceUid);
					device.setLanguage(language);
					authDao.save(device);	
				}
	        }catch (Exception e) {
	        	mutex.unlock();	
	        }
		}else {
			device.setAppVersion(appVersion);
			device.setLanguage(language);
			authDao.saveOrUpdate(device);
		}
		if(!device.isActive()) {
			return new Failure<>("device.not_active");
		}
		return new Success<>(device);
	}
	@Transactional
	public Return<ModelSession> checkSession(String sessionId,Long deviceId, String token) {
		Long id = NumberUtils.parseLong(sessionId);
		if(id==null && StringUtils.isEmpty(sessionId)) {
			return new Failure<>("session.not_valid");
		}
		ModelSession session=authDao.getSession(id,token);
		if(session==null) {
			return new Failure<>("session.not_valid");
		}else {
			if(session.getDevice().getId()!=deviceId) {
				return new Failure<>("session.not_valid");
			}
			session.setDateLastAccess(new Date());
			authDao.saveOrUpdate(session);
		}
		if(session.getDateExpired()!=null) {
			return new Failure<>("session.expired");
		}
		return new Success<>(session);
	}
	
	
	
}
