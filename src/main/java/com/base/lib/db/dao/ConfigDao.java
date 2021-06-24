package com.base.lib.db.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.base.lib.db.model.ModelConfig;

@SuppressWarnings("unchecked")
public class ConfigDao extends BaseDao {

	public ModelConfig getConfigByKey(String key) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelConfig.class);
		criteria.add(Restrictions.eq("key", key));
		criteria.add(Restrictions.isNull("deactivateAt"));
		criteria.addOrder(Order.desc("id"));
		return (ModelConfig) criteria.getExecutableCriteria(getSession()).setMaxResults(1).uniqueResult();
	}

}
