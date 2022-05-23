package com.rule.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PromotionEntity implements Serializable {

	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@ApiModelProperty(notes = "ID")
	@Id
	private String id;

	/** The promotion name. */
	@ApiModelProperty(notes = "Promotion Name")
	@NotBlank(message = "{promotion.promotion_name_blank}")
	@Size(max = 100, message = "{promotion.promotion_name_size}")
	@Field("promotion_name")
	private String promotionName;

	/** The promotion description. */
	@ApiModelProperty(notes = "Promotion Description")
	@NotBlank(message = "{promotion.promotion_description_blank}")
	@Size(max = 500, message = "{promotion.promotion_description_size}")
	@Field("promotion_description")
	private String promotionDescription;

	/** The promotion type id. */
	@ApiModelProperty(notes = "Promotion Type Id")
	@NotBlank(message = "{promotion.promotion_type_id_blank}")
	@Field("promotion_type_id")
	private String promotionTypeId;

	/** The status. */
	@ApiModelProperty(notes = "Status")
	@NotBlank(message = "{promotion.promotion_status_blank}")
	@Pattern(regexp = "active|inactive", flags = Pattern.Flag.CASE_INSENSITIVE, message = "{promotion.promotion_status_invalid}")
	@Field("status")
	private String status;

	/** The from date. */
	@ApiModelProperty(notes = "From Date")
	@Field("from_Date")
	private Instant fromDate;

	/** The thru date. */
	@ApiModelProperty(notes = "Thru Date")
	@Field("thru_Date")
	private Instant thruDate;

	/** The coupon required. */
	@ApiModelProperty(notes = "Coupon Required")
	@Field("coupon_required")
	private Boolean couponRequired;

	/** The use limit per code. */
	@ApiModelProperty(notes = "use Limit Per Code")
	@Field("use_limit_per_code")
	@Size(max = 10, message = "{promotion.useLimitPerCode_size}")
	@Pattern(regexp = "^[0-9]*$", message = "{promotion.useLimitPerCode_not_valid}")
	private String useLimitPerCode;

	/** The use limit per customer. */
	@ApiModelProperty(notes = "Use Limit Per Customer")
	@Field("use_limit_per_customer")
	@Size(max = 3, message = "{promotion.useLimitPerCustomer_size}")
	@Pattern(regexp = "^[0-9]*$", message = "{promotion.useLimitPerCustomer_not_valid}")
	private String useLimitPerCustomer;

	/** The workflow status. */
	@ApiModelProperty(notes = "Workflow Status")
	@Field("workflow_status")
	private String workflowStatus;

	/** The criteria. */
	@ApiModelProperty(notes = "Criteria")
	@Valid
	@Field("criteria")
	private List<CriteriaEntity> criteria;

	/** The action. */
	@ApiModelProperty(notes = "Action")
	@Valid
	@Field("action")
	private ActionEntity action;

	/** The promotion type. */
	@ApiModelProperty(notes = "Promotion Type")
	@Transient
	private String promotionType;

	/** The rule id. */
	@ApiModelProperty(notes = "Rule Id")
	@JsonIgnore
	@Field("rule_id")
	private String ruleId;

	/** The List of coupon code. */
	@ApiModelProperty(notes = "Coupon Code")
	@Transient
	private List<String> couponCode;

	/** The List of domain. */
	@ApiModelProperty(notes = "Domain")
	@Transient
	private List<String> domain;

	/** The List of geo. */
	@ApiModelProperty(notes = "Geo")
	@Transient
	private List<String> geo;

	/** The List of product name. */
	@ApiModelProperty(notes = "Product Name")
	@Transient
	private List<String> productName;

	/** The List of category name. */
	@ApiModelProperty(notes = "Category Name")
	@Transient
	private List<String> categoryName;

	/** The user specific promotion. */
	@ApiModelProperty(notes = "User Specific Promotion")
	@Transient
	private Boolean userSpecificPromotion;

	/** The before offer message. */
	@ApiModelProperty(notes = "Before Offer Message")
	@Transient
	private String beforeOfferMessage;

	/** The after offer message. */
	@ApiModelProperty(notes = "After Offer Message")
	@Transient
	private String afterOfferMessage;
}
