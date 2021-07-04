package com.base.lib.db.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import com.base.lib.db.model.auth.ModelStation;

public class StationDao extends BaseDao {

	public ModelStation getStationByName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelStation.class);
		criteria.add(Restrictions.eq("name", name));
		return (ModelStation) criteria.getExecutableCriteria(getSession()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<ModelStation> getStations(String searchName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelStation.class);
		if(!StringUtils.isEmpty(searchName)) {
			searchName=searchName.trim()+"%";
			criteria.add(Restrictions.ilike("name", searchName));
		}
		return (List<ModelStation>) criteria.getExecutableCriteria(getSession()).list();
	}
}
