package com.base.lib.db.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.base.lib.db.model.ModelConfig;
import com.base.lib.db.model.ModelLocalization;

public class LocalizationDao extends BaseDao {

	@Transactional
	public ModelLocalization getLocalizedCode(String language,String code,String defaultLanguage) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelLocalization.class);
		if(language!=null) {
			language=language.toLowerCase();
		}
		criteria.add(Restrictions.eq("language", language));
		criteria.add(Restrictions.eq("code", code));
		criteria.addOrder(Order.desc("id"));
		ModelLocalization l = (ModelLocalization) criteria.getExecutableCriteria(getSession()).setMaxResults(1)
				.uniqueResult();
		
		if(l==null) {
			criteria = DetachedCriteria.forClass(ModelLocalization.class);
			criteria.add(Restrictions.eq("code", code));
			criteria.add(Restrictions.eq("language", defaultLanguage));
			criteria.addOrder(Order.desc("id"));
			l = (ModelLocalization) criteria.getExecutableCriteria(getSession()).setMaxResults(1)
					.uniqueResult();
		}
		return l;
	}

}
