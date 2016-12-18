package com.zeus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.dao.RoleMapper;
import com.zeus.dto.Pagination;
import com.zeus.model.Role;
import com.zeus.model.User;
import com.zeus.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleMapper roleMapper;

	@Override
	public Role getRoleById(Integer uId) {

		return this.roleMapper.selectByPrimaryKey(uId);
	}

	@Override
	public List<Role> listRoles(Pagination page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && page.getFirstResult() != null && page.getRows() != null) {
			map.put("firstResult", page.getFirstResult());
			map.put("rows", page.getRows());
		}
		List<Role> result = this.roleMapper.getRoleList(map);
		return result;

	}

	@Override
	public int addRole(Role role) {
		return this.roleMapper.insert(role);
	}

	@Override
	public int deleteRole(Integer roleId) {
		return this.roleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public int update(Role role) {
		return this.roleMapper.updateByPrimaryKey(role);
	}

}
