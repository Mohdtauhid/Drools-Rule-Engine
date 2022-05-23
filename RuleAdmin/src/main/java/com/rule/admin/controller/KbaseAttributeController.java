package com.rule.admin.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rule.admin.dto.PageDetail;
import com.rule.admin.entity.KbaseAttribute;
import com.rule.admin.service.KbaseAttributeService;
import com.rule.admin.util.SortOrderUtil;

/**
 * The Class KbaseAttributeController.
 */
@RestController
@RequestMapping("/v1/rules")
public class KbaseAttributeController {

	/** The kbas attribute service. */
	@Autowired
	private KbaseAttributeService kbasAttributeService;

	/**
	 * Creates the kbase attribute.
	 *
	 * @param kbaseAttribute the kbase attribute
	 * @return the response entity
	 */
	@PostMapping(value = "/kbaseAttributes")
	private ResponseEntity<KbaseAttribute> createKbaseAttribute(@RequestBody KbaseAttribute kbaseAttribute) {
		return new ResponseEntity<>(kbasAttributeService.createKbaseAttribute(kbaseAttribute), HttpStatus.OK);
	}

	/**
	 * Gets the kbase attribute.
	 *
	 * @param kbaseId the kbase id
	 * @return the kbase attribute
	 */
	@GetMapping(value = "/kbaseAttributes/{kbaseId}")
	private Optional<KbaseAttribute> getKbaseAttributeById(@PathVariable String kbaseId) {
		return kbasAttributeService.getKbaseAttributeById(kbaseId);
	}

	/**
	 * Gets the kbase attribute.
	 *
	 * @param kbaseName the kbase name
	 * @return the kbase attribute
	 */
	@GetMapping(value = "/kbaseAttributes/name/{kbaseName}")
	private Optional<KbaseAttribute> getKbaseAttributeByName(@PathVariable String kbaseName) {
		return kbasAttributeService.getKbaseAttributeByName(kbaseName);
	}

	/**
	 * Update kbase attribute.
	 *
	 * @param kbaseAttribute the kbase attribute
	 * @param kbaseId        the kbase id
	 * @return the response entity
	 */
	@PutMapping(value = "/kbaseAttributes/{kbaseId}")
	private ResponseEntity<KbaseAttribute> updateKbaseAttribute(@RequestBody KbaseAttribute kbaseAttribute,
			@PathVariable String kbaseId) {
		return new ResponseEntity<>(kbasAttributeService.updateKbaseAttribute(kbaseAttribute, kbaseId), HttpStatus.OK);
	}

	/**
	 * Delete kbase attribute.
	 *
	 * @param kbaseId the kbase id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/kbaseAttributes/{kbaseId}")
	private ResponseEntity<String> deleteKbaseAttribute(@PathVariable String kbaseId) {
		kbasAttributeService.deleteKbaseAttribute(kbaseId);
		return new ResponseEntity<>("Kbase Attributes deleted successfully", HttpStatus.OK);
	}

	/**
	 * Gets the kbase attributes by category.
	 *
	 * @param categoryList the category list
	 * @return the kbase attributes by category
	 */
	@GetMapping(value = "/kbaseAttributes/categories", produces = "application/json")
	public List<KbaseAttribute> getkbaseAttributesByCategory(
			@RequestParam @NotEmpty(message = "Input Category list cannot be empty.") List<@Valid String> categoryList) {
		return this.kbasAttributeService.getKbaseAttributeByCategory(categoryList);
	}

	/**
	 * Gets the all kbase attribute.
	 *
	 * @return the all kbase attribute
	 */
	@GetMapping(value = "/kbaseAttributes")
	private PageDetail<KbaseAttribute> getAllKbaseAttribute(
			@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size,
			@RequestParam(name = "sortParam", defaultValue = "name:asc", required = false) final String sortParam) {
		List<Order> orders = SortOrderUtil.getSortOrder(sortParam);
		Pageable paging = PageRequest.of(page, size, Sort.by(orders));
		Page<KbaseAttribute> kbaseAttributes = kbasAttributeService.getAllKbaseAttribute(paging);
		PageDetail<KbaseAttribute> paginationResult = new PageDetail<KbaseAttribute>(kbaseAttributes);

		return paginationResult;
	}

}
