package com.rule.admin.event;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("RuleEvent")
public class RuleEvent {

	/** The id. */
	private String id;

	/** The name. */
	private String name;

	/** The category. */
	private String category;

	/** The action. */
	private String action;

	/**
	 * Instantiates a new rule event.
	 *
	 * @param id       the id
	 * @param name     the name
	 * @param category the category
	 * @param action   the action
	 */
	public RuleEvent(final String id, final String name, final String category, final String action) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.action = action;
	}

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public String getAction() {
		return this.action;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return this.category;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return this.id;
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
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	public void setAction(final String action) {
		this.action = action;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(final String category) {
		this.category = category;
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "RuleEvent [id=" + this.id + ", name=" + this.name + ", category=" + this.category + ", action="
				+ this.action + "]";
	}

}
