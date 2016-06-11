package hust.dev.action;

import java.util.List;

import hust.action.BaseAction;
import hust.model.Action;
import hust.model.Menu;
import hust.model.MenuType;
import hust.service.ActionService;
import hust.service.MenuManageService;

import org.springframework.beans.factory.annotation.Autowired;

public class MenuManageAction extends BaseAction {

	private static final long serialVersionUID = -7750552997615790146L;

	@Autowired
	private MenuManageService menuManageService;
	
	@Autowired
	private ActionService actionService;
	
	// 获取整个菜单列表
	private Menu menuTree;
	
	// 封装前端穿过来的menu参数
	private Menu menu;
	
	// 返回给前端的菜单详细信息
	private Menu parentMenu;
	private List<Menu> subMenuList;
	private List<Action> normalActionList;
	private List<Action> authorActionList;
	private List<MenuType> menuTypeList;
	
	private boolean refreshTree; 
	
	public String init() {
		
		return "managePage";
	}
	
	//获取所有菜单数据
	public String menuTree() {
		menuTree = generateRoot();
		menuTree.setSubMenuList(menuManageService.getMenuTree(menuTree.getId(), null));
		return "menuTree";
	}
	
	// 菜单详情
	public String menuDetail() {
		List<Menu> menuList = null;
		if (menu.getId().equals("root")) {
			menu = generateRoot();
		} else {
			menuList = menuManageService.getMenu(menu);
			if (menuList != null && menuList.size() == 1) {
				menu = menuList.get(0);
			}
		}
		Menu tmp = new Menu();
		// 查询父菜单
		if (menu.getPid() != null) {
			if (menu.getPid().equals("root")) {
				parentMenu =generateRoot();
			} else {
				tmp.setId(menu.getPid());
				menuList = menuManageService.getMenu(tmp);
				parentMenu = menuList.get(0);
			}
		}
		// 查询子菜单
		if (menu.getType() == Menu.MENUTYPE_PARENT) {
			tmp.setId(null);
			tmp.setPid(menu.getId());
			subMenuList = menuManageService.getMenu(tmp);
		}
		// 查询动作
		if (menu.getType() == Menu.MENUTYPE_NODE) {
			Action paraAction = new Action();
			paraAction.setMid(menu.getId());
			paraAction.setType(Action.ACTIONTYPE_NORMAL);
			normalActionList = actionService.getActions(paraAction);
			
			paraAction.setType(Action.ACTIONTYPE_AUTHOR);
			authorActionList = actionService.getActions(paraAction);
		}
		return "menuDetail";
	}
	
	// 添加子菜单
	public String toAddSubMenu() {
		List<Menu> retList = null;
		if (menu.getId().equals("root")) { // 如果当前菜单是root根菜单
			menu = generateRoot();
		} else {
			retList = menuManageService.getMenu(menu);
			if (retList != null && retList.size() == 1) {
				menu = retList.get(0);
			}
		}
	
		menuTypeList = menuManageService.getMenuTypeList();
		return "addMenuPage";
	}
	
	public String addMenu() throws Exception {
		menuManageService.addMenu(menu);
		refreshTree = true;
		return "addSucc";
	}
	
	public String toEditMenu() throws Exception {
		List<Menu> retList = menuManageService.getMenu(menu);
		Menu paraMenu = new Menu();
		if (retList != null && retList.size() > 0) {
			menu = retList.get(0);
			paraMenu.setId(menu.getPid());
			if (paraMenu.getId().equals("root")) {
				parentMenu = generateRoot();
			} else {
				parentMenu = menuManageService.getMenu(paraMenu).get(0);
			}
		}
		menuTypeList = menuManageService.getMenuTypeList();

		return "editMenuPage";
	}

	public String editMenu() throws Exception {
		menuManageService.editMenu(menu);
		// 为保证编辑后回到原菜单级别而设置此项（因为在struts配置文件中添加菜单时，传递的参数是父菜单id，它会跳到父菜单的列表详情页面）
		menu.setPid(menu.getId());
		refreshTree = true;
		return "addSucc";
	}
	
	public String delMenu() {
		menuManageService.delMenu(menu.getId(), menu.getType());
		refreshTree = true;
		return "addSucc";
	}
	
	public String upMenu() {
		menu = menuManageService.getMenu(menu).get(0);
		menuManageService.moveMenu(menu, -1);
		refreshTree = true;
		return "addSucc";
	}
	
	public String downMenu() {
		menu = menuManageService.getMenu(menu).get(0);
		menuManageService.moveMenu(menu, 1);
		refreshTree = true;
		return "addSucc";
	}
	
	// 因为数据库中本身是不存在root这样的记录
	private Menu generateRoot() {
		Menu rootMenu = new Menu();
		rootMenu.setId("root");
		rootMenu.setName((String) application.getAttribute("sysname"));
		rootMenu.setType(Menu.MENUTYPE_PARENT);
		rootMenu.setRemark("系统菜单根对象");
		return rootMenu;
	}
	
	/*
	 * 返回json格式数据时，会将类中所有的get方法所对应的属性进行json化，可以在get方法上加@JSON(serialize=false)来阻止序列化
	 * 因此在写属性的get/set方法时，对不需要的方法就不要写，影响效率。（如果menuManageService拥有get/set方法，那么在返回Json
	 * 格式的数据时，它会被实例化。）
	 */
//	private String test;
//	@JSON(serialize=false)
//	public String getTest() {
//		return "test2";
//	}
	
	public Menu getMenuTree() {
		return menuTree;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Menu getMenu() {
		return menu;
	}
	public Menu getParentMenu() {
		return parentMenu;
	}
	public List<Menu> getSubMenuList() {
		return subMenuList;
	}
	public List<Action> getNormalActionList() {
		return normalActionList;
	}
	public List<Action> getAuthorActionList() {
		return authorActionList;
	}
	public List<MenuType> getMenuTypeList() {
		return menuTypeList;
	}
	public boolean isRefreshTree() {
		return refreshTree;
	}
	public void setRefreshTree(boolean refreshTree) {
		this.refreshTree = refreshTree;
	}
}
