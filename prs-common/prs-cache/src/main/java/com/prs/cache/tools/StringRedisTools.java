package com.prs.cache.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class StringRedisTools implements RedisTools {

	private final ValueOperations<String, String> stringValueOperations;

	@Autowired
	public StringRedisTools(StringRedisTemplate redisTemplate) {
		this.stringValueOperations = redisTemplate.opsForValue();
	}

	/***
	 * string set
	 *
	 * @param key key 不能为空
	 * @param value value 不能为空
	 * @param timeOut <= 0 不设置有效期
	 */
	@Override
	public void set(String key, String value, long timeOut) {

		Assert.notNull(key, KEY_ASSERT);
		Assert.notNull(value, VALUE_ASSERT);
		if (timeOut > 0)
			stringValueOperations.set(key, value, timeOut);
		else
			stringValueOperations.set(key, value);
	}

	@Override
	public String get(String key) {
		Assert.notNull(key, KEY_ASSERT);
		return stringValueOperations.get(key);
	}
}
