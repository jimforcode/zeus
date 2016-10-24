package com.zeus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.dao.UserMapper;
import com.zeus.dto.Pagination;
import com.zeus.model.User;
import com.zeus.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserMapper userMapper;

	public User getUserById(Long uId) {
		logger.debug("ИзІО:{}", uId);
		return this.userMapper.selectByPrimaryKey(uId);
	}

	@Override
	public List<User> listUsers(Pagination page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && page.getFirstResult() != null && page.getRows() != null) {
			map.put("firstResult", page.getFirstResult());
			map.put("rows", page.getRows());
		}
		List<User> result = this.userMapper.getUserList(map);
		return result;
	}

}
