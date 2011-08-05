package app.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.util.Constant;
import app.util.Constraint;
import app.util.OrderMap;
import app.util.PagingInfo;
import app.util.SearchResult;

@Repository("genericDao")
public class GenericDao {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired protected SessionFactory sessionFactory;
	
	public List runHQL(String hql, Map<String, Object> parameters) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		if(parameters != null) {
			for (String key : parameters.keySet()) {
				query.setParameter(key, parameters.get(key));
			}
		}
		
		return query.list();
	}
	
	public int executeUpdateHQL(String hql, Map<String, Object> parameters) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		if(parameters != null) {
			for (String key : parameters.keySet()) {
				query.setParameter(key, parameters.get(key));
			}
		}
		
		return query.executeUpdate();
	}
	
	public SearchResult searchHQL(String selectClause, String fromClause, Constraint constraint, 
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize, boolean isCacheQuery) {
		
		StringBuffer hql = new StringBuffer();
		
		hql.append(" select " + selectClause);
		hql.append(" from " + fromClause);
		
		Map<String, Object> parameters = null;
		
		if (constraint != null) {
			if(StringUtils.isNotBlank(constraint.getWhereClause())) {
				hql.append(" where " + constraint.getWhereClause());
			}
			
			parameters = constraint.getParameters();
		}

		if (orderMap != null) {
			hql.append(" order by " + orderMap.toString());
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		if(parameters != null) {
			for (String key : parameters.keySet()) {
				query.setParameter(key, parameters.get(key));
			}
		}
		
		PagingInfo pagingInfo = null;
		
		if (isUsingPaging) {
			
			String hqlPaging = "select count(*) from " + fromClause;
			if (constraint != null) {
				if(StringUtils.isNotBlank(constraint.getWhereClause())) {
					hqlPaging = hqlPaging + " where " + constraint.getWhereClause();
				}
			}
			
			Query queryPaging = sessionFactory.getCurrentSession()
	 				.createQuery(hqlPaging);
			
			if(parameters != null) {
				for (String key : parameters.keySet()) {
					queryPaging.setParameter(key, parameters.get(key));
				}
			}
			
			int totalRows = ((Long) queryPaging.uniqueResult()).intValue();
			
			if(offset < 0 || offset >= totalRows) {
				offset = 0;
			}
			if (pageSize <= 0) {
				pageSize = Constant.DEFAULT_PAGE_SIZE;
			}
			
			query.setFirstResult(offset).setMaxResults(pageSize);
			
			pagingInfo = new PagingInfo(offset, pageSize, totalRows);
		}
		
		List recs = query.setCacheable(isCacheQuery).list();
		 
		return new SearchResult(recs, pagingInfo);
	}
	
	public SearchResult searchHQL(String selectClause, String fromClause, Constraint constraint, 
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize) {
		
		return searchHQL(selectClause, fromClause, constraint, orderMap, isUsingPaging, offset, pageSize, false);
	}
	
	public SearchResult searchSQLQuery(String selectClause, String fromClause, Constraint constraint, 
			Map<String, Type> mapScalar, Map mapEntity,
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize) {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select " + selectClause);
		sql.append(" from " + fromClause);
		
		Map<String, Object> parameters = null;
		
		if (constraint != null) {
			if(StringUtils.isNotBlank(constraint.getWhereClause())) {
				sql.append(" where " + constraint.getWhereClause());
			}
			
			parameters = constraint.getParameters();
		}

		if (orderMap != null) {
			sql.append(" order by " + orderMap.toString());
		}
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		
		if(parameters != null) {
			for (String key : parameters.keySet()) {
				query.setParameter(key, parameters.get(key));
			}
		}
		
		if(mapScalar != null) {
			for(String key : mapScalar.keySet()) {
				query.addScalar(key, mapScalar.get(key));
			}
		}
		
		if(mapEntity != null) {
			
		}
		
		PagingInfo pagingInfo = null;
		
		if (isUsingPaging) {
			
			String sqlPaging = "select count(*) from " + fromClause;
			if (constraint != null) {
				if(StringUtils.isNotBlank(constraint.getWhereClause())) {
					sqlPaging = sqlPaging + " where " + constraint.getWhereClause();
				}
			}
			
			SQLQuery queryPaging = sessionFactory.getCurrentSession()
	 				.createSQLQuery(sqlPaging);
			
			if(parameters != null) {
				for (String key : parameters.keySet()) {
					queryPaging.setParameter(key, parameters.get(key));
				}
			}
			
			int totalRows = ((BigInteger) queryPaging.uniqueResult()).intValue();
			
			if(offset < 0 || offset >= totalRows) {
				offset = 0;
			}
			if (pageSize <= 0) {
				pageSize = Constant.DEFAULT_PAGE_SIZE;
			}
			
			query.setFirstResult(offset).setMaxResults(pageSize);
			
			pagingInfo = new PagingInfo(offset, pageSize, totalRows);
		}
		
		List recs = query.list();
		 
		return new SearchResult(recs, pagingInfo);
	}
	
	public List runSQLQuery(String sql, Map<String, Object> parameters, Map<String, Type> mapScalar) {
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		
		if(parameters != null) {
			for (String key : parameters.keySet()) {
				query.setParameter(key, parameters.get(key));
			}
		}
		
		if(mapScalar != null) {
			for(String key : mapScalar.keySet()) {
				query.addScalar(key, mapScalar.get(key));
			}
		}
		
		return query.list();
	}

}
