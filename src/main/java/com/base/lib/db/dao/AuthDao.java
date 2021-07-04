package com.base.lib.db.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.base.lib.db.model.auth.ModelDevice;
import com.base.lib.db.model.auth.ModelLoginVerification;
import com.base.lib.db.model.auth.ModelSession;
import com.base.lib.db.model.auth.ModelUser;

@SuppressWarnings("unchecked")
public class AuthDao extends BaseDao {

	public ModelUser getUserByUserName(String username) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelUser.class);
		criteria.add(Restrictions.eq("username", username));
		return (ModelUser) criteria.getExecutableCriteria(getSession()).uniqueResult();
	}

	public List<ModelLoginVerification> getVerification(Long verificationId, String username, String code) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelLoginVerification.class);
		if(verificationId!=null) {
			criteria.add(Restrictions.eq("id", verificationId));
		}else {
			criteria.add(Restrictions.eq("username", username));
			criteria.add(Restrictions.eq("code", code));
		}
		return (List<ModelLoginVerification>) criteria.getExecutableCriteria(getSession()).list();
		
	}

	public ModelDevice getDevice(Long id, String deviceUid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelDevice.class);
		
		if(deviceUid!=null) {
			criteria.add(Restrictions.eq("uid", deviceUid));
		}else if(id!=null) {
			criteria.add(Restrictions.eq("id", id));
		} else {
			return null;
		}
		
		return (ModelDevice) criteria.getExecutableCriteria(getSession()).uniqueResult();
	}
	public ModelSession getSession(Long id, String token) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelSession.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("token", token));
		return (ModelSession) criteria.getExecutableCriteria(getSession()).uniqueResult();
	}
}
