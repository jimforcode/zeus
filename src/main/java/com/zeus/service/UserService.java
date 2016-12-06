package com.zeus.service;

import java.util.List;

import com.zeus.dto.Pagination;
import com.zeus.dto.login.UrlAndPermission;
import com.zeus.model.User;

public interface UserService {
	public User getUserById(Long uId);

	public List<User> listUsers(Pagination page);

	public List<String> listMenuByUser(Long userId);

	List<UrlAndPermission> getMenuPermissionByUser(Long userId);

}
