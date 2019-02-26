package com.prs.cache.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.prs.cache.serializer.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConditionalOnClass(RedisProperties.Jedis.class)
@EnableConfigurationProperties(value = {RedisProperties.class, PrsCacheProperties.class})
public class RedisAutoConfiguration {

	private final PrsCacheProperties prsCacheProperties;

	@Autowired
	public RedisAutoConfiguration(PrsCacheProperties prsCacheProperties) {
		this.prsCacheProperties = prsCacheProperties;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);

		FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// 全局开启AutoType，不建议使用
		// ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		// 建议使用这种方式，小范围指定白名单
		ParserConfig.getGlobalInstance().addAccept(prsCacheProperties.getAcceptPackage());

		// 设置值（value）的序列化采用FastJsonRedisSerializer。
		redisTemplate.setValueSerializer(fastJsonRedisSerializer);
		redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);

		// 设置键（key）的序列化采用StringRedisSerializer。
		redisTemplate.setKeySerializer(stringRedisSerializer);
		redisTemplate.setHashKeySerializer(stringRedisSerializer);

		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}
