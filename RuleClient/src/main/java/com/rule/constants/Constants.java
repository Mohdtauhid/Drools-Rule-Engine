package com.rule.constants;

/**
 * The Class ExampleConstants.
 */
public class Constants {

	private Constants() {

	}

	/** The Constant PROP_MESSAGE. */
	// Constants for Application Property File start with a prefix of PROP_
	public static final String PROP_MESSAGE = "message";

	/** The Constant PROP_NAME. */
	public static final String PROP_NAME = "name";

	/** The Constant PATH_VARIABLE_RES_ID. */
	// Constants for URL PATH Variables start with a prefix of PATH_VAR_
	public static final String PATH_VARIABLE_RES_ID = "resource-id";

	/** The Constant STATUS_INACTIVE. */
	public static final String STATUS_INACTIVE = "inactive";

	/** The Constant STATUS_DELETE. */
	public static final String STATUS_DELETE = "delete";

	/** The Constant STATUS_ACTIVE. */
	public static final String STATUS_ACTIVE = "active";

	/** The Constant PROMOTION_DELETED. */
	public static final String PROMOTION_DELETED = "promotion_successfully_deleted";

	/** The Constant PROMOTION_UPDATE. */
	public static final String PROMOTION_UPDATED = "promotion_successfully_updated";

	/** The Constant ALPHA_NUMERIC_STRING. */
	public static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/** The Constant COUPON_SPECNAME. */
	public static final String COUPON_SPECNAME = "Coupon";

	/** The Constant KAFKA_FAILED. */
	public static final String KAFKA_FAILED = "failed";

	/** The Constant COUPON_EXPORT_FAILURE. */
	public static final String COUPON_EXPORT_FAILURE = "coupon.export_fail";

	/** The Constant COUPON_EXPORT_SUCCESS. */
	public static final String COUPON_EXPORT_SUCCESS = "coupon.export_success";

	/** The Constant COUPON_UPDATED. */
	public static final String COUPON_UPDATED = "coupon.update_success";

	/** The Constant COUPON_DELETED. */
	public static final String COUPON_DELETED = "coupon.delete_success";

	/** The KAFKA_TOPIC. */
	public static final String KAFKA_TOPIC = "minecraft_coupons";

	/** The Constant IN_PROGRESS. */
	public static final String IN_PROGRESS = "InProgress";

	/** The Constant INVALID_ADVANCE_SEARCH_FILTER. */
	public static final String INVALID_ADVANCE_SEARCH_FILTER = "Invalid input data";

	/** The Constant SEARCH_FIELD_SELECT. */
	public static final String SEARCH_FIELD_SELECT = "Enter at least one field";

	/** The Constant SEARCH_FIELD_SELECT_DUPLICATE. */
	public static final String SEARCH_FIELD_SELECT_DUPLICATE = "Enter unique search field names";

	/** The Constant SEARCH_OPERATOR_INVALID. */
	public static final String SEARCH_OPERATOR_INVALID = "Enter valid search operator for %s";

	/** The Constant SEARCH_VALUE_INVALID. */
	public static final String SEARCH_VALUE_INVALID = "Enter valid search field value for %s";

	/** The Constant SEARCH_FIELD_NAME_INVALID. */
	public static final String SEARCH_FIELD_NAME_INVALID = "Enter valid search field name %s";

	/** The Constant MASTER_CRITERIA_DOMAIN_CONDITION_NAME. */
	public static final String MASTER_CRITERIA_DOMAIN_CONDITION_NAME = "DOMAIN";

	/** The Constant MASTER_CRITERIA_GEO_CONDITION_NAME. */
	public static final String MASTER_CRITERIA_GEO_CONDITION_NAME = "GEO";

	/** The Constant MASTER_CRITERIA_CATEGORY_CONDITION_NAME. */
	public static final String MASTER_CRITERIA_CATEGORY_CONDITION_NAME = "CATEGORY";

	/** The Constant MASTER_CRITERIA_PRODUCT_CONDITION_NAME. */
	public static final String MASTER_CRITERIA_PRODUCT_CONDITION_NAME = "PRODUCT";

	/** The Constant MASTER_CRITERIA_CURRENCY_CONDITION_NAME. */
	public static final String MASTER_CRITERIA_CURRENCY_CONDITION_NAME = "CURRENCY_ID";

