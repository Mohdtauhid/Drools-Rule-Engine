package com.game.rule.client.repository;

import org.springframework.data.mongodb.core.query.Query;

import com.game.rule.client.entity.KbaseAttribute;

public interface KbaseAttributeTemplate {
	KbaseAttribute findByKbaseAttributes(Query criteria);
}
