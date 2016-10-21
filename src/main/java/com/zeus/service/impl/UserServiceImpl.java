package com.zeus.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.dao.UserMapper;
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

}
