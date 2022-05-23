package com.rule.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.rule.admin.message.MessagePublisher;
import com.rule.admin.message.RulesMessagePublisher;

@Configuration
public class RedisConfig {

	/** The topic. */
	@Value("${rulesapi.topic}")
	private String topic;

	/** The Redis host. */
	@Value("${spring.redis.host}")
	private String redisHost;

	/** The Redis port. */
	@Value("${spring.redis.port}")
	private int redisPort;

	/**
	 * Jedis connection factory.
	 *
	 * @return the jedis connection factory
	 */
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost,
				redisPort);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	/**
	 * Redis publisher.
	 *
	 * @return the message publisher
	 */
	@Bean
	MessagePublisher redisPublisher() {
		return new RulesMessagePublisher(redisTemplate(), topic());
	}

	/**
	 * Redis template.
	 *
	 * @return the redis template
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final var template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
		return template;
	}

	/**
	 * Topic.
	 *
	 * @return the channel topic
	 */
	@Bean
	ChannelTopic topic() {
		return new ChannelTopic(this.topic);
	}
}
