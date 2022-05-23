package com.rule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rule.model.PromotionEntity;
import com.rule.model.RuleValidationDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PromotionService {

	@Autowired
	private RuleEngineService ruleEngineService;

	public PromotionEntity createPromotion(final PromotionEntity promotionEntity) {
		log.info("Creating new promotion");

		String ruleId = ruleEngineService.createRulesForPromotions(promotionEntity.getCriteria(),
				promotionEntity.getAction());
		log.info("ruleId {}", ruleId);
		return promotionEntity;
	}

	public RuleValidationDTO applyPromotion(RuleValidationDTO ruleValidationDTO, String ruleId) {
		log.info("Apply promotion {}", ruleValidationDTO);
		var validateRule = ruleEngineService.validateRule(ruleValidationDTO, ruleId);
		return validateRule.get();
	}

}