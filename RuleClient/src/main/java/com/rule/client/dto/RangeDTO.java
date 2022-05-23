package com.rule.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RangeDTO {

	/** Minimum Amount */
	private String minimumAmount;

	/** Maximum Amount */
	private String maximumAmount;

	/** Amount off */
	private double amountOff;

	/** Max Cap */
	private double maxCap;
}
