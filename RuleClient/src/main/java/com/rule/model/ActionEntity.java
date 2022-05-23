package com.rule.model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Class ActionEntity.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ActionEntity implements Serializable {

	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** The before offer message. */
	@ApiModelProperty(notes = "Before Offer Message")
	@Field("before_offer_message")
	private String beforeOfferMessage;

	/** The after offer message. */
	@ApiModelProperty(notes = "After Offer Message")
	@Field("after_offer_message")
	private String afterOfferMessage;

	/** The action name. */
	@ApiModelProperty(notes = "Action Name")
	@Field("action_name")
	private String actionName;

	/** The action id. */
	@ApiModelProperty(notes = "Action Id")
	@Field("action_id")
	@NotBlank(message = "{action.action_id_blank}")
	private String actionId;

	/** The max_cap. */
	@ApiModelProperty(notes = "Max Cap")
	@Field("max_cap")
	private Double maxCap;

	/** The price. */
	@ApiModelProperty(notes = "Price")
	@Field("price")
	@Valid
	private PriceEntity price;

}