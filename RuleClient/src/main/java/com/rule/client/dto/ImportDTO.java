package com.rule.client.dto;

import java.util.HashMap;
import java.util.List;

public class ImportDTO {

	/** Rule Name */
	private String ruleName;

	/** Package Name to be used in DRL file */
	private String packageName;

	/** No of Entity Class to be import. */
	private List<String> entityList;

	/** No of global variable with alias name to be needed */
	private HashMap<String, String> globalVariables = new HashMap<>();

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<String> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<String> entityList) {
		this.entityList = entityList;
	}

	public HashMap<String, String> getGlobalVariables() {
		return globalVariables;
	}

	public void setGlobalVariables(HashMap<String, String> globalVariables) {
		this.globalVariables = globalVariables;
	}

}
