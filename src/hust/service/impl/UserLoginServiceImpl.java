package hust.service.impl;

import hust.service.UserLoginService;

public class UserLoginServiceImpl implements UserLoginService {

	@Override
	public boolean validateUser(String account, String passwd) {
		if("zs".equals(account) && "123".equals(passwd)) {
			return true;
		} else {
			return false;
		}
	}

}
