package app.service;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.domain.Employee;
import app.util.Constant;
import app.util.Constraint;
import app.util.OrderMap;
import app.util.SearchResult;

import app.dao.EmployeeDao;
import app.dao.GenericDao;

@Service("databaseService")
@Transactional
public class DatabaseServiceImpl implements DatabaseService {

	@Autowired private GenericDao genericDao;
	
	@Autowired private EmployeeDao employeeDao;
	
	public SearchResult searchEmployee(Constraint constraint, OrderMap orderMap,
			boolean isUsingPaging, int offset, int pageSize) {
        return genericDao.searchHQL("employee", "Employee employee", constraint, orderMap, isUsingPaging, offset, pageSize);
    }
	
	public SearchResult lookupEmployee(Constraint constraint, int offset, int pageSize) {
    	
    	StringBuilder buffer = new StringBuilder(constraint.getWhereClause());
    	if(StringUtils.isNotBlank(constraint.getWhereClause())) {
    		buffer.append(" and ");
    	}
    	buffer.append("employee.status = :status");
    	
    	constraint.setWhereClause(buffer.toString());
    	
    	Map parameters = constraint.getParameters();
    	parameters.put("status", true);
    	
    	OrderMap orderMap = new OrderMap();
    	orderMap.put("employee.name", Constant.ORDER_ASC);
    	
        return genericDao.searchHQL("new Employee(employee.id, employee.name)", "Employee employee", 
        		constraint, orderMap, true, offset, pageSize);
    }
	
	public Employee findEmployeeById(String id) {
        return employeeDao.findById(id);
    }
	
	@Transactional(readOnly = false)
    public Employee saveEmployee(Employee employee) {
    	
        return employeeDao.save(employee);
    }
}
