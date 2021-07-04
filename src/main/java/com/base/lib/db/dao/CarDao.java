package com.base.lib.db.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.base.lib.db.model.ModelCar;
import com.base.lib.db.model.ModelCarCode;

public class CarDao extends BaseDao {

	public ModelCar getCarByPlateNumberAndPlateCode(String plateNumber,String plateCode, boolean lock) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelCar.class);
		criteria.add(Restrictions.eq("plateNumber", plateNumber));
		criteria.add(Restrictions.eq("plateCode", plateCode));
		if(lock) {
			criteria.setLockMode(LockMode.PESSIMISTIC_WRITE);
		}
		return (ModelCar) criteria.getExecutableCriteria(getSession()).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<ModelCarCode> getCarCodes() {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelCarCode.class);
		return (List<ModelCarCode>) criteria.getExecutableCriteria(getSession()).list();
	}

	public ModelCarCode getCarCodeByCode(String plateCode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelCarCode.class);
		criteria.add(Restrictions.eq("code", plateCode));
		return (ModelCarCode) criteria.getExecutableCriteria(getSession()).uniqueResult();
	}

}
