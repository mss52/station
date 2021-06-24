package com.base.lib.db.dao;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.base.lib.db.model.ModelCar;

@SuppressWarnings("unchecked")
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

}
