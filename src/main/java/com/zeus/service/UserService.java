package com.zeus.service;

import java.util.List;

import com.zeus.dto.Pagination;
import com.zeus.model.User;

public interface UserService {
	public User getUserById(Long uId);

	public List<User> listUsers(Pagination page);

	public int addUser(User user);

	public int deleteUser(Long userid);

	public int update(User user);

}
