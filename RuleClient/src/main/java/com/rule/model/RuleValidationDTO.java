package com.rule.model;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class RuleValidationDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The domain. */
	@ApiModelProperty(notes = "domain")
	private String domain;

	/** The geo. */
	@ApiModelProperty(notes = "geo")
	private String geo;

	/** The category. */
	@ApiModelProperty(notes = "category")
	private List<String> category;

	/** The productType. */
	@ApiModelProperty(notes = "productType")
	private List<String> productType;

	/** The occasion. */
	@ApiModelProperty(notes = "occasion")
	private List<String> occasion;

	/** The recipient. */
	@ApiModelProperty(notes = "recipient")
	private List<String> recipient;

	/** The product. */
	@ApiModelProperty(notes = "product")
	private String product;

	/** The utmCampaign. */
	@ApiModelProperty(notes = "utmCampaign")
	private String utmCampaign;

	/** The fromCountry. */
	@ApiModelProperty(notes = "fromCountry")
	private String fromCountry;

	/** The fromCity. */
	@ApiModelProperty(notes = "fromCity")
	private String fromCity;

	/** The deliveryCity. */
	@ApiModelProperty(notes = "deliveryCity")
	private String deliveryCity;

	/** The deliveryDate. */
	@ApiModelProperty(notes = "deliveryDate")
	private String deliveryDate;

	/** The partyRole. */
	@ApiModelProperty(notes = "partyRole")
	private String partyRole;

	/** The partyClassification. */
	@ApiModelProperty(notes = "partyClassification")
	private String partyClassification;

	/** The customer. */
	@ApiModelProperty(notes = "customer")
	private String customer;

	/** The recency. */
	@ApiModelProperty(notes = "recency")
	private float recency;

	/** The frequency. */
	@ApiModelProperty(notes = "frequency")
	private float frequency;

	/** The monetary. */
	@ApiModelProperty(notes = "monetary")
	private float monetary;

	/** The deliveryCountry. */
	@ApiModelProperty(notes = "deliveryCountry")
	private String deliveryCountry;

	/** The customer type. */
	@ApiModelProperty(notes = "customerType")
	private String customerType;

	/** The quantity. */
	@ApiModelProperty(notes = "quantity")
	private float quantity;

	/** The cartItem. */
	@ApiModelProperty(notes = "cartItem")
	private float cartItem;

	/** The currencyId. */
	@ApiModelProperty(notes = "currencyId")
	private String currencyId;

	/** The addon. */
	@ApiModelProperty(notes = "addon")
	private String addon;

	/** The cartValue. */
	@ApiModelProperty(notes = "cartValue")
	private float cartValue;

	/** The paymentType. */
	@ApiModelProperty(notes = "paymentType")
	private String paymentType;

	/** The cardType. */
	@ApiModelProperty(notes = "cardType")
	private String cardType;

	/** The issuingBank. */
	@ApiModelProperty(notes = "issuingBank")
	private String issuingBank;

	/** The wallet. */
	@ApiModelProperty(notes = "wallet")
	private String wallet;

	/** The cartType. */
	@ApiModelProperty(notes = "cartType")
	private String cartType;

	/** The actions. */
	@ApiModelProperty(notes = "actions")
	private RuleValidationResponseDTO actions;

	/** The finalAmount. */
	@ApiModelProperty(notes = "finalAmount")
	private float finalAmount;

	/** The priceValueId. */
	@ApiModelProperty(notes = "priceValueId")
	private String priceValueId;

	/** The deviceCategory. */
	@ApiModelProperty(notes = "deviceCategory")
	private String deviceCategory;

}