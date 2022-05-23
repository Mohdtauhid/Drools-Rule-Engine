package com.rule.client.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rule.client.entity.KbaseAttribute;

@Repository
public interface KbaseAttributeRepository extends MongoRepository<KbaseAttribute, String>, KbaseAttributeTemplate {
}
