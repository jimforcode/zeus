package com.zeus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.dao.UserMapper;
import com.zeus.model.User;
import com.zeus.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	public User getUserById(Long uId) {
		return this.userMapper.selectByPrimaryKey(uId);
	}

}
