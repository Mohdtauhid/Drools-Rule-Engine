package com.rule.client.drools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rule.client.RuleManager;
import com.rule.client.RuleManagerFactory;
import com.rule.client.dto.KbaseAttributeDTO;
import com.rule.client.dto.RuleDTO;
import com.rule.client.repository.KbaseAttributeTemplateImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DroolsRuleManagerFactory extends RuleManagerFactory {

	/** The Constant RULE_WITH_HYPHEN. */
	private static final String RULE_WITH_HYPHEN = "rule-";

	/** The Constant DOT. */
	private static final String DOT = ".";

	/** The Constant TAB. */
	private static final String TAB = "\t";

	/** The Constant NEW_LINE. */
	private static final String NEW_LINE = "\n";

	/** The Constant FORWARD_SLASH. */
	private static final String FORWARD_SLASH = "/";

	/** The Constant URL_SUFFIX. */
	private static final String KBASE_URL_SUFFIX = "/v1/rules/kbaseAttributes/categories?categoryList={categoryList}";

	private static final String RULES_URL_SUFFIX = "/v1/rules/packages?packagesList={packagesList}";

	/**
	 * Builds the.
	 *
	 * @param categories    the categories
	 * @param ruleServerUrl the rule server url
	 * @param restTemplate  the rest template
	 * @return the rule manager factory
	 */
	public static RuleManagerFactory build(final String[] categories, final String ruleServerUrl,
			final RestTemplate restTemplate, final KbaseAttributeTemplateImpl kAttributeTemplateImpl) {
		log.info("build : {}", new DroolsRuleManagerFactory(categories, ruleServerUrl,
				newKieContainer(categories, ruleServerUrl, restTemplate), restTemplate, kAttributeTemplateImpl));
		return new DroolsRuleManagerFactory(categories, ruleServerUrl,
				newKieContainer(categories, ruleServerUrl, restTemplate), restTemplate, kAttributeTemplateImpl);
	}

	/**
	 * Check results.
	 *
	 * @param results the results
	 */
	static void checkResults(final Results results) {
		System.out.println("checkResults : " + results);
		if (results.hasMessages(Message.Level.ERROR)) {
			final var list = results.getMessages(Message.Level.ERROR);
			final var builder = new StringBuilder();
			list.forEach(message -> {
				builder.append("Path: ").append(message.getPath()).append(TAB).append("Line: ")
						.append(message.getLine()).append(TAB).append("Text: ").append(message.getText())
						.append(NEW_LINE);
			});
			throw new IllegalArgumentException("Unable to build rules - \n" + builder.toString());
		}
	}

	/**
	 * Gets the rule path.
	 *
	 * @param entity the entity
	 * @return the rule path
	 */
	private static String getRulePath(final RuleDTO entity) {
		log.info("getRulePath : {}",
				new StringBuilder().append(entity.getPath()).append(FORWARD_SLASH).append(RULE_WITH_HYPHEN)
						.append(entity.getId()).append(DOT).append(entity.getDefinitionType()).toString());
		log.info("entity : {}", entity);
		return new StringBuilder().append(entity.getPath()).append(FORWARD_SLASH).append(RULE_WITH_HYPHEN)
				.append(entity.getId()).append(DOT).append(entity.getDefinitionType()).toString();
	}

	/**
	 * New kie container.
	 *
	 * @param ruleEntityList the rule entity list
	 * @return the kie container
	 */
	private static KieContainer newKieContainer(final List<RuleDTO> ruleEntityList,
			final List<KbaseAttributeDTO> kbaseAttributeDTOs) {
		log.info("newKieContainer2 : {} {}", ruleEntityList, kbaseAttributeDTOs);
		final var kieServices = KieServices.Factory.get();
		final var kieFileSystem = kieServices.newKieFileSystem();
		String kieModuleXml = getKbaseModuleXml(kbaseAttributeDTOs, kieServices);

		kieFileSystem.writeKModuleXML(kieModuleXml);

		ruleEntityList.forEach(ruleEntity -> {
			final var path = getRulePath(ruleEntity);
			kieFileSystem.write(path, ruleEntity.getDefinition());
			System.out.println("Path " + path + "\nRuleEntity: " + ruleEntity.getDefinition());
		});
		final var kieBuilder = kieServices.newKieBuilder(kieFileSystem);
		kieBuilder.buildAll();

		checkResults(kieBuilder.getResults());

		final var kieModule = kieBuilder.getKieModule();
		return kieServices.newKieContainer(kieModule.getReleaseId());
	}

	/**
	 * New kie container.
	 *
	 * @param categories    the categories
	 * @param ruleServerUrl the rule server url
	 * @param restTemplate  the rest template
	 * @return the kie container
	 */
	private static KieContainer newKieContainer(final String[] categories, final String ruleServerUrl,
			final RestTemplate restTemplate) {
		log.info("newKieContainer3 : {}, {}, {}", categories, ruleServerUrl, restTemplate);
		List<KbaseAttributeDTO> kbaseAttributes = getKbaseAttributeList(categories, restTemplate, ruleServerUrl);
		String packages = getRulePackages(kbaseAttributes);
		List<RuleDTO> ruleEntityList = getRules(packages, ruleServerUrl, restTemplate);
		return newKieContainer(ruleEntityList, kbaseAttributes);
	}

	private static List<RuleDTO> getRules(final String packages, final String ruleServerUrl,
			final RestTemplate restTemplate) {
		log.info("getRules : {} {} {}", packages, ruleServerUrl, restTemplate);
		final ResponseEntity<String> response = restTemplate.exchange(ruleServerUrl + RULES_URL_SUFFIX, HttpMethod.GET,
				null, String.class, packages);
		final String ruleJson = response.getBody();
		final ObjectMapper objectMapper = new ObjectMapper();
		List<RuleDTO> ruleEntityList = new ArrayList<RuleDTO>();
		try {
			ruleEntityList = objectMapper.readValue(ruleJson, new TypeReference<>() {
			});
		} catch (JsonMappingException e) {
			log.error("Failed to map properties", e);
			ruleEntityList = new ArrayList<RuleDTO>();
		} catch (JsonProcessingException e) {
			log.error("Failed to process rules api", e);
			ruleEntityList = new ArrayList<RuleDTO>();
		}
		return ruleEntityList;
	}

	/** The kie container holder. */
	final KieContainerHolder kieContainerHolder;

	/**
	 * Instantiates a new drools rule manager factory.
	 *
	 * @param categories    the categories
	 * @param ruleServerUrl the rule server url
	 * @param kieContainer  the kie container
	 * @param restTemplate  the rest template
	 */
	private DroolsRuleManagerFactory(final String[] categories, final String ruleServerUrl,
			final KieContainer kieContainer, final RestTemplate restTemplate,
			final KbaseAttributeTemplateImpl kAttributeTemplateImpl) {
		log.info("DroolsRuleManagerFactory : {} {} {} {}", categories, ruleServerUrl, kieContainer, restTemplate);
		super.categories = categories;
		super.ruleServerUrl = ruleServerUrl;
		this.kieContainerHolder = new KieContainerHolder(kieContainer);
		super.restTemplate = restTemplate;
		super.kAttributeTemplateImpl = kAttributeTemplateImpl;

	}

	/**
	 * Gets the rule manager.
	 *
	 * @return the rule manager
	 */
	@Override
	public RuleManager getRuleManager(Query query, String kbase) {
		log.info("getRuleManager : {} {}", query, kbase);
		if (query != null) {
			String kbaseName = getKbaseFilterByAttribute(query, kAttributeTemplateImpl);
			return new DroolsRuleManager(this.kieContainerHolder.get().getKieBase(kbaseName).newKieSession());
		} else if (kbase != null) {
			return new DroolsRuleManager(this.kieContainerHolder.get().getKieBase(kbase).newKieSession());

		} else {
			return new DroolsRuleManager(this.kieContainerHolder.get().newKieSession());

		}

	}

	/**
	 * Refresh.
	 *
	 * @return the rule manager factory
	 */
	@Override
	public void refresh() {
		log.info("refresh");
		KieContainer kieContainer = newKieContainer(this.categories, this.ruleServerUrl, this.restTemplate);
		this.kieContainerHolder.set(kieContainer);
	}

	private static List<KbaseAttributeDTO> getKbaseAttributeList(String[] categories, RestTemplate restTemplate,
			String ruleServerUrl) {
		log.info("getKbaseAttributeList : {} {} {}", categories, restTemplate, ruleServerUrl);
		final ResponseEntity<String> response = restTemplate.exchange(ruleServerUrl + KBASE_URL_SUFFIX, HttpMethod.GET,
				null, String.class, String.join(",", categories));
		final String kbaseJson = response.getBody();
		final ObjectMapper objectMapper = new ObjectMapper();
		List<KbaseAttributeDTO> kbaseAttributeList = new ArrayList<KbaseAttributeDTO>();
		try {
			kbaseAttributeList = objectMapper.readValue(kbaseJson, new TypeReference<>() {
			});
		} catch (JsonMappingException e) {
			log.error("Failed to map properties", e);
			kbaseAttributeList = new ArrayList<KbaseAttributeDTO>();
		} catch (JsonProcessingException e) {
			log.error("Failed to process rules api", e);
			kbaseAttributeList = new ArrayList<KbaseAttributeDTO>();
		}
		return kbaseAttributeList;
	}

	private static String getKbaseModuleXml(List<KbaseAttributeDTO> kbaseAttributeDTOs, KieServices kieServices) {
		log.info("getKbaseModuleXml : {} {}", kbaseAttributeDTOs, kieServices);
		KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
		String kModuleXml = null;
		Optional.ofNullable(kbaseAttributeDTOs).orElse(Collections.emptyList()).forEach(kbaseAttribute -> {
			kieModuleModel.newKieBaseModel(kbaseAttribute.getName()).setDefault(false)
					.setEqualsBehavior(EqualityBehaviorOption.EQUALITY)
					.setEventProcessingMode(EventProcessingOption.STREAM)
					.addPackage(String.join(",", kbaseAttribute.getPackages()))
					.newKieSessionModel(kbaseAttribute.getName() + "session").setDefault(false)
					.setType(KieSessionModel.KieSessionType.STATEFUL).setClockType(ClockTypeOption.get("realtime"));

		});
		kModuleXml = kieModuleModel.toXML();
		return kModuleXml;
	}

	private static String getRulePackages(List<KbaseAttributeDTO> kbaseAttributeDTOs) {
		log.info("getRulePackages : {}", kbaseAttributeDTOs);
		List<String> packages = new ArrayList<>();
		String pakagesString = null;
		kbaseAttributeDTOs.forEach(kbaseAttribute -> {
			packages.addAll(kbaseAttribute.getPackages());
		});
		if (!packages.isEmpty()) {
			pakagesString = String.join(",", packages);
		}
		log.info("Pakages String : {}", pakagesString);
		return pakagesString;
	}

	private static String getKbaseFilterByAttribute(Query query, KbaseAttributeTemplateImpl kAttributeTemplateImpl) {
		log.info("getKbaseFilterByAttribute : {} {}", query, kAttributeTemplateImpl);
		var kbaseAttribute = kAttributeTemplateImpl.findByKbaseAttributes(query);
		log.info("getKbaseFilterByAttribute : {}", kbaseAttribute.getName());
		return kbaseAttribute.getName();
	}

}
