/*
 *
 */
package com.game.rule.client;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.client.RestTemplate;

import com.game.rule.client.drools.DroolsRuleManagerFactory;
import com.game.rule.client.repository.KbaseAttributeTemplateImpl;

public abstract class RuleManagerFactory {

	/**
	 * Builds the.
	 *
	 * @param categories             the categories
	 * @param ruleServerUrl          the rule server url
	 * @param restTemplate           the rest template
	 * @param kAttributeTemplateImpl the k attribute template impl
	 * @return the rule manager factory
	 */
	public static RuleManagerFactory build(final String categories[], final String ruleServerUrl,
			final RestTemplate restTemplate, KbaseAttributeTemplateImpl kAttributeTemplateImpl) {
		return DroolsRuleManagerFactory.build(categories, ruleServerUrl, restTemplate, kAttributeTemplateImpl);
	}

	/** The categories. */
	protected String[] categories;

	/** The rule server url. */
	protected String ruleServerUrl;

	/** The rest template. */
	protected RestTemplate restTemplate;

	/** The k attribute template impl. */
	protected KbaseAttributeTemplateImpl kAttributeTemplateImpl;

	/**
	 * Gets the rule manager.
	 *
	 * @param query the query
	 * @param kbase the kbase
	 * @return the rule manager
	 */
	public abstract RuleManager getRuleManager(Query query, String kbase);

	/**
	 * Refresh.
	 *
	 * @return the rule manager factory
	 */
	public abstract void refresh();
}
