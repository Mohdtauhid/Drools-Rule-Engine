package com.rule.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rule.admin.entity.KbaseAttribute;

@Repository
public interface KbaseAttributeRepository extends MongoRepository<KbaseAttribute, String> {

	List<KbaseAttribute> findByCategory(String category);

	List<KbaseAttribute> findByCategoryIn(List<String> category);

	Optional<KbaseAttribute> findByName(String kbaseAttributeName);
}