	/** The Constant MASTER_CRITERIA_CURRENCY_CONDITION_NAME. */
	public static final String MASTER_CRITERIA_CART_CONDITION_NAME = "CART_ITEM";

	/** The KAFKA_TOPIC_AUDIT_MINECRAFT_MASTER_CRITERIA. */
	public static final String KAFKA_TOPIC_AUDIT_MINECRAFT_MASTER_CRITERIA = "audit.minecraft.master_criteria";

	/** The KAFKA_TOPIC_AUDIT_MINECRAFT_MASTER_ACTIONS. */
	public static final String KAFKA_TOPIC_AUDIT_MINECRAFT_MASTER_ACTIONS = "audit.minecraft.master_actions";

	/** The KAFKA_TOPIC_AUDIT_MINECRAFT_PROMOTION_COUPONS. */
	public static final String KAFKA_TOPIC_AUDIT_MINECRAFT_PROMOTION_COUPONS = "audit.minecraft.promotion_coupons";

	/** The KAFKA_TOPIC_AUDIT_MINECRAFT_PROMOTIONS. */
	public static final String KAFKA_TOPIC_AUDIT_MINECRAFT_PROMOTIONS = "audit.minecraft.promotions";

	/** The KAFKA_TOPIC_AUDIT_MINECRAFT_PROMOTIONS_TYPE. */
	public static final String KAFKA_TOPIC_AUDIT_MINECRAFT_PROMOTIONS_TYPE = "audit.minecraft.promotions_type";

	/** The PROMOTION_ID. */
	public static final String PROMOTION_ID = "promotion_id";

	/** The BATCH_ID. */
	public static final String BATCH_ID = "batch_id";

	/** The PROMOTION_COUPONS. */
	public static final String PROMOTION_COUPONS = "promotion_coupons";

	/** The AUTO_GENERATED. */
	public static final String AUTO_GENERATED = "auto_generated";
	
	/** The STATUS. */
	public static final String STATUS = "status";
	
	/** The CREATED_AT. */
	public static final String CREATED_AT = "created_at";
	
	/** The CREATED_BY. */
	public static final String CREATED_BY = "created_by";
	
	/** The ID. */
	public static final String ID = "_id";
	
	/** The PROMOTION_NAME. */
	public static final String PROMOTION_NAME = "promotion_name";

	/** The FROM_DATE. */
	public static final String FROM_DATE = "from_Date";
	
	/** The THRU_DATE. */
	public static final String THRU_DATE = "thru_Date";
	
	/** The CRITERIA. */
	public static final String CRITERIA = "criteria";
	
	/** The CONDITION_ID. */
	public static final String CONDITION_ID = "condition_id";
	
	/** The VALUES. */
	public static final String VALUES = "values";
	
	/** The CODE. */
	public static final String CODE = "code";
	
	/** The PROMOTION_TYPE_ID. */
	public static final String PROMOTION_TYPE_ID = "promotion_type_id";
	
	/** The Constant MASTER_CRITERIA_QUANTITY_CONDITION_NAME. */
	public static final String MASTER_CRITERIA_QUANTITY_CONDITION_NAME = "QUANTITY";
	
	/** The Constant MASTER_CRITERIA_CART_ITEM_CONDITION_NAME. */
	public static final String MASTER_CRITERIA_CART_ITEM_CONDITION_NAME = "CART_ITEM";
	
	/** The Constant MASTER_CRITERIA_CART_VALUE_CONDITION_NAME. */
	public static final String MASTER_CRITERIA_CART_VALUE_CONDITION_NAME = "CART_VALUE";
	
	/** The Constant MASTER_CRITERIA_MONETARY_CONDITION_NAME. */
	public static final String MASTER_CRITERIA_MONETARY_CONDITION_NAME = "MONETARY";
	
	/** The Constant MASTER_CRITERIA_FREQUENCY_CONDITION_NAME. */
	public static final String MASTER_CRITERIA_FREQUENCY_CONDITION_NAME = "FREQUENCY";
	
	/** The Constant MASTER_CRITERIA_RECENCY_CONDITION_NAME. */
	public static final String MASTER_CRITERIA_RECENCY_CONDITION_NAME = "RECENCY";
	
	/** The Constant OPERATOR_BETWEEN. */
	public static final String OPERATOR_BETWEEN = "between";
	
	/** The Constant MAXIMUM_PAYLOAD. */
	public static final Integer MAXIMUM_PAYLOAD = 200000;
	
}