package hust.dev.action;

import org.springframework.beans.factory.annotation.Autowired;

import hust.action.BaseAction;
import hust.model.Menu;
import hust.service.MenuManageService;

public class MenuManageAction extends BaseAction {

	private static final long serialVersionUID = -7750552997615790146L;

	@Autowired
	private MenuManageService menuManageService;
	
	public String init() {
		
		return "menuManage";
	}
	
	//获取菜单数据
	public void menuTree() {
		Menu rootMenu = new Menu();
		rootMenu.setId("root");
		rootMenu.setName(application.getInitParameter("sysname"));
		
	}
}
