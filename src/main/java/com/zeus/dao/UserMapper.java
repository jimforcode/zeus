package com.zeus.dao;

import java.util.List;
import java.util.Map;

import com.zeus.dto.login.UrlAndPermission;
import com.zeus.model.User;

public interface UserMapper {
	int deleteByPrimaryKey(Long userid);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Long userid);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	List<User> getUserList(Map<String, Object> map);

	/**
	 * �����û���ȡ��Ӧ�Ĳ˵�
	 * 
	 * @param userId
	 * @return
	 */
	List<String> getMenuByUser(Long userId);

	/**
	 * �����û���ȡ��Ӧ�Ĳ˵�Ȩ��
	 * 
	 * @param userId
	 * @return
	 */
	List<UrlAndPermission> getMenuPermissionByUser(Long userId);

}