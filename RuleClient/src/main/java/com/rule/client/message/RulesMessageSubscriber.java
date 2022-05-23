/*
 *
 */
package com.rule.client.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import com.rule.client.RuleManagerFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class RulesMessageSubscriber.
 */
@Component
@Slf4j
public class RulesMessageSubscriber implements MessageListener {

	/** The rule manager factory. */
	@Autowired
	private RuleManagerFactory ruleManagerFactory;

	/**
	 * On message.
	 *
	 * @param message the message
	 * @param pattern the pattern
	 */
	@Override
	public void onMessage(final Message message, final byte[] pattern) {
		log.info("Message received: {}", new String(message.getBody()));
		this.ruleManagerFactory.refresh();
	}

}
