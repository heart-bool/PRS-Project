package com.prs.auth.service;

import com.prs.auth.entity.User;
import com.prs.auth.execption.AuthException;
import com.prs.auth.mapper.UserMapper;
import com.prs.auth.properties.TokenProperties;
import com.prs.cache.Constant;
import com.prs.cache.tools.HashRedisTools;
import com.prs.domain.enu.ApiResponseEnum;
import com.prs.domain.response.TokenResponseEntity;
import com.prs.tools.MD5Tools;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private HashRedisTools hashRedisTools;

	@Autowired
	private TokenProperties tokenProperties;

	/***
	 * 用户注册
	 *
	 * @param info
	 */
	public void saveUserByRegister(User info) throws AuthException {
		info.setPassword(MD5Tools.md5(info.getPassword()));
		info.setCreated(new Date());
		userMapper.insertSelective(info);
		log.info("用户注册成功, 用户名:{}", info.getUserName());
	}

	/***
	 * 检查用户名是否存在
	 *
	 * @param userName 用户名
	 * @throws AuthException
	 */
	public void hasUserName(String userName) throws AuthException {
		User user = userMapper.getUserByUserName(userName);
		if (user != null) {
			throw new AuthException(ApiResponseEnum.REGISTER_USERNAME_ERROR);
		}
	}

	/***
	 * 检查邮箱是否存在
	 *
	 * @param email 邮箱
	 * @throws AuthException
	 */
	public void hasEmail(String email) throws AuthException {
		User user = userMapper.getUserByEmail(email);
		if (user != null) {
			throw new AuthException(ApiResponseEnum.REGISTER_EMAIL_ERROR);
		}
	}

	/***
	 * 检查手机号是否存在
	 *
	 * @param phone 手机号
	 * @throws AuthException
	 */
	public void hasPhone(String phone) throws AuthException {
		User user = userMapper.getUserByPhone(phone);
		if (user != null) {
			throw new AuthException(ApiResponseEnum.REGISTER_PHONE_ERROR);
		}
	}

	/***
	 * 密码登录
	 *
	 * @param loginName 登录名 可能是用户名/邮箱/手机号
	 * @param password 密码
	 * @return TokenResponseEntity
	 * @throws AuthException
	 */
	public Map<String, Object> passwordLogin(String loginName, String password) throws AuthException {

		// 检查登录名和密码
		User user = getUserByLogin(loginName);
		if (user == null || !user.getPassword().equals(password)) {
			throw new AuthException(ApiResponseEnum.LOGIN_ERROR);
		}

		// 生成 token --生成规则: 使用用户名生成唯一MD5值
		String token = MD5Tools.md5(user.getUserName());
		// 检查重复登录
		TokenResponseEntity is = (TokenResponseEntity) hashRedisTools.get(Constant.AUTH_HASH, token);
		if (is != null &&  System.currentTimeMillis() < is.getExpire()) {
			throw new AuthException(ApiResponseEnum.LOGIN_REPEAT_ERROR);
		}

		// 返回结构
		Map<String, Object> res = new HashMap<>();

		// 创建token
		TokenResponseEntity tokenUser = new TokenResponseEntity();
		BeanUtils.copyProperties(user, tokenUser);

		// 设置token的过期时间和刷新时间
		long expire = System.currentTimeMillis() + tokenProperties.getExpire();
		long refresh = System.currentTimeMillis() + tokenProperties.getRefresh();
		tokenUser.setExpire(expire);
		tokenUser.setRefresh(refresh);

		// 生成缓存
		hashRedisTools.put(Constant.AUTH_HASH, token, tokenUser);
		res.put("token", token);
		res.put("userInfo", tokenUser);
		log.info("用户登录成功, 用户名:{}", user.getUserName());
		return res;
	}

	/***
	 * 根据登录名获取用户信息
	 *
	 * @param loginName 登录名 可能是用户名/邮箱/手机号
	 * @return User
	 */
	public User getUserByLogin(String loginName) {
		return userMapper.getUserByLoginName(loginName);
	}

	/***
	 * 刷新token有效期
	 *
	 * @param token
	 * @param longTime
	 * @throws AuthException
	 */
	public void refreshToken(String token, long longTime) throws AuthException {
		TokenResponseEntity is = (TokenResponseEntity) hashRedisTools.get(Constant.AUTH_HASH, token);
		if (is == null) throw new AuthException(ApiResponseEnum.LOGIN_NOT_ERROR);

		is.setExpire(is.getExpire() + longTime);
		is.setRefresh(is.getRefresh() + longTime);

		hashRedisTools.put(Constant.AUTH_HASH, token, is);
	}
}
