package com.rule.client.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.rule.client.dto.ActionDTO;
import com.rule.client.dto.CriteriaDTO;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RulesService {

	private Configuration freemarkerConfig;

	@Autowired
	public RulesService(Configuration freemarkerConfig,
			@Value("${freemarker.template.path}") String defaulttemplatePath) {
		this.freemarkerConfig = freemarkerConfig;
		this.freemarkerConfig.setClassForTemplateLoading(this.getClass(), defaulttemplatePath);
	}

	/*
	 * Function to get the DRL String using template Required Params: 1. List of
	 * CriteriaDTO 2. List of ActionDTO
	 *
	 * Created on: 26 Oct 2021
	 */
	public String createNewDRlFile(String ruleTemplateName, String ruleName, List<CriteriaDTO> criteriaDTO,
			ActionDTO actionDTO) throws IOException, Exception {

		Template ruleTemplate = null;
		String ruleTemplateDRL = null;
		try {
			ruleTemplate = freemarkerConfig.getTemplate(ruleTemplateName);
		} catch (IOException ioException) {
			log.error(ioException.getMessage());
			throw ioException;
		}

		Map<String, Object> model = new LinkedHashMap<>();

		model.put("conditions", criteriaDTO);
		model.put("actions", actionDTO);
		model.put("ruleName", ruleName);

		try {
			ruleTemplateDRL = FreeMarkerTemplateUtils.processTemplateIntoString(ruleTemplate, model);
		} catch (IOException | TemplateException exception) {
			log.error(exception.getMessage());
			throw exception;
		}
		return ruleTemplateDRL;
	}

}
