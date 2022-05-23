package com.rule.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Class AmountRangeEntity.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AmountRangeEntity implements Serializable {

	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** The minimum amount. */
	@ApiModelProperty(notes = "Minimum Amount")
	@Field("minimum_amount")
	private Double minimumAmount;

	/** The maximum amount. */
	@ApiModelProperty(notes = "Maximum Amount")
	@Field("maximum_amount")
	private Double maximumAmount;

	/** The amount off. */
	@ApiModelProperty(notes = "Action Off")
	@Field("amount_off")
	private Double amountOff;

	/** The max_cap. */
	@ApiModelProperty(notes = "Max Cap")
	@Field("max_cap")
	private Double maxCap;

}