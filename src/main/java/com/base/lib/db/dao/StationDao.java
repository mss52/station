package com.base.lib.db.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.springframework.util.StringUtils;

import com.base.bean.InsightsBean;
import com.base.lib.db.model.auth.ModelStation;

public class StationDao extends BaseDao {

	public ModelStation getStationByName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelStation.class);
		criteria.add(Restrictions.eq("name", name));
		return (ModelStation) criteria.getExecutableCriteria(getSession()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<ModelStation> getStations(String searchName,Long statusId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelStation.class);
		if(!StringUtils.isEmpty(searchName)) {
			searchName=searchName.trim()+"%";
			criteria.add(Restrictions.ilike("name", searchName));
		}
		if(statusId!=null) {
			criteria.add(Restrictions.eq("status.id", statusId));
		}
		return (List<ModelStation>) criteria.getExecutableCriteria(getSession()).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<InsightsBean> getInsights(long stationId,Date fromDate,Date toDate) {
		
		String queryString="SELECT sum(amount) 'totalFillAmount',count(*) 'totalFillCount',"
				+ "MONTH(`date`) 'month' ,Day(`date`) 'dayOfMonth' \n"
				+ "FROM station_schema.car_filling_history cf\n"
				+ "left join `user` u on cf.filling_user_id=u.id\n"
				+ "where u.station_id=:stationId \n";
		if(fromDate!=null) {
			queryString=queryString+ " and `date`>= :fromDate"; 
		}
		if(toDate!=null) {
			queryString=queryString+ " and `date`<= :toDate \n"; 
		}
		queryString=queryString+ " group by MONTH(`date`),Day(`date`);";
		
		SQLQuery query = getSession().createSQLQuery(queryString);
		query.setParameter("stationId", stationId);
		if(fromDate!=null) {
			query.setParameter("fromDate", fromDate);
		}
		if(toDate!=null) {
			query.setParameter("toDate", toDate);
		}
		query.addScalar("totalFillAmount", DoubleType.INSTANCE);
		query.addScalar("totalFillCount", LongType.INSTANCE);
		query.addScalar("month", LongType.INSTANCE);
		query.addScalar("dayOfMonth", LongType.INSTANCE);
		query.setResultTransformer( Transformers.aliasToBean( InsightsBean.class ));
		return query.list();
	}
}
