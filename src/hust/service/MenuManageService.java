package hust.service;

import hust.model.Menu;
import hust.model.Role;

import java.util.List;

public interface MenuManageService {

	public List<Menu> getMenuTree(String parentId, List<Role> roleList);
}
