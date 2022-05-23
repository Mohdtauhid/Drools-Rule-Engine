package com.rule.client.dto;

import java.util.List;

public class CriteriaDTO {

	/** Criteria Condition Variable Name */
	private String conditionName;

	/** Criteria Operator Name */
	private String operator;

	/** Criteria Condition Value */
	private List<String> values;

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}
