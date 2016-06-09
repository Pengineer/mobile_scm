package hust.service;

import hust.model.Menu;
import hust.model.MenuType;
import hust.model.Role;

import java.util.List;

public interface MenuManageService {

	/*
	 * 以parentID为根，获取其角色范围内的菜单树
	 */
	public List<Menu> getMenuTree(String parentId, List<Role> roleList);
	
	/*
	 * 获取满足menu信息的所有Menu对象
	 */
	public List<Menu> getMenu(Menu menu);

	public List<MenuType> getMenuTypeList();

	public void addMenu(Menu menu);
}
