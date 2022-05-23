package com.rule.admin.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * The Class SortOrderUtil.
 */
public final class SortOrderUtil {

	/** The Constant COMMA. */
	private static final String COMMA = ",";

	/** The Constant COLON. */
	private static final String COLON = ":";

	/** The Constant DESC. */
	private static final String DESC = "desc";

	/** The Constant ASC . */
	private static final String ASC = "asc";

	/**
	 * Gets the sort order.
	 *
	 * @param sortParam the sort param
	 * @return the sort order
	 */
	public static List<Order> getSortOrder(final String sortParam) {
		final var sortParams = sortParam.split(COMMA);
		final Predicate<String> invalidParam = param -> !param.contains(COLON);
		final Predicate<String> invalidLength = param -> param.split(COLON).length < 2;
		final Predicate<String> emptySortParam = param -> param.split(COLON)[0].equals("");
		final Predicate<String> invalidDir = param -> !(param.split(COLON)[1].equalsIgnoreCase(ASC)
				|| param.split(COLON)[1].equalsIgnoreCase(DESC));

		final var invalidUrlParam = Arrays.stream(sortParams)
				.filter(invalidParam.or(invalidLength).or(emptySortParam).or(invalidDir)).findAny();
		if (invalidUrlParam.isPresent()) {
			throw new InvalidDataAccessApiUsageException("Invalid Sort Param : " + invalidUrlParam.get());
		}

		final List<Order> orders = new ArrayList<>();
		Arrays.stream(sortParams).forEach(sortp -> {
			final var sortParDir = sortp.split(COLON);
			final var direction = DESC.equalsIgnoreCase(sortParDir[1]) ? Direction.DESC : Direction.ASC;
			final var order = new Order(direction, sortParDir[0]);
			orders.add(order);
		});
		return orders;
	}

	/**
	 * Instantiates a new sort order util.
	 */
	private SortOrderUtil() {
	}

}
