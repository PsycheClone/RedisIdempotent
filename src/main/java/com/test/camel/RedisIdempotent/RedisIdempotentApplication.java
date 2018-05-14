package com.test.camel.RedisIdempotent;

import org.apache.camel.component.redis.processor.idempotent.RedisIdempotentRepository;
import org.apache.camel.spi.IdempotentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
public class RedisIdempotentApplication {

	@Bean
	public StringRedisSerializer stringRedisSerializer() {
		return new StringRedisSerializer();
	}

	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(10);
		jedisPoolConfig.setMaxIdle(5);
		jedisPoolConfig.setMinIdle(1);
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setTestOnReturn(true);
		jedisPoolConfig.setTestWhileIdle(true);
		jedisPoolConfig.setNumTestsPerEvictionRun(10);
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(60000);
		jedisPoolConfig.setBlockWhenExhausted(true);
		jedisPoolConfig.setMaxWaitMillis(10000);
		return jedisPoolConfig;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new JedisConnectionFactory(jedisPoolConfig());
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(stringRedisSerializer());
		redisTemplate.setValueSerializer(stringRedisSerializer());
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}

	@Bean
	public IdempotentRepository<String> redisIdempotentOne() {
		return RedisIdempotentRepository.redisIdempotentRepository(redisTemplate(), "key1");
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisIdempotentApplication.class, args);
	}
}
