package com.rule.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rule.admin.entity.KbaseAttribute;
import com.rule.admin.repository.KbaseAttributeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KbaseAttributeService {

	@Autowired
	private KbaseAttributeRepository kbaseAttributeRepository;

	public KbaseAttribute createKbaseAttribute(KbaseAttribute kbaseAttribute) {

		KbaseAttribute savedkbase = kbaseAttributeRepository.save(kbaseAttribute);
		return savedkbase;

	}

	public Optional<KbaseAttribute> getKbaseAttributeById(String kbaseAttributeId) {
		return kbaseAttributeRepository.findById(kbaseAttributeId);
	}

	public Optional<KbaseAttribute> getKbaseAttributeByName(String kbaseAttributeName) {
		return kbaseAttributeRepository.findByName(kbaseAttributeName);
	}

	public Page<KbaseAttribute> getAllKbaseAttribute(Pageable pageable) {
		return kbaseAttributeRepository.findAll(pageable);
	}

	public KbaseAttribute updateKbaseAttribute(KbaseAttribute kbaseAttribute, String kbaseAttributeId) {
		Optional<KbaseAttribute> existingKbaseAttribute = kbaseAttributeRepository.findById(kbaseAttributeId);
		KbaseAttribute updatedkbase = null;
		if (existingKbaseAttribute.isPresent()) {
			KbaseAttribute updateKbaseAttribute = existingKbaseAttribute.get();
			updateKbaseAttribute.setAttributes(kbaseAttribute.getAttributes());
			updateKbaseAttribute.setCategory(kbaseAttribute.getCategory());
			updateKbaseAttribute.setName(kbaseAttribute.getName());
			updateKbaseAttribute.setPackages(kbaseAttribute.getPackages());
			updatedkbase = kbaseAttributeRepository.save(updateKbaseAttribute);
		}
		return updatedkbase;
	}

	public void deleteKbaseAttribute(String kbaseAttributeId) {
		kbaseAttributeRepository.deleteById(kbaseAttributeId);
	}

	public List<KbaseAttribute> getKbaseAttributeByCategory(List<String> categoryList) {
		return kbaseAttributeRepository.findByCategoryIn(categoryList);
	}

}
