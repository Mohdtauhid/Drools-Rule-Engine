/*
 *
 */
package com.rule.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rule.admin.entity.RuleEntity;

@Repository
public interface RuleRepository extends MongoRepository<RuleEntity, String> {

	/**
	 * Find by category.
	 *
	 * @param category the category
	 * @return the list
	 */
	List<RuleEntity> findByPackages(String packages);

	/**
	 * Find by category in.
	 *
	 * @param categoryList the category list
	 * @return the list
	 */
	List<RuleEntity> findByPackagesIn(List<String> packagesList);

	/**
	 * Find by name.
	 *
	 * @param name of the rule
	 * @return the RuleEntity
	 */
	Optional<RuleEntity> findByName(String name);

	/**
	 * Delete by name
	 * 
	 * @param ruleName
	 */
	void deleteByName(String ruleName);
}
