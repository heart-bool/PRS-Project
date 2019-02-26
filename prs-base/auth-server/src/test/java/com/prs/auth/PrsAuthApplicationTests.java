package com.prs.auth;

import com.prs.auth.entity.User;
import com.prs.domain.response.TokenResponseEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrsAuthApplicationTests {

	@Test
	public void contextLoads() {
		User user = new User();
		user.setUserName("123");

		TokenResponseEntity entity = new TokenResponseEntity();
		BeanUtils.copyProperties(user, entity);

		System.out.println(entity.getUserName());
	}

}
