package com.rule.model;

import java.util.List;

import com.rule.client.dto.RangeDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class RuleValidationResponseDTO {

	/** The maxCap. */
	@ApiModelProperty(notes = "maxCap")
	private double maxCap;

	/** The amountOff. */
	@ApiModelProperty(notes = "amountOff")
	private double amountOff;

	/** The amountPercentOff. */
	@ApiModelProperty(notes = "amountPercentOff")
	private double amountPercentOff;

	/** The values. */
	@ApiModelProperty(notes = "values")
	private String values;

	/** The range. */
	@ApiModelProperty(notes = "range")
	private List<RangeDTO> range;

	/** The actionName. */
	@ApiModelProperty(notes = "actionName")
	private String actionName;

	/** The priceName. */
	@ApiModelProperty(notes = "priceName")
	private String priceName;

	/** The amount. */
	@ApiModelProperty(notes = "amount")
	private double amount;
}