package app.dao;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T, ID extends Serializable> {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Class domainClass;
	
	public AbstractDao() {}
	
	public T findById(ID id) {
		return (T) sessionFactory.getCurrentSession().get(domainClass, id);
	}
	
	public T save(T domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		
		return domain;
	}
	
	public void delete(T domain) {
		sessionFactory.getCurrentSession().delete(domain);
	}
	
	public void delete(ID id) {
		delete(findById(id));
	}
	
}