package com.rule.admin.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonView;
import com.rule.admin.views.PageView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class PageImpl.
 *
 * @param <T> the generic type
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDetail<T> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8212390476830547103L;

	/** The content. */
	@JsonView({ PageView.class })
	private List<T> data;

	/** The number. */
	@JsonView({ PageView.class })
	private int currentPage;

	/** The total elements. */
	@JsonView({ PageView.class })
	private long total;

	/** The total pages. */
	@JsonView(PageView.class)
	private int totalPages;

	/**
	 * Instantiates a new page impl.
	 *
	 * @param page the page
	 */
	public PageDetail(Page<T> page) {
		this.data = page.getContent();
		this.currentPage = page.getNumber();
		this.total = page.getTotalElements();
		this.totalPages = page.getTotalPages();
	}
}
