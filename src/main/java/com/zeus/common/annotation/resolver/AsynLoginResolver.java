package com.zeus.common.annotation.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.zeus.common.annotation.login.AsynLoginRequired;
import com.zeus.common.exception.AsynLoginException;
import com.zeus.dto.login.LoginUser;

public class AsynLoginResolver implements HandlerMethodArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
			NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
		boolean required = methodParameter.getParameterAnnotation(AsynLoginRequired.class) != null;
		LoginUser sysUser = (LoginUser) nativeWebRequest.getAttribute("user", WebRequest.SCOPE_SESSION);
		if (required) {
			if (sysUser == null) {
				throw new AsynLoginException();
			} else {
				return sysUser;
			}
		}
		return sysUser;
	}

	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterType().isAssignableFrom(LoginUser.class);
	}

}
