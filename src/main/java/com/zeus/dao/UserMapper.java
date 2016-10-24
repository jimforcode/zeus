package com.zeus.dao;

import java.util.List;
import java.util.Map;

import com.zeus.model.User;

public interface UserMapper {
	int deleteByPrimaryKey(Long userid);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Long userid);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	List<User> getUserList(Map<String, Object> map);
}