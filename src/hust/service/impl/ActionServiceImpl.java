package hust.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import hust.dao.BaseDao;
import hust.model.Action;
import hust.service.ActionService;

@SuppressWarnings({"rawtypes","unchecked"})
public class ActionServiceImpl implements ActionService {

	@Autowired
	BaseDao baseDao;
	
	@Override
	public List<Action> getActions(Action action) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("action", action);
		return baseDao.list(Action.class.getName() + ".list", paraMap);
	}

}
