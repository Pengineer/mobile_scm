package hust.service;

import hust.model.Action;
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

	public void editMenu(Menu menu);

	public void delMenu(String id, Integer type);

	public void delAction(Action action);

	public void moveMenu(Menu menu, int i);

	public void addAction(Action action);

	public void editAction(Action action);
}
