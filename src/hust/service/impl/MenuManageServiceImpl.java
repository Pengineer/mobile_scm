package hust.service.impl;

import hust.dao.BaseDao;
import hust.model.Action;
import hust.model.Menu;
import hust.model.MenuType;
import hust.model.Role;
import hust.model.RoleAction;
import hust.model.RoleMenu;
import hust.service.MenuManageService;
import hust.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.bcel.internal.generic.NEW;

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

	public List<Menu> getMenu(Menu menu) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("menu", menu);  // 这也是为什么将menu作为对象传入，而不是传入具体的字段的原因，如果传入的是具体的字段，这里的getMenu方法就不能通用了。
		return baseDao.list(Menu.class.getName() + ".list", paraMap);
	}

	public List<MenuType> getMenuTypeList() {
		List<MenuType> retList = new ArrayList<MenuType>();
		retList.add(new MenuType(Menu.MENUTYPE_PARENT, "有子菜单"));
		retList.add(new MenuType(Menu.MENUTYPE_NODE, "无子菜单"));
		retList.add(new MenuType(Menu.MENUTYPE_LINE, "分割线"));
		return retList;
	}

	@Transactional
	public void addMenu(Menu menu) {
		if (menu.getId() == null) {
			menu.setId(StringUtils.uniqueKey());
		}
		if (menu.getType() == Menu.MENUTYPE_LINE) {
			menu.setName("-- 分割线 --");
		}
		Action action = menu.getAction();
		if (action == null) {
			action = new Action();
			menu.setAction(action);
		}
		if (menu.getType() == Menu.MENUTYPE_NODE) {
			action.setId(StringUtils.uniqueKey());
			action.setType(Action.ACTIONTYPE_NORMAL);
			action.setMid(menu.getId());
			action.setRemark("菜单所属动作");
			baseDao.add(action);
		} else {
			action.setId(null);
		}
		// sort order
		if (menu.getOrder() == null) {
			Integer max = (Integer) baseDao.selectOne(Menu.class.getName() + ".getMaxOrder", menu.getPid());
			menu.setOrder(max == null ? 1 : max + 1);
		}
		baseDao.add(menu);
	}

	@Transactional
	public void editMenu(Menu menu) {
		if(menu.getType() == Menu.MENUTYPE_NODE) {
			baseDao.modify(menu.getAction());
		}
		baseDao.modify(menu);
	}

	@Transactional
	public void delMenu(String id, Integer type) {
		// 删除本菜单
		baseDao.deleteById(Menu.class, id); // 删除sql可以抽象出来，将表名和id最为变量即可
		// 删除关联关系
		baseDao.delete(RoleMenu.class.getName() + ".deleteByMenuId", id);

		// 删除关联动作
		if (type == Menu.MENUTYPE_NODE) {
			delActionsByMenu(id);
		} else if (type == Menu.MENUTYPE_PARENT) {
			// 获得子菜单列表, 递归删除
			List<Menu> menuList = getMenuTree(id, null);

			if (menuList != null && menuList.size() > 0) {
				for (Menu menu : menuList) {
					delMenu(menu.getId(), menu.getType());
				}
			}
		}
	}
	
	@Transactional
	private void delActionsByMenu(String menuId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		Action paraAction = new Action();
		paraAction.setId(menuId);
		paraMap.put("action", paraAction);
		List<Action> actions = baseDao.list(Action.class, paraMap);
		
		baseDao.delete(RoleAction.class.getName() + ".deleteByActionList", actions);
		baseDao.delete(Action.class.getName() + ".deleteByList", actions);
	}

	@Transactional
	public void delAction(Action action) {
		baseDao.delete(Action.class, action);
		baseDao.delete(RoleAction.class.getName() + ".deleteByActionList", new ArrayList<Action>().add(action));
	}

	@Transactional
	public void moveMenu(Menu menu, int i) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("pid", menu.getPid());
		paraMap.put("order", menu.getOrder());
		
		Menu neibMenu = null;
		if (i == 1) {
			// 找到相邻的下一个菜单
			paraMap.put("func", "min");
			paraMap.put("symbol", ">");
			neibMenu = (Menu) baseDao.selectOne(Menu.class.getName() + ".getNeibMenu", paraMap);
		} else if (i == -1) {
			// 找到相邻的上一个菜单
			paraMap.put("func", "max");
			paraMap.put("symbol", "<");
			neibMenu = (Menu) baseDao.selectOne(Menu.class.getName() + ".getNeibMenu", paraMap);
		}

		if (neibMenu != null) { // 有相邻菜单交换order
		
			Menu paraMenu = new Menu();
			
			paraMenu.setId(menu.getId());
			paraMenu.setOrder(neibMenu.getOrder());
			baseDao.modify(paraMenu);
			
			paraMenu.setId(neibMenu.getId());
			paraMenu.setOrder(menu.getOrder());
			baseDao.modify(paraMenu);
		}
	}

	@Transactional
	public void addAction(Action action) {
		// ID
		if (action.getId() == null) {
			action.setId(StringUtils.uniqueKey());
		}
	
		baseDao.add(action);
	}
	
	@Transactional
	public void editAction(Action action) {
		baseDao.modify(action);
	}
	
}
