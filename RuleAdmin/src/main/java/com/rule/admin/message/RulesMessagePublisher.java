/*
 *
 */
package com.rule.admin.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.rule.admin.message.MessagePublisher;

@Service
public class RulesMessagePublisher implements MessagePublisher {

	/** The redis template. */
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/** The topic. */
	@Autowired
	private ChannelTopic topic;

	/**
	 * Instantiates a new rules message publisher.
	 */
	public RulesMessagePublisher() {
	}

	/**
	 * Instantiates a new rules message publisher.
	 *
	 * @param redisTemplate the redis template
	 * @param topic         the topic
	 */
	public RulesMessagePublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
		this.redisTemplate = redisTemplate;
		this.topic = topic;
	}

	/**
	 * Publish.
	 *
	 * @param message the message
	 */
	@Override
	public void publish(final String message) {
		this.redisTemplate.convertAndSend(this.topic.getTopic(), message);
	}

}
