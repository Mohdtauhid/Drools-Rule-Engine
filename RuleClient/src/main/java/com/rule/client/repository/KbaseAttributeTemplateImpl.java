package com.rule.client.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.rule.client.entity.KbaseAttribute;

@Repository
public class KbaseAttributeTemplateImpl implements KbaseAttributeTemplate {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public KbaseAttribute findByKbaseAttributes(Query query) {
		return mongoTemplate.findOne(query, KbaseAttribute.class);
	}

}
