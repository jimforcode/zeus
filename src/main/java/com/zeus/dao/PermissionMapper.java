package com.zeus.dao;

import com.zeus.model.Permission;
import com.zeus.model.PermissionKey;

public interface PermissionMapper {
    int deleteByPrimaryKey(PermissionKey key);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(PermissionKey key);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}