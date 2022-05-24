package com.game.rule.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class EventEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String type;

	private String description;

	private Integer sequence;

}
