package com.game.rule.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RulesConfigConstant {

	/** The rules admin uri. */
	@Value("${rules.configurations.rulesAdminUrl}")
	private String rulesAdminURI;

	/** The kbase attribute url. */
	@Value("${rules.configurations.kBaseAttributeURL}")
	private String kBaseAttributeURL;

	/** The drl path. */
	@Value("${rules.configurations.path}")
	private String drlPATh;

	/** The drl engine. */
	@Value("${rules.configurations.engine}")
	private String drlENGINE;

	/** The category. */
	@Value("${rulesapi.categories}")
	private String category;

	/** The template name. */
	@Value("${rulesapi.templatename}")
	private String templateName;

	/** The rule name. */
	public static final String RULE_NAME = "ruleGame";

	/** The PERCENTAGE_OFF */
	public static final String PERCENTAGE_OFF = "PERCENTAGE_OFF";

	/** The FLAT_OFF */
	public static final String FLAT_OFF = "FLAT_OFF";

	/** The FREE */
	public static final String FREE = "FREE";

	/** The CUSTOM */
	public static final String CUSTOM = "CUSTOM";

	/** The ADDITIONAL_CHARGES */
	public static final String ADDITIONAL_CHARGES = "ADDITIONAL_CHARGES";

	/** The PRODUCT_PRICE */
	public static final String PRODUCT_PRICE = "PRODUCT_PRICE";

	/** The SHIPPING_METHOD */
	public static final String SHIPPING_METHOD = "SHIPPING_METHOD";

	/** The ADDONS */
	public static final String ADDONS = "ADDONS";

	/** The CART */
	public static final String CART = "CART_TOTAL";

	/** The WITH */
	public static final String WITH = " with ";

	/** The AND */
	public static final String AND = " AND ";

	/** The DISCOUNT */
	public static final String DISCOUNT = "discount";

	/** The MAX_VALUE */
	public static final String MAX_VALUE = "max_value";

	/** The PACKAGE_NAME */
	public static final String PACKAGE_NAME = "game";

	/** The DEFINITION_TYPE */
	public static final String DEFINITION_TYPE = "drl";

	/** The CREATED_BY */
	public static final String CREATED_BY = "admin";

	/** The MODIFIED_BY */
	public static final String MODIFIED_BY = "admin";

	/** The CONVENIENCE */
	public static final String CONVENIENCE = "CONVENIENCE";

	/** The FREQUENCY */
	public static final String FREQUENCY = "FREQUENCY";

	/** The RECENCY */
	public static final String RECENCY = "RECENCY";

	/** The MONETARY */
	public static final String MONETARY = "MONETARY";

	/** The DELIVERY_COUNTRY */
	public static final String DELIVERY_COUNTRY = "DELIVERY_COUNTRY";

	/** The PRESENT */
	public static final String PRESENT = "Present";

	/** The ABSENT */
	public static final String ABSENT = "Absent";

	/**
	 * @return String
	 */
	public String getRulesAdminURI() {
		return rulesAdminURI;
	}

	/**
	 * @param rulesAdminURI String
	 */
	public void setRulesAdminURI(String rulesAdminURI) {
		this.rulesAdminURI = rulesAdminURI;
	}

	/**
	 * @return String
	 */
	public String getDrlPATh() {
		return drlPATh;
	}

	/**
	 * @param drlPATh String
	 */
	public void setDrlPATh(String drlPATh) {
		this.drlPATh = drlPATh;
	}

	/**
	 * @return String
	 */
	public String getDrlENGINE() {
		return drlENGINE;
	}

	/**
	 * @param drlENGINE String
	 */
	public void setDrlENGINE(String drlENGINE) {
		this.drlENGINE = drlENGINE;
	}

	/**
	 * @return String
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @return String
	 */
	public String getkBaseAttributeURL() {
		return kBaseAttributeURL;
	}

	/**
	 * @param kBaseAttributeURL String
	 */
	public void setkBaseAttributeURL(String kBaseAttributeURL) {
		this.kBaseAttributeURL = kBaseAttributeURL;
	}

	/**
	 * @return String
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName String
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

}