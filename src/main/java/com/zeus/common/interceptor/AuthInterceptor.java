package com.zeus.common.interceptor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthInterceptor implements HandlerInterceptor {

	static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("+++++++++++++++++++++++++afterCompletion");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("+++++++++++++++++++++++++postHandle");
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rep, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("+++++++++++++++++++++++++preHandle");

		Map<String, List<String>> auths = new LinkedHashMap<String, List<String>>();
		// key menu
		// value functions

		// cur url
		String path = req.getServletPath();
		if (auths.containsKey(path)) {

		} else {

			return false;
		}

		return true;
	}

}
