package com.rule.client.repository;

import org.springframework.data.mongodb.core.query.Query;

import com.rule.client.entity.KbaseAttribute;

public interface KbaseAttributeTemplate {
	KbaseAttribute findByKbaseAttributes(Query criteria);
}
