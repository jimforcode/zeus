package com.zeus.service;

import java.util.List;

import com.zeus.dto.Pagination;
import com.zeus.model.Role;

public interface RoleService {
	public Role getRoleById(Integer roleId);

	public List<Role> listRoles(Pagination page);

	public int addRole(Role role);

	public int deleteRole(Integer roleId);

	public int update(Role role);

}
