package app.web.controller;

import java.util.Map;

import org.springframework.ui.ModelMap;

import app.util.Constraint;
import app.web.filter.FlashMap;
import app.web.filter.FlashMap.Message;
import app.web.filter.FlashMap.MessageType;

public class BaseController {

	protected Constraint resolveSearch(Object example) {
		if(example == null) {
			return null;
		}
		
		Constraint constraint = new Constraint();
		
		StringBuilder buffer = new StringBuilder();
		Map<String, Object> parameters = constraint.getParameters();
		
		// default
		String operator = " LIKE ";
		String prefix = "%";
		String suffix = "%";
		
		specificSearch(example, buffer, parameters, operator, prefix, suffix);
		
		constraint.setWhereClause(buffer.toString());
		constraint.setParameters(parameters);
		
		return constraint;
	}
	
	protected void specificSearch(Object object, StringBuilder buffer, Map<String, Object> parameters, 
			String operator, String prefix, String suffix) {
		// implemented at child class..
	}
	
	protected void addMessage(ModelMap modelMap, String message, MessageType type, boolean isRedirect) {
		if(isRedirect) {
			switch (type) {
				case info:
					FlashMap.setInfoMessage(message);
					break;
					
				case success:
					FlashMap.setSuccessMessage(message);
					break;
					
				case warning:
					FlashMap.setWarningMessage(message);
					break;
					
				case error:
					FlashMap.setErrorMessage(message);
					break;
			}
			
		} else {
			modelMap.put("message", new Message(type, message));
		}
	}
}
