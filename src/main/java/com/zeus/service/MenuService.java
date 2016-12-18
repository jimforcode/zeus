package com.zeus.service;

import java.util.List;

import com.zeus.dto.Pagination;
import com.zeus.model.Menu;

public interface MenuService {
	public Menu getMenuById(Integer menuId);

	public List<Menu> listMenus(Pagination page);

	public int addMenu(Menu menu);

	public int deleteMenu(Integer menuId);

	public int update(Menu menu);

}
