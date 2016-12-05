package com.zeus.dao;

import com.zeus.model.RoleMenuPermissionKey;

public interface RoleMenuPermissionMapper {
    int deleteByPrimaryKey(RoleMenuPermissionKey key);

    int insert(RoleMenuPermissionKey record);

    int insertSelective(RoleMenuPermissionKey record);
}