package com.prs.user;

import com.prs.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrsUserApplicationTests {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {

		HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();

//		redisTemplate.opsForValue().set("test", "test");
//		Object test = redisTemplate.opsForValue().get("test");
//		System.out.println(test);

//		List<User> allByIdIn = userService.findAllByIdIn();
//		System.out.println(allByIdIn);

//		Map<String, Object> param = new HashMap<>();
//		param.put("key4", "d");
//		param.put("key1", "a");
//		param.put("key3", "c");
//		param.put("key5", "e");
//		param.put("key2", "b");
//		param.put("a", new A("zhangsan"));
//		opsForHash.putAll("he8", param);
//		Set<String> keys = opsForHash.keys("he8");
//		keys.forEach(System.out::println);


		A value = (A) opsForHash.get("he8", "a");
		System.out.printf(value.getName());

	}

}
