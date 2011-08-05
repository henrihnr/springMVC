package app.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import app.domain.Employee;

@Repository("employeeDao")
public class EmployeeDao extends AbstractDao<Employee, Serializable> {

	public EmployeeDao() {
		this.domainClass = Employee.class;
	}
}
