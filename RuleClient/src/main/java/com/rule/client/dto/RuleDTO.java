package com.rule.client.dto;

import java.util.Date;

/**
 * The Class RuleDTO.
 */
public class RuleDTO {

	/** The id. */
	private String id;

	/** The name. */
	private String name;

	/** The path. */
	private String path;

	/** The category. */
	private String packages;

	/** The definition. */
	private String definition;

	/** The definition type. */
	private String definitionType;

	/** The engine name. */
	private String engineName;

	private Integer version;

	/** The created by. */
	private String createdBy;

	/** The modified by. */
	private String updatedBy;

	/** The created at. */
	private Date createdAt;

	/** The modified at. */
	private Date updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	/**
	 * Gets the definition.
	 *
	 * @return the definition
	 */
	public String getDefinition() {
		return definition;
	}

	/**
	 * Sets the definition.
	 *
	 * @param definition the new definition
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 * Gets the definition type.
	 *
	 * @return the definition type
	 */
	public String getDefinitionType() {
		return definitionType;
	}

	/**
	 * Sets the definition type.
	 *
	 * @param definitionType the new definition type
	 */
	public void setDefinitionType(String definitionType) {
		this.definitionType = definitionType;
	}

	/**
	 * Gets the engine name.
	 *
	 * @return the engine name
	 */
	public String getEngineName() {
		return engineName;
	}

	/**
	 * Sets the engine name.
	 *
	 * @param engineName the new engine name
	 */
	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
