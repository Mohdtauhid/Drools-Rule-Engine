package com.rule.client;

import java.util.Map;
import java.util.Optional;

/**
 * The Interface RuleManager.
 */
public interface RuleManager extends AutoCloseable {

	/**
	 * Dispose.
	 */
	void dispose();

	/**
	 * Execute.
	 *
	 * @param fact    the fact
	 * @param globals the globals
	 */
	void execute(Object[] fact, Optional<Map<String, Object>> globals);

	/**
	 * Execute and dispose.
	 *
	 * @param fact    the fact
	 * @param globals the globals
	 */
	void executeAndDispose(Object[] fact, Optional<Map<String, Object>> globals);
}
