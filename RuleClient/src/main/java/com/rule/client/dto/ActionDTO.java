package com.rule.client.dto;

import java.util.List;

public class ActionDTO {

	/** Action Variable to be set */
	private String actionName;

	/** Action value to set */
	private String values;

	/** Action amount to set */
	private String amount;

	/** Action amountOff to set */
	private String amountOff;

	/** Action amountOff to set */
	private String amountPercentOff;

	/** Action RangeDTO List to set */
	private List<RangeDTO> range;

	/** Action maxCap to set */
	private String maxCap;

	/** Action Price Name to be set */
	private String priceName;

	public ActionDTO() {
		super();
	}

	public ActionDTO(String actionName, String values, String amount, String amountOff, String amountPercentOff,
			List<RangeDTO> range, String maxCap, String priceName) {
		this.actionName = actionName;
		this.values = values;
		this.amount = amount;
		this.amountOff = amountOff;
		this.amountPercentOff = amountPercentOff;
		this.range = range;
		this.maxCap = maxCap;
		this.priceName = priceName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmountOff() {
		return amountOff;
	}

	public void setAmountOff(String amountOff) {
		this.amountOff = amountOff;
	}

	public String getAmountPercentOff() {
		return amountPercentOff;
	}

	public void setAmountPercentOff(String amountPercentOff) {
		this.amountPercentOff = amountPercentOff;
	}

	public List<RangeDTO> getRange() {
		return range;
	}

	public void setRange(List<RangeDTO> range) {
		this.range = range;
	}

	public String getMaxCap() {
		return maxCap;
	}

	public void setMaxCap(String maxCap) {
		this.maxCap = maxCap;
	}

	public String getPriceName() {
		return priceName;
	}

	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}

	@Override
	public String toString() {
		return "ActionDTO{" + "actionName='" + actionName + '\'' + ", values='" + values + '\'' + ", amount='" + amount
				+ '\'' + ", amountOff='" + amountOff + '\'' + ", amountPercentOff='" + amountPercentOff + '\''
				+ ", range=" + range + ", maxCap='" + maxCap + '\'' + ", priceName='" + priceName + '\'' + '}';
	}
}
