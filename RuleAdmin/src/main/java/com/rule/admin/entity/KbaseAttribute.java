package com.rule.admin.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "kbase_attribute")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KbaseAttribute extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7873940660642803320L;

	/** The id. */
	@Id
	private String id;

	/** The name. */
	private String name;

	/** The category. */
	private String category;

	/** The packages. */
	private List<String> packages;

	/** The attributes. */
	private Map<String, String> attributes;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
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
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Gets the packages.
	 *
	 * @return the packages
	 */
	public List<String> getPackages() {
		return packages;
	}

	/**
	 * Sets the packages.
	 *
	 * @param packages the new packages
	 */
	public void setPackages(List<String> packages) {
		this.packages = packages;
	}

	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * Sets the attributes.
	 *
	 * @param attributes the attributes
	 */
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

}
