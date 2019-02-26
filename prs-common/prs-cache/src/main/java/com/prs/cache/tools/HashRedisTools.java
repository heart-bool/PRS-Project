package com.prs.cache.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class HashRedisTools {

	private final HashOperations<String, String, Object> hashOperations;

	@Autowired
	public HashRedisTools(RedisTemplate<String, Object> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}

	public void put(String key, String hashKey, Object value) {
		Assert.notNull(key, RedisTools.KEY_ASSERT);
		Assert.notNull(hashKey, RedisTools.HASH_KEY_ASSERT);
		Assert.notNull(value, RedisTools.VALUE_ASSERT);
		hashOperations.put(key, hashKey, value);
	}

	public Object get(String key, String hashKey) {
		Assert.notNull(key, RedisTools.KEY_ASSERT);
		Assert.notNull(hashKey, RedisTools.HASH_KEY_ASSERT);
		return hashOperations.get(key, hashKey);
	}
}
