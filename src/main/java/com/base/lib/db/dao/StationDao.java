package com.base.lib.db.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.base.lib.db.model.auth.ModelStation;

public class StationDao extends BaseDao {

	public ModelStation getStationByName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelStation.class);
		criteria.add(Restrictions.eq("name", name));
		return (ModelStation) criteria.getExecutableCriteria(getSession()).uniqueResult();
	}
}
