package com.base.lib.db.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public <T> T getValueById(Class<T> modelClass, long id) {
		return (T) sessionFactory.getCurrentSession().get(modelClass, id);
	}

	@SuppressWarnings("unchecked")
	public <T> T getValueById(Class<T> modelClass, int id) {
		return (T) sessionFactory.getCurrentSession().get(modelClass, id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public <T> T getValueByIdForUpdate(Class<T> modelClass, int id) {
		return (T) sessionFactory.getCurrentSession().get(modelClass, id, LockMode.PESSIMISTIC_WRITE);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public <T> T getValueByIdForUpdate(Class<T> modelClass, long id) {
		return (T) sessionFactory.getCurrentSession().get(modelClass, id, LockMode.PESSIMISTIC_WRITE);
	}

	public void save(Object model) {
		sessionFactory.getCurrentSession().save(model);
	}

	public void saveOrUpdate(Object model) {
		sessionFactory.getCurrentSession().saveOrUpdate(model);
	}

	public <T> void saveMass(List<T> model) {
		for (int i = 0; i < model.size(); i++) {
			sessionFactory.getCurrentSession().save(model.get(i));
			if (i % 20 == 0) { // 20, same as the JDBC batch size
				// flush a batch of inserts and release memory:
				sessionFactory.getCurrentSession().flush();
				sessionFactory.getCurrentSession().clear();
			}
		}
	}

	public <T> void updateMass(Collection<T> model) {
		int i=0;
		for(T t:model)
		{
			sessionFactory.getCurrentSession().update(t);
			if (i % 20 == 0) { // 20, same as the JDBC batch size
				// flush a batch of inserts and release memory:
				sessionFactory.getCurrentSession().flush();
				sessionFactory.getCurrentSession().clear();
			}
			i++;
		}
	}
	public void merge(Object model) {
		sessionFactory.getCurrentSession().merge(model);
	}
}
