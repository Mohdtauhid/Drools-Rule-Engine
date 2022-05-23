package com.rule.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.rule.client.RuleManagerFactory;
import com.rule.client.dto.ActionDTO;
import com.rule.client.dto.CriteriaDTO;
import com.rule.client.dto.KbaseAttributeDTO;
import com.rule.client.dto.RangeDTO;
import com.rule.client.entity.KbaseAttribute;
import com.rule.client.entity.RuleConfigEntity;
import com.rule.client.service.RulesService;
import com.rule.constants.RulesConfigConstant;
import com.rule.model.ActionEntity;
import com.rule.model.AmountRangeEntity;
import com.rule.model.CriteriaEntity;
import com.rule.model.PriceEntity;
import com.rule.model.RuleValidationDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RuleEngineService {

	/** The rule manager factory. */
	@Autowired
	private RuleManagerFactory ruleManagerFactory;

	/** The RestTemplate to call other API. */
	@Autowired
	private RestTemplate restTemplate;

	/** The rulesConstant. */
	@Autowired
	private RulesConfigConstant rulesConstant;

	/** The rulesService. */
	@Autowired
	private RulesService rulesService;

	public Optional<RuleValidationDTO> validateRule(RuleValidationDTO ruleDTO, String kbaseName) {
		log.info("Validate rule by {}", kbaseName);
		final Map<String, Object> map = new HashMap<>();
		map.put(RulesConfigConstant.RULE_NAME, ruleDTO);
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(kbaseName));
		try (var ruleManager = this.ruleManagerFactory.getRuleManager(query, kbaseName)) {
			ruleManager.executeAndDispose(new Object[] { ruleDTO }, Optional.of(map));
			return Optional.of(ruleDTO);
		} catch (final Exception e) {
			log.error("Error in calculating discount {}", e.getMessage());
		}
		return Optional.empty();
	}

	public void deleteRule(String ruleId) {
		log.info("Delete rule by {}", ruleId);
		String deleteUriForRule = rulesConstant.getRulesAdminURI() + "/rule/" + ruleId;
		String deleteUrlForKBase = rulesConstant.getkBaseAttributeURL() + "/kBaseName/" + ruleId;
		try {
			restTemplate.delete(deleteUriForRule, ruleId);
			restTemplate.delete(deleteUrlForKBase, ruleId);
		} catch (RestClientException ex) {
			log.error("Error on delete rule for {} {}", ruleId, ex.getMessage());
		}
	}

	public String createRulesForPromotions(List<CriteriaEntity> criteriaEntityList, ActionEntity actionEntityList) {
		log.info("Create rules for promotions");
		String ruleId = "rule_" + Instant.now().getEpochSecond();
		List<CriteriaDTO> criteriaList = new ArrayList<>();
		if (!criteriaEntityList.isEmpty()) {
			criteriaEntityList.forEach(criteriaEntity -> {
				CriteriaDTO criteria = new CriteriaDTO();
				criteria.setConditionName(criteriaEntity.getConditionName());
				criteria.setOperator(criteriaEntity.getOperator());
				criteria.setValues(criteriaEntity.getValues());
				criteriaList.add(criteria);
			});
		}

		ActionDTO action = this.parseActionEntityForRuleCreations(actionEntityList);
		String rulesTemplate = createDRLFile(ruleId, criteriaList, action);
		createKBaseAttribute(ruleId);
		if (StringUtils.isNotBlank(rulesTemplate)) {
			try {
				RuleConfigEntity rulesConfig = new RuleConfigEntity();
				rulesConfig.setDefinitionType(RulesConfigConstant.DEFINITION_TYPE);
				rulesConfig.setEngineName(rulesConstant.getDrlENGINE());
				rulesConfig.setName(ruleId);
				rulesConfig.setUpdatedBy(RulesConfigConstant.MODIFIED_BY);
				rulesConfig.setUpdatedAt(new Date());
				rulesConfig.setCreatedAt(new Date());
				rulesConfig.setCreatedBy(RulesConfigConstant.CREATED_BY);
				rulesConfig.setPath(rulesConstant.getDrlPATh());
				rulesConfig.setDefinition(rulesTemplate);
				rulesConfig.setPackages(RulesConfigConstant.PACKAGE_NAME + ruleId);
				restTemplate.postForEntity(rulesConstant.getRulesAdminURI() + "/rule", rulesConfig, String.class);
				log.info("DRL Created Successfully");
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			log.error("Error occurred in Creating the file {}", ruleId);
			log.info("Failed to write the DRL File {}", ruleId);
		}
		return ruleId;
	}

	public ActionDTO parseActionEntityForRuleCreations(ActionEntity action) {
		log.info("Parse action entity for rule creations {} ", action);
		PriceEntity priceEntity = action.getPrice();
		ActionDTO actionForRule = new ActionDTO();
		actionForRule.setMaxCap(String.valueOf(action.getMaxCap()));
		actionForRule.setActionName(action.getActionName());
		actionForRule.setPriceName(priceEntity.getPriceName());
		actionForRule
				.setAmount(Objects.nonNull(priceEntity.getAmount()) ? String.valueOf(priceEntity.getAmount()) : null);
		actionForRule.setAmountOff(
				Objects.nonNull(priceEntity.getAmountOff()) ? String.valueOf(priceEntity.getAmountOff()) : null);

		if (!CollectionUtils.isEmpty(priceEntity.getAmountRange())) {
			actionForRule.setRange(mapPriceRange(priceEntity.getAmountRange()));
		} else if (!CollectionUtils.isEmpty(priceEntity.getValues())) {
			actionForRule.setValues(parseActionValues(priceEntity.getValues()));
		}
		return actionForRule;
	}

	public String parseActionValues(List<String> values) {
		log.info("Parse action values {}", values);
		StringBuilder valuesSb = new StringBuilder();
		valuesSb.append("[");
		values.forEach((value -> {
			valuesSb.append("'" + value + "',");
		}));
		valuesSb.deleteCharAt(valuesSb.length() - 1);
		valuesSb.append("]");
		return valuesSb.toString();
	}

	public List<RangeDTO> mapPriceRange(List<AmountRangeEntity> amountRangeEntitiy) {
		log.info("Map price range {}", amountRangeEntitiy);
		List<RangeDTO> rangeList = new ArrayList<>();
		amountRangeEntitiy.forEach(amountRange -> {
			RangeDTO range = new RangeDTO();
			range.setMaximumAmount(String.valueOf(amountRange.getMaximumAmount()));
			range.setMinimumAmount(String.valueOf(amountRange.getMinimumAmount()));
			if (Objects.nonNull(amountRange.getMaxCap())) {
				range.setMaxCap(amountRange.getMaxCap());
			}
			if (Objects.nonNull(amountRange.getAmountOff())) {
				range.setAmountOff(amountRange.getAmountOff());
			}
			rangeList.add(range);
		});
		return rangeList;
	}

	public void createKBaseAttribute(String ruleId) {
		ResponseEntity<KbaseAttribute> kbaseResponse = restTemplate.getForEntity(
				rulesConstant.getkBaseAttributeURL() + "/name/{kbaseName}", KbaseAttribute.class, "promotionEngine");
		if (Objects.isNull(kbaseResponse.getBody())) {
			log.info("Create drl file for ruleId {}", ruleId);
			KbaseAttributeDTO kbase = new KbaseAttributeDTO();
			kbase.setCategory(rulesConstant.getCategory());
			kbase.setName("promotionEngine");
			List<String> packages = new ArrayList<>();
			packages.add(RulesConfigConstant.PACKAGE_NAME + ruleId);
			kbase.setPackages(packages);
			kbase.setUpdatedBy(RulesConfigConstant.MODIFIED_BY);
			kbase.setUpdatedAt(new Date());
			kbase.setCreatedAt(new Date());
			kbase.setCreatedBy(RulesConfigConstant.CREATED_BY);
			restTemplate.postForEntity(rulesConstant.getkBaseAttributeURL(), kbase, KbaseAttribute.class);
			log.info("KBaseAttribute Added Successfully for ruleId {}", ruleId);
		} else {
			var kbase = kbaseResponse.getBody();
			kbase.getPackages().add(RulesConfigConstant.PACKAGE_NAME + ruleId);
			kbase.setPackages(kbase.getPackages());
			restTemplate.put(rulesConstant.getkBaseAttributeURL() + "/{kbaseId}", kbase, kbase.getId());
			log.info("All Packages Name :{}", kbase.getPackages());
			log.info("Update drl file for ruleId {}", ruleId);
		}

	}

	public String createDRLFile(String ruleId, List<CriteriaDTO> criterias, ActionDTO actions) {
		try {
			return rulesService.createNewDRlFile(rulesConstant.getTemplateName(), ruleId, criterias, actions);
		} catch (Exception exception) {
			log.info("RULE_ENGINE_TEMPLATE_CREATION_FAILS");
			log.error(exception.getMessage());
			return null;
		}
	}

	/**
	 * Function to Update the Existing Promotion Rule
	 * 
	 * @param promotionName
	 * @param criteriaEntityList
	 * @param actionEntityList
	 */
	public Boolean updateRulesForPromotion(String ruleId, List<CriteriaEntity> criteriaEntityList,
			ActionEntity actionEntityList) {
		log.info("Update rules for promotion {}", ruleId);
		List<CriteriaDTO> criteriaList = new ArrayList<>();
		if (!criteriaEntityList.isEmpty()) {
			criteriaEntityList.forEach(criteriaEntity -> {
				CriteriaDTO criteria = new CriteriaDTO();
				criteria.setConditionName(criteriaEntity.getConditionName());
				criteria.setOperator(criteriaEntity.getOperator());
				criteria.setValues(criteriaEntity.getValues());
				criteriaList.add(criteria);
			});
		}

		// Parse and set the Action List
		ActionDTO action = this.parseActionEntityForRuleCreations(actionEntityList);
		String rulesTemplate = createDRLFile(ruleId, criteriaList, action);
		String urlToFetchRuleConfig = rulesConstant.getRulesAdminURI() + "/rule/" + ruleId;
		// find the existing rule object
		ResponseEntity<RuleConfigEntity> fetchedRuleConfig = restTemplate.getForEntity(urlToFetchRuleConfig,
				RuleConfigEntity.class);
		if (StringUtils.isNotBlank(rulesTemplate)) {
			try {
				RuleConfigEntity rulesConfig = fetchedRuleConfig.getBody();
				rulesConfig.setUpdatedAt(new Date());
				rulesConfig.setDefinition(rulesTemplate);
				restTemplate.put(urlToFetchRuleConfig, rulesConfig);
				log.info("DRL Updated Successfully for ruleId {}", ruleId);
				return true;
			} catch (Exception e) {
				log.error(e.getMessage());
				return false;
			}
		} else {
			log.error("Error occurred in Updating the drl file {}", ruleId);
			log.info("Failed to write the DRL File {}", ruleId);
		}
		return false;
	}
}
