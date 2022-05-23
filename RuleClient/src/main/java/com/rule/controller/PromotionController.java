package com.rule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rule.model.PromotionEntity;
import com.rule.model.RuleValidationDTO;
import com.rule.service.PromotionService;

@RestController
@RequestMapping(value = "/v1/rules")
public class PromotionController {

	@Autowired
	private PromotionService promotionService;

	@PostMapping(value = "/promotions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PromotionEntity> createRule(@RequestBody final PromotionEntity promotionEntity) {
		final var promotion = promotionService.createPromotion(promotionEntity);
		return new ResponseEntity<>(promotion, HttpStatus.CREATED);
	}

	@PostMapping(value = "/promotions/{ruleId}/apply", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RuleValidationDTO> applyPromotion(@RequestBody final RuleValidationDTO ruleValidationDTO,
			@PathVariable("ruleId") final String ruleId) {
		final var promotionResponse = this.promotionService.applyPromotion(ruleValidationDTO, ruleId);
		return new ResponseEntity<>(promotionResponse, HttpStatus.OK);
	}
}