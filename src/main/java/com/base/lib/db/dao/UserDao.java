package com.base.lib.db.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;

import com.base.bean.InsightsBean;

public class UserDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<InsightsBean> getInsights(long userId,Date fromDate,Date toDate) {
		String queryString="SELECT sum(amount) 'totalFillAmount',count(*) 'totalFillCount',"
				+ "MONTH(`date`) 'month' ,Day(`date`) 'dayOfMonth' \n"
				+ "FROM car_filling_history\n"
				+ "where filling_user_id=:userId ";
		if(fromDate!=null) {
			queryString=queryString+ " and `date`>= :fromDate"; 
		}
		if(toDate!=null) {
			queryString=queryString+ " and `date`<= :toDate \n"; 
		}
		queryString=queryString+ " group by MONTH(`date`),Day(`date`);";
		
		SQLQuery query = getSession().createSQLQuery(queryString);
		query.setParameter("userId", userId);
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
