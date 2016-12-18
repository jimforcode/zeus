package com.zeus.dao;

import java.util.List;
import java.util.Map;

import com.zeus.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
	List<Role> getRoleList(Map<String, Object> map);

    
}