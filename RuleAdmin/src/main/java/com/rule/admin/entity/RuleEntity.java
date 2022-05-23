package com.rule.admin.entity;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonView;
import com.rule.admin.views.RulesView;

/**
 * The Class RuleEntity.
 */
@Document(collection = "rule")
public class RuleEntity extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -199778088895254246L;

	/** The id. */
	@JsonView(RulesView.CreateRulesView.class)
	@Id
	private String id;

	/** The name. */
	private String name;

	/** The path. */
	private String path;

	/** The definition. */
	private String definition;

	/** The definition type. */
	private String definition_type;

	/** The engine name. */
	private String engine_name;

	/** The packages. */
	private String packages;

	/**
	 * Instantiates a new rule entity.
	 */
	public RuleEntity() {
	}

	/**
	 * Instantiates a new rule entity.
	 *
	 * @param name           the name
	 * @param path           the path
	 * @param packages       the packages
	 * @param definition     the definition
	 * @param definitionType the definition type
	 * @param engineName     the engine name
	 * @param createdBy      the created by
	 * @param createdAt      the created at
	 */
	public RuleEntity(final String name, final String path, final String packages, final String definition,
			final String definitionType, final String engineName, final String createdBy, final Instant createdAt) {
		this.setName(name);
		this.setPath(path);
		this.setPackages(packages);
		this.setDefinition(definition);
		this.setDefinitionType(definitionType);
		this.setEngineName(engineName);
		this.setCreatedBy(createdBy);
		this.setCreatedAt(createdAt);
	}

	/**
	 * Gets the definition.
	 *
	 * @return the definition
	 */
	public String getDefinition() {
		return this.definition;
	}

	/**
	 * Gets the definition type.
	 *
	 * @return the definition type
	 */
	public String getDefinitionType() {
		return this.definition_type;
	}

	/**
	 * Gets the engine name.
	 *
	 * @return the engine name
	 */
	public String getEngineName() {
		return this.engine_name;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Sets the definition.
	 *
	 * @param definition the new definition
	 */
	public void setDefinition(final String definition) {
		this.definition = definition;
	}

	/**
	 * Sets the definition type.
	 *
	 * @param definitionType the new definition type
	 */
	public void setDefinitionType(final String definitionType) {
		this.definition_type = definitionType;
	}

	/**
	 * Sets the engine name.
	 *
	 * @param engineName the new engine name
	 */
	public void setEngineName(final String engineName) {
		this.engine_name = engineName;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(final String path) {
		this.path = path;
	}

	/**
	 * Gets the packages.
	 *
	 * @return the packages
	 */
	public String getPackages() {
		return packages;
	}

	/**
	 * Sets the packages.
	 *
	 * @param packages the new packages
	 */
	public void setPackages(String packages) {
		this.packages = packages;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

}
