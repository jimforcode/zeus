package com.zeus.common.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
@Aspect
public class RequestAllowOriginInterceptor {

	@After("@annotation(com.zeus.common.annotation.login.RequestAllowOirginRequired)")
	public void addRequestHeader(JoinPoint joint) {
		Object[] args = joint.getArgs();
		HttpServletResponse response = (HttpServletResponse) args[1];

		for (Object arg : args) {
			if (arg.getClass().isInstance(HttpServletResponse.class)) {
				System.out.println(arg.getClass().getName());
			}
		}

		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "0");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("XDomainRequestAllowed", "1");
	}

}
