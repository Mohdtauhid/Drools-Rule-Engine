package com.game.rule.client.drools;

import org.kie.api.runtime.KieContainer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KieContainerHolder {

	/** The kie container. */
	private KieContainer kieContainer;

	/**
	 * Instantiates a new kie container holder.
	 *
	 * @param kieContainer the kie container
	 */
	public KieContainerHolder(final KieContainer kieContainer) {
		log.info("KieContainerHolder : {} ", kieContainer);
		if (kieContainer == null) {
			throw new IllegalArgumentException("Argument KieContainer cannot be null!");
		}
		this.kieContainer = kieContainer;
	}

	/**
	 * Gets the.
	 *
	 * @return the kie container
	 */
	public synchronized KieContainer get() {
		log.info("KieContainer get :{} ", kieContainer);
		return this.kieContainer;
	}

	/**
	 * Sets the.
	 *
	 * @param kieContainer the kie container
	 */
	public synchronized void set(final KieContainer kieContainer) {
		log.info("KieContainer set :{}", kieContainer);
		this.kieContainer = kieContainer;
	}
}
