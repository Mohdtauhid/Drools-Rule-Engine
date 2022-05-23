package com.rule.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Class PriceEntity.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PriceEntity implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** The price name. */
	@ApiModelProperty(notes = "Price Name")
	@Field("price_name")
	private String priceName;

	/** The price id. */
	@ApiModelProperty(notes = "Price Id")
	@Field("price_id")
	@NotBlank(message = "{price.price_id_blank}")
	private String priceId;

	/** The amount. */
	@ApiModelProperty(notes = "Amount")
	@Field("amount")
	private Double amount;

	/** The amount off. */
	@ApiModelProperty(notes = "Action Off")
	@Field("amount_off")
	private Double amountOff;

	/** The values. */
	@ApiModelProperty(notes = "Values")
	@Field("values")
	private List<@NotBlank(message = "{price.values_blank}") String> values;

	/** The amount range. */
	@ApiModelProperty(notes = "Amount Range")
	@Field("amount_range")
	private List<AmountRangeEntity> amountRange;

	/** The currency. */
	@ApiModelProperty(notes = "currency")
	@Field("currency")
	private String currency;

}