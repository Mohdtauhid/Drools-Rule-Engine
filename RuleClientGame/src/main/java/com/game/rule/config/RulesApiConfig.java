package com.game.rule.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.game.rule.client.RuleManagerFactory;
import com.game.rule.client.repository.KbaseAttributeTemplateImpl;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ComponentScan("com.rule.client")
@Slf4j
public class RulesApiConfig {

	/** The categories. */
	@Value("${rulesapi.categories}")
	private String[] categories;

	/** The rulesAdminServerUrl. */
	@Value("${rules.configurations.rulesAdminServerUrl}")
	private String rulesAdminServerUrl;

	/**
	 * Rule manager factory.
	 *
	 * @param ruleRepository the rule repository
	 * @return the rule manager factory
	 */
	@Bean
	public RuleManagerFactory ruleManagerFactory(KbaseAttributeTemplateImpl kbaseAttributeTemplateImpl) {
		RestTemplate restTemplate = new RestTemplate();
		log.info("Inside RulesApiConfig");
		return RuleManagerFactory.build(this.categories, this.rulesAdminServerUrl, restTemplate,
				kbaseAttributeTemplateImpl);
	}
}