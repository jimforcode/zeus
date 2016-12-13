package com.zeus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.dao.RoleMapper;
import com.zeus.dto.Pagination;
import com.zeus.model.Role;
import com.zeus.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleMapper 	roleMapper ;
	
	@Override
	public Role getRoleById(Integer uId) {
                    
		return this.roleMapper.selectByPrimaryKey(uId);
	}

	@Override
	public List<Role> listRoles(Pagination page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addRole(Role role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRole(Integer userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Role role) {
		// TODO Auto-generated method stub
		return 0;
	}

}
