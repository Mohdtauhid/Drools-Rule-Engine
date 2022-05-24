package com.game.rule.service;

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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.game.rule.client.RuleManagerFactory;
import com.game.rule.client.dto.KbaseAttributeDTO;
import com.game.rule.client.entity.KbaseAttribute;
import com.game.rule.client.entity.RuleConfigEntity;
import com.game.rule.client.service.RulesService;
import com.game.rule.constants.RulesConfigConstant;
import com.game.rule.model.EventEntity;
import com.game.rule.model.GameEntity;

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

	public Optional<GameEntity> validateRule(GameEntity gameEntityDTO, String kbaseName) {
		log.info("Validate rule by {}", kbaseName);
		final Map<String, Object> map = new HashMap<>();
		map.put(RulesConfigConstant.RULE_NAME, gameEntityDTO);
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(kbaseName));
		try (var ruleManager = this.ruleManagerFactory.getRuleManager(query, kbaseName)) {
			ruleManager.executeAndDispose(new Object[] { gameEntityDTO }, Optional.of(map));
			return Optional.of(gameEntityDTO);
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

	public String createRulesForGames(GameEntity gameEntity) {
		log.info("Create rules for game");
		String ruleId = "rule_" + Instant.now().getEpochSecond();
		String rulesTemplate = createDRLFile(ruleId, gameEntity, gameEntity.getEvent());
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

	public void createKBaseAttribute(String ruleId) {
		ResponseEntity<KbaseAttribute> kbaseResponse = restTemplate.getForEntity(
				rulesConstant.getkBaseAttributeURL() + "/name/{kbaseName}", KbaseAttribute.class, "gameEngine");
		if (Objects.isNull(kbaseResponse.getBody())) {
			log.info("Create drl file for ruleId {}", ruleId);
			KbaseAttributeDTO kbase = new KbaseAttributeDTO();
			kbase.setCategory(rulesConstant.getCategory());
			kbase.setName("gameEngine");
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

	public String createDRLFile(String ruleId, GameEntity gameEntity, List<EventEntity> eventEntity) {
		try {
			return rulesService.createNewDRlFile(rulesConstant.getTemplateName(), ruleId, gameEntity, eventEntity);
		} catch (Exception exception) {
			log.info("RULE_ENGINE_TEMPLATE_CREATION_FAILS");
			log.error(exception.getMessage());
			return null;
		}
	}

}
