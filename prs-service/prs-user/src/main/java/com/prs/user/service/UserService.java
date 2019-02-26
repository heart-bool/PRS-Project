package com.prs.user.service;

import com.prs.user.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserService {

	@Autowired
	private UserMapper userMapper;
}
