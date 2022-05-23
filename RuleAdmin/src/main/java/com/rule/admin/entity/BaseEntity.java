package com.rule.admin.entity;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Field;

import com.rule.admin.util.Constants;

public class BaseEntity implements Serializable {

	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** The created by. */
	@Field(name = Constants.CREATED_BY)
	protected String createdBy;

	/** The created at. */
	@Field(name = Constants.CREATED_AT)
	protected Instant createdAt;

	/** The modified by. */
	@Field(name = Constants.UPDATED_BY)
	protected String updatedBy;

	/** The modified at. */
	@Field(name = Constants.UPDATED_AT)
	protected Instant updatedAt;

	/** The version. */
	@Version
	public Integer version;

	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public Instant getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt the new created at
	 */
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * Sets the modified by.
	 *
	 * @param updatedBy the new updated by
	 */
	public void setUpdatedBy(final String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Gets the updated at.
	 *
	 * @return the updated at
	 */
	public Instant getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * Sets the updated at.
	 *
	 * @param updatedAt the new updated at
	 */
	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

}
