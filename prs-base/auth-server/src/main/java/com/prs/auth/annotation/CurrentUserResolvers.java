package com.prs.auth.annotation;

import com.prs.auth.execption.AuthException;
import com.prs.auth.properties.TokenProperties;
import com.prs.cache.tools.HashRedisTools;
import com.prs.domain.enu.ApiResponseEnum;
import com.prs.domain.response.TokenResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Configuration
@Slf4j
public class CurrentUserResolvers implements HandlerMethodArgumentResolver {

    @Autowired
    private HashRedisTools hashRedisTools;

    @Autowired
    private TokenProperties tokenProperties;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = webRequest.getHeader("token");
        TokenResponseEntity user = (TokenResponseEntity) hashRedisTools.get(tokenProperties.getKeyPrefix(), token);
        if (user == null || System.currentTimeMillis() > user.getExpire()) {
            throw new AuthException(ApiResponseEnum.UNAUTHORIZED);
        }
        return user;
    }
}
