package hust.dev.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hust.action.BaseAction;
import hust.model.Menu;
import hust.model.Role;
import hust.service.MenuManageService;

public class MenuManageAction extends BaseAction {

	private static final long serialVersionUID = -7750552997615790146L;

	@Autowired
	private MenuManageService menuManageService;
	
	
	
	public String init() {
		
		return "menuManage";
	}
	
	//获取菜单数据
	public String menuTree() {
		Menu rootMenu = new Menu();
		rootMenu.setId("root");
		rootMenu.setName(application.getInitParameter("sysname"));
		
		Role r1 = new Role();
		Role r2 = new Role();
		r1.setId("123");
		r2.setId("456");
		List<Role> list = new ArrayList<Role>();
		list.add(r1);
		list.add(r2);
		
		menuManageService.getMenuTree(rootMenu.getId(), list);
		return "managePage";
	}
}
