package hust.service.impl;

import hust.dao.BaseDao;
import hust.model.Menu;
import hust.model.Role;
import hust.service.MenuManageService;

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
		paraMap.put("pid", parentId);
		paraMap.put("roleList", roleList);
		
		return (List<Menu>) baseDao.list(Menu.class.getName() + ".list", paraMap);
	}

}
