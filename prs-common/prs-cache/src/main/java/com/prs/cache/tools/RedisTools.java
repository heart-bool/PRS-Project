package com.prs.cache.tools;

public interface RedisTools {

	String KEY_ASSERT = "key is must be null";
	String HASH_KEY_ASSERT = "hash key is must be null";
	String VALUE_ASSERT = "value is must be null";

	void set(String key, String value, long timeOut);

	Object get(String key);

}
