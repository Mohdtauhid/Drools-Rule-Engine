package com.rule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.rule.client.message.RulesMessageSubscriber;

/**
 * The Class RedisConfig.
 */
@Configuration
@ComponentScan("com.rule.client.message")
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

	/** The rules subscriber. */
	@Autowired
	RulesMessageSubscriber rulesSubscriber;

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
	 * Message listener.
	 *
	 * @param rulesMessageSubscriber the rules message subscriber
	 * @return the message listener adapter
	 */
	@Bean
	MessageListenerAdapter messageListener(final RulesMessageSubscriber rulesMessageSubscriber) {
		return new MessageListenerAdapter(rulesMessageSubscriber);
	}

	/**
	 * Redis container.
	 *
	 * @return the redis message listener container
	 */
	@Bean
	RedisMessageListenerContainer redisContainer() {
		final var container = new RedisMessageListenerContainer();
		container.setConnectionFactory(jedisConnectionFactory());
		container.addMessageListener(messageListener(this.rulesSubscriber), topic());
		return container;
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
