package app.service;

import app.domain.Employee;
import app.util.Constraint;
import app.util.OrderMap;
import app.util.SearchResult;

public interface DatabaseService {

	public SearchResult searchEmployee(Constraint constraint, OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize);
    public SearchResult lookupEmployee(Constraint constraint, int offset, int pageSize);
    public Employee findEmployeeById(String id);
    public Employee saveEmployee(Employee employee);
    
}
