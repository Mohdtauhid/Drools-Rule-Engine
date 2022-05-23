package com.rule.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Class CriteriaEntity.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CriteriaEntity implements Serializable {

	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** The condition id. */
	@ApiModelProperty(notes = "Condition Id")
	@Field("condition_id")
	@NotBlank(message = "{criteria.condition_id_blank}")
	private String conditionId;

	/** The condition name. */
	@ApiModelProperty(notes = "Condition Name")
	@Field("condition_name")
	private String conditionName;

	/** The operator. */
	@ApiModelProperty(notes = "Operator")
	@Field("operator")
	@NotBlank(message = "{criteria.operator_blank}")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "{criteria.operator_not_valid}")
	private String operator;

	/** The values. */
	@ApiModelProperty(notes = "Values")
	@NotEmpty(message = "{criteria.values_empty}")
	@Field("values")
	private List<@NotBlank(message = "{criteria.values_blank}") @Pattern(regexp = "[a-zA-Z0-9 _.',:()-]*$", message = "{criteria.values_not_valid}") String> values;

	/** The parent id. */
	@ApiModelProperty(notes = "Parent Id")
	@Field("parent_id")
	private String parentId;

	/** The parent value. */
	@ApiModelProperty(notes = "Parent Value")
	@Field("parent_value")
	private String parentValue;

}
