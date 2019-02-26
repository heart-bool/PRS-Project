package com.prs.aop.logging;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


@Aspect
@Configuration
@Log4j2
public class WebGlobalRequestLoggerConfiguration {

	@Pointcut(value = "execution(public * com.prs.service.*.controller.*.*(..))")
	public void requestLog() {
	}

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");

	@Before("requestLog()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		log.info("URL: " + request.getRequestURL().toString()
				+ ";HTTP_METHOD: " + request.getMethod()
				+ ";IP: " + request.getRemoteAddr()
				+ ";CLASS_METHOD: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
				+ ";ARGS: " + Arrays.toString(joinPoint.getArgs())
				+ ";REQUEST DATE: " + format.format(new Date())
		);
	}

}
