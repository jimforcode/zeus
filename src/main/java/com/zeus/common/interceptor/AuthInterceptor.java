package com.zeus.common.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
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
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rep, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		logger.trace("accessable vertify start ..................");
		String curReqUrl = req.getServletPath();
		logger.trace("current request:{}", curReqUrl);

		Map<String, HashSet<String>> auths = new LinkedHashMap<String, HashSet<String>>();
		
		auths = (Map<String, HashSet<String>>) req.getSession().getAttribute("auths");
		logger.trace("my  permissions:{}", auths);
		if (auths.containsKey(curReqUrl)) {

		} else {
			rep.setCharacterEncoding("UTF-8");
			rep.setContentType("application/json; charset=utf-8");
			PrintWriter out = null;
			try {
				out = rep.getWriter();
				out.append("xxxxxZ");

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}

		}
		logger.trace("accessable vertify end ..................");
		return true;
	}

}
