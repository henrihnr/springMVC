package app.util;

import java.util.HashMap;
import java.util.Map;

public class Constraint {

	private String whereClause;
	private Map<String, Object> parameters;
	
	public Constraint() {
		whereClause = null;
		parameters = new HashMap<String, Object>();
	}
	
	public String getWhereClause() {
		return whereClause;
	}
	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	
}
