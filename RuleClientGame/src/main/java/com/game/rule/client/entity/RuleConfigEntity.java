package com.game.rule.client.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "rule")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RuleConfigEntity {

	/** The id. */
	@Id
	private String id;

	/** The name. */
	private String name;

	/** The path. */
	private String path;

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

	/** The Package used */
	private String packages;

}
