package app.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import app.domain.Employee;

public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return Employee.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Employee employee = (Employee) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "", "ID harus diisi.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Nama harus diisi.");
		
	}
}
