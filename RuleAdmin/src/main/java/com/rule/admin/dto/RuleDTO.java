package com.rule.admin.dto;

public class RuleDTO {

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

	/** The modified by. */
	private String modifiedBy;

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

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getPackages() {
		return packages;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(String packages) {
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

	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy the new modified by
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
