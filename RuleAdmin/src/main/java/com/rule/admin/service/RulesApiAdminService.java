/*
 *
 */
package com.rule.admin.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rule.admin.dto.RuleDTO;
import com.rule.admin.entity.RuleEntity;
import com.rule.admin.event.RuleEvent;
import com.rule.admin.message.RulesMessagePublisher;
import com.rule.admin.repository.RuleRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class RulesApiAdminService.
 */
@Service
@Slf4j
public class RulesApiAdminService {

	/** The rules repository. */
	@Autowired
	private RuleRepository rulesRepository;

	/** The redis publisher. */
	@Autowired
	private RulesMessagePublisher redisPublisher;

	/**
	 * Creates the rule.
	 *
	 * @param ruleEntity the rule entity
	 * @param definition the definition
	 */
	public RuleEntity createRule(final RuleEntity ruleEntity, final String definition) {
		ruleEntity.setDefinition(definition);
		final var result = this.rulesRepository.save(ruleEntity);
		final var ruleEvent = new RuleEvent(result.getId(), result.getName(), result.getPackages(), "create rule");
		this.redisPublisher.publish(ruleEvent.toString());
		log.info("{} Rule Created", result.getName());
		return result;
	}

	/**
	 * Delete rule.
	 *
	 * @param id the id
	 */
	public void deleteRule(final String id) {
		this.rulesRepository.deleteById(id);
		final var ruleEvent = new RuleEvent(id, null, null, "Delete Rule");
		this.redisPublisher.publish(ruleEvent.toString());
		log.info("Rule deleted successfully {}");

	}

	/**
	 * Gets the all rules.
	 *
	 * @return the all rules
	 */
	public Page<RuleEntity> getAllRules(Pageable pageable) {
		return this.rulesRepository.findAll(pageable);
	}

	/**
	 * Gets the rule.
	 *
	 * @param id the id
	 * @return the rule
	 */
	public Optional<RuleEntity> getRule(final String id) {
		return this.rulesRepository.findById(id);
	}

	/**
	 * Update rule definition.
	 *
	 * @param id         the id
	 * @param definition the definition
	 */
	public void updateRuleDefinition(final String id, final String definition, RuleDTO ruleDto) {
		final var existingRuleEntity = this.rulesRepository.findById(id);
		if (!existingRuleEntity.isEmpty()) {
			final var updatedRuleEntity = existingRuleEntity.get();
			updatedRuleEntity.setDefinition(definition);
			updatedRuleEntity.setPackages(ruleDto.getPackages());
			updatedRuleEntity.setDefinitionType(ruleDto.getDefinitionType());
			updatedRuleEntity.setEngineName(ruleDto.getEngineName());
			updatedRuleEntity.setName(ruleDto.getName());
			updatedRuleEntity.setPath(ruleDto.getPath());
			updatedRuleEntity.setUpdatedAt(Instant.now());
			updatedRuleEntity.setUpdatedBy(ruleDto.getModifiedBy());
			this.rulesRepository.save(updatedRuleEntity);
			final var ruleEvent = new RuleEvent(updatedRuleEntity.getId(), updatedRuleEntity.getName(),
					updatedRuleEntity.getPackages(), "update rule");
			this.redisPublisher.publish(ruleEvent.toString());
			log.info("{} Rule updated successfully", updatedRuleEntity.getName());

		} else {
			log.error("Rule not found");
		}
	}

	public List<RuleEntity> getRulesByPackages(List<String> packagesList) {
		return this.rulesRepository.findByPackagesIn(packagesList);
	}

	public void createDynamicRule(RuleEntity ruleEntity) {
		ruleEntity.setCreatedAt(Instant.now());
		final var result = this.rulesRepository.save(ruleEntity);
		final var ruleEvent = new RuleEvent(result.getId(), result.getName(), result.getPackages(), "create rule");
		this.redisPublisher.publish(ruleEvent.toString());
	}

	/**
	 * Update rule definition.
	 *
	 * @param ruleName the id
	 * @param ruleDto  the rule object
	 */
	public void updateRuleDefinitionByName(String ruleName, RuleDTO ruleDto) {
		final var existingRuleEntity = this.rulesRepository.findByName(ruleName);
		if (!existingRuleEntity.isEmpty()) {
			final var updatedRuleEntity = existingRuleEntity.get();
			updatedRuleEntity.setDefinition(ruleDto.getDefinition());
			updatedRuleEntity.setPackages(ruleDto.getPackages());
			updatedRuleEntity.setDefinitionType(ruleDto.getDefinitionType());
			updatedRuleEntity.setEngineName(ruleDto.getEngineName());
			updatedRuleEntity.setName(ruleDto.getName());
			updatedRuleEntity.setPath(ruleDto.getPath());
			updatedRuleEntity.setUpdatedAt(Instant.now());
			updatedRuleEntity.setUpdatedBy(ruleDto.getModifiedBy());
			this.rulesRepository.save(updatedRuleEntity);
			final var ruleEvent = new RuleEvent(updatedRuleEntity.getId(), updatedRuleEntity.getName(),
					updatedRuleEntity.getPackages(), "update rule");
			this.redisPublisher.publish(ruleEvent.toString());
			log.info("{} Rule updated successfully", updatedRuleEntity.getName());
		} else {
			log.error("Rule not found");
		}
	}

	/**
	 * Delete rule.
	 *
	 * @param ruleName the name
	 */
	public void deleteByRuleName(final String ruleName) {
		this.rulesRepository.deleteByName(ruleName);
		final var ruleEvent = new RuleEvent(ruleName, null, null, "Delete Rule");
		this.redisPublisher.publish(ruleEvent.toString());
		log.info("Rule deleted successfully {}", ruleName);
	}

	/**
	 * Gets the rule.
	 *
	 * @param name
	 * @return the rule
	 */
	public Optional<RuleEntity> getRuleByName(final String name) {
		return this.rulesRepository.findByName(name);
	}

}
