package com.zeus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.dao.MenuMapper;
import com.zeus.dto.Pagination;
import com.zeus.model.Menu;
import com.zeus.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public Menu getMenuById(Integer menuId) {
		return this.menuMapper.selectByPrimaryKey(menuId);
	}

	@Override
	public List<Menu> listMenus(Pagination page) {
		return null;

	}

	@Override
	public int addMenu(Menu menu) {
		return this.menuMapper.insert(menu);
	}

	@Override
	public int deleteMenu(Integer menuId) {
		return this.menuMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public int update(Menu menu) {
		return this.menuMapper.updateByPrimaryKey(menu);
	}

}
