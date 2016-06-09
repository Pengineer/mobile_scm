package hust.service.impl;

import hust.dao.BaseDao;
import hust.model.Menu;
import hust.model.MenuType;
import hust.model.Role;
import hust.service.MenuManageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings({"rawtypes","unchecked"})
public class MenuManageServiceImpl implements MenuManageService {

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public List<Menu> getMenuTree(String parentId, List<Role> roleList) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
//		paraMap.put("pid", parentId);
//		paraMap.put("roleList", roleList);
		Menu paraMenu = new Menu();
		paraMenu.setPid(parentId);
		paraMap.put("menu", paraMenu);
		paraMap.put("roleList", roleList);
		
		List<Menu> menuList = baseDao.list(Menu.class.getName() + ".list", paraMap);
		
		if (menuList != null && menuList.size() > 0) {
			for (Menu menu : menuList) {
				menu.setSubMenuList(getMenuTree(menu.getId(), roleList));
			}
		}
		
		return menuList;
	}

	@Override
	public List<Menu> getMenu(Menu menu) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("menu", menu);  // 这也是为什么将menu作为对象传入，而不是传入具体的字段的原因，如果传入的是具体的字段，这里的getMenu方法就不能通用了。
		return baseDao.list(Menu.class.getName() + ".list", paraMap);
	}

	@Override
	public List<MenuType> getMenuTypeList() {
		List<MenuType> retList = new ArrayList<MenuType>();
		retList.add(new MenuType(Menu.MENUTYPE_PARENT, "有子菜单"));
		retList.add(new MenuType(Menu.MENUTYPE_NODE, "无子菜单"));
		retList.add(new MenuType(Menu.MENUTYPE_LINE, "分割线"));
		return retList;
	}

	@Override
	public void addMenu(Menu menu) {
		
	}
	
}
