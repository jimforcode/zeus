package com.zeus.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeus.model.User;
import com.zeus.service.UserService;

@Controller
@RequestMapping("/user")
public class BizUserController extends BaseController {

	@Autowired
	UserService userService;

	@RequestMapping("add")
	public Map<String, Object> addUser(User user) {
		return resultOK(this.userService.addUser(user));
	}

	@RequestMapping("del")
	public Map<String, Object> delUser(User user) {
		return resultOK(this.userService.addUser(user));
	}

	@RequestMapping("update")
	public Map<String, Object> updateUser(User user) {
		return resultOK(this.userService.update(user));
	}

}
