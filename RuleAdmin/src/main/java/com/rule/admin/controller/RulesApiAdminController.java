package com.rule.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.rule.admin.dto.PageDetail;
import com.rule.admin.dto.RuleDTO;
import com.rule.admin.entity.RuleEntity;
import com.rule.admin.service.RulesApiAdminService;
import com.rule.admin.util.SortOrderUtil;
import com.rule.admin.views.RulesView;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class RulesApiAdminController.
 */
@RestController
@RequestMapping("/v1/rules")
@Slf4j
public class RulesApiAdminController {

	/** The rules api admin service. */
	@Autowired
	private RulesApiAdminService rulesApiAdminService;

	/**
	 * Creates the rule.
	 *
	 * @param ruleFile   the rule file
	 * @param ruleEntity the rule entity
	 * @return the response entity
	 */
	@JsonView(RulesView.CreateRulesView.class)
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = "application/json")
	public ResponseEntity<RuleEntity> createRule(@RequestPart("file") final MultipartFile ruleFile,
			@RequestPart("ruleConfig") final RuleEntity ruleEntity) {
		log.debug("Rule is in creation state" + ruleEntity);
		String content;
		try {
			content = new String(ruleFile.getBytes());
		} catch (final IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to read rule file", e);
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.rulesApiAdminService.createRule(ruleEntity, content));
	}

	/**
	 * Delete rule.
	 *
	 * @param ruleId the rule id
	 * @return the response entity
	 */
	@RequestMapping(value = "/{ruleId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> deleteRule(@PathVariable final String ruleId) {
		this.rulesApiAdminService.deleteRule(ruleId);
		return new ResponseEntity<>("Rule deleted successfully", HttpStatus.OK);
	}

	/**
	 * Gets the all rules.
	 *
	 * @return the all rules
	 */
	@GetMapping(value = "/", produces = "application/json")
	public PageDetail<RuleEntity> getAllRules(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size,
			@RequestParam(name = "sortParam", defaultValue = "name:asc", required = false) final String sortParam) {
		List<Order> orders = SortOrderUtil.getSortOrder(sortParam);
		Pageable paging = PageRequest.of(page, size, Sort.by(orders));
		Page<RuleEntity> rules = this.rulesApiAdminService.getAllRules(paging);
		return new PageDetail<>(rules);
	}

	/**
	 * Gets the rule.
	 *
	 * @param ruleId the rule id
	 * @return the rule
	 */
	@RequestMapping(value = "/{ruleId}", method = RequestMethod.GET, produces = "application/json")
	public Optional<RuleEntity> getRule(@PathVariable final String ruleId) {
		return this.rulesApiAdminService.getRule(ruleId);
	}

	/**
	 * Update rule definition.
	 *
	 * @param ruleId   the rule id
	 * @param ruleFile the rule file
	 * @return the response entity
	 */
	@RequestMapping(value = "/{ruleId}", method = RequestMethod.PUT, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = "application/json")
	public ResponseEntity<String> updateRuleDefinition(@PathVariable final String ruleId,
			@RequestPart("file") final MultipartFile ruleFile, @RequestPart("ruleConfig") final RuleDTO ruleDto) {
		String content;
		try {
			content = new String(ruleFile.getBytes());
		} catch (final IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to read rule file", e);
		}
		this.rulesApiAdminService.updateRuleDefinition(ruleId, content, ruleDto);
		return new ResponseEntity<>("Rules updated successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/packages", method = RequestMethod.GET, produces = "application/json")
	public List<RuleEntity> getRulesByPackages(
			@RequestParam @NotEmpty(message = "Input Packages list cannot be empty.") List<@Valid String> packagesList) {
		return this.rulesApiAdminService.getRulesByPackages(packagesList);
	}

	/**
	 * Creates the rule.
	 *
	 * @param ruleEntity the rule entity
	 * @return the response entity
	 */
	@RequestMapping(value = "/rule", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> createDynamicRules(@RequestBody final RuleEntity ruleEntity) {
		log.debug("Rule is in creation state" + ruleEntity);
		this.rulesApiAdminService.createDynamicRule(ruleEntity);
		return new ResponseEntity<>("Rules Created successfully", HttpStatus.OK);
	}

	/**
	 * Update rule definition by Name.
	 *
	 * @param ruleName the rule id
	 * @return the response entity
	 */
	@RequestMapping(value = "/rule/{ruleName}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> updateRuleDefinitionByName(@PathVariable final String ruleName,
			@RequestBody final RuleDTO ruleDto) {

		this.rulesApiAdminService.updateRuleDefinitionByName(ruleName, ruleDto);
		return new ResponseEntity<>("Rules updated successfully", HttpStatus.OK);
	}

	/**
	 * Delete rule.
	 *
	 * @param ruleName the rule name
	 * @return the response entity
	 */
	@RequestMapping(value = "/rule/{ruleName}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> deleteRuleByName(@PathVariable final String ruleName) {
		this.rulesApiAdminService.deleteByRuleName(ruleName);
		return new ResponseEntity<>("Rule deleted successfully", HttpStatus.OK);
	}

	/**
	 * Gets the rule.
	 *
	 * @param ruleName the rule name
	 * @return the rule
	 */
	@RequestMapping(value = "/rule/{ruleName}", method = RequestMethod.GET, produces = "application/json")
	public Optional<RuleEntity> getRuleByName(@PathVariable final String ruleName) {
		return this.rulesApiAdminService.getRuleByName(ruleName);
	}

}
