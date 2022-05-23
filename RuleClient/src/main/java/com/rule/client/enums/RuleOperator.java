package com.rule.client.enums;

public enum RuleOperator {
	EQUAL_TO(" == "), DOES_NOT_CONTAINS(" != "), LESS_THAN_EQUAL_TO(" <= "), GRATER_THAN_EQUAL_TO(" >= "),
	OR_OPERATOR(" || "), AND_OPERATOR(" && "), DOUBLE_QUOTE("\""), IN(" In "), NOT_IN(" NotIn ");

	private final String field;

	RuleOperator(final String field) {
		this.field = field;
	}

	public String getField() {
		return this.field;
	}
}