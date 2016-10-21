package com.zeus.common.annotation.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.zeus.common.annotation.login.WebLoginRequired;
import com.zeus.common.exception.WebLoginException;
import com.zeus.dto.login.LoginUser;

public class WebLoginResolver implements HandlerMethodArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
			WebDataBinderFactory webDataBinderFactory) throws Exception {
		boolean required = methodParameter.getParameterAnnotation(WebLoginRequired.class) != null;
		LoginUser sysUser = (LoginUser) nativeWebRequest.getAttribute("user", WebRequest.SCOPE_SESSION);

		if (required) {
			if (sysUser == null) {
				throw new WebLoginException();
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