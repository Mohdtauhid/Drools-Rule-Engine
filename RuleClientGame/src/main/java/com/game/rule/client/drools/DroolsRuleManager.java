/*
 *
 */
package com.game.rule.client.drools;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.kie.api.runtime.KieSession;

import com.game.rule.client.RuleManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class DroolsRuleManager implements RuleManager {

	/** The kie session. */
	private final KieSession kieSession;

	/**
	 * Instantiates a new drools rule manager.
	 *
	 * @param kieSession the kie session
	 */
	public DroolsRuleManager(final KieSession kieSession) {
		log.info("DroolsRuleManager :  {}", kieSession);
		if (kieSession == null) {
			throw new IllegalArgumentException("Argument KieSession cannot be null!");
		}
		this.kieSession = kieSession;
	}

	/**
	 * Close.
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void close() throws Exception {
		log.info("close :  {}", kieSession);
		this.kieSession.destroy();
	}

	/**
	 * Dispose.
	 */
	@Override
	public void dispose() {
		log.info("dispose :  {}", kieSession);
		this.kieSession.dispose();
	}

	/**
	 * Execute.
	 *
	 * @param facts   the facts
	 * @param globals the globals
	 */
	@Override
	public void execute(final Object[] facts, final Optional<Map<String, Object>> globals) {
		log.info("execute :  {} {}", facts, globals);
		setGlobals(globals);
		setFacts(facts);
		this.kieSession.fireAllRules();
	}

	/**
	 * Execute and dispose.
	 *
	 * @param fact    the fact
	 * @param globals the globals
	 */
	@Override
	public void executeAndDispose(final Object[] fact, final Optional<Map<String, Object>> globals) {
		log.info("executeAndDispose :  {} {}", fact, globals);
		execute(fact, globals);
		dispose();
	}

	/**
	 * Sets the facts.
	 *
	 * @param facts the new facts
	 */
	void setFacts(final Object... facts) {
		log.info("setFacts :  {} ", facts);
		if (facts == null) {
			throw new IllegalArgumentException("Argument 'Object[] facts' cannot be null!");
		}
		Arrays.asList(facts).forEach(fact -> {
			this.kieSession.insert(fact);
		});
	}

	/**
	 * Sets the globals.
	 *
	 * @param globals the globals
	 */
	void setGlobals(final Optional<Map<String, Object>> globals) {
		log.info("setGlobals :  {} ", globals);
		globals.ifPresent(map -> map.forEach((key, value) -> {
			log.info("key, value :  {} {} ", key, value);
			this.kieSession.setGlobal(key, value);
		}));
	}
}
