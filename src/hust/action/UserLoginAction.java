package hust.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import hust.service.UserLoginService;

import com.opensymphony.xwork2.ActionContext;

public class UserLoginAction implements ServletResponseAware {
	
	private String account;
	private String passwd;
	private String vcode;
	
	HttpServletResponse response;
	
	@Autowired
	private UserLoginService userLoginService;
	
	/*
	 * 提交ajax请求，不需要返回页面
	 */
	public String execute() throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		PrintWriter out = response.getWriter();
		
		String realVcode = (String) session.get("vcode");
		if (!realVcode.equalsIgnoreCase(vcode)) {
			out.print("vcode error");
			out.close();
			return null;
		}
		
		if (!userLoginService.validateUser(account, passwd)) {
			out.print("userpass error");
			out.close();
			return null;
		}
		
		//将用户信息放入session
		session.remove("vcode");
		session.put("loginUser", "zs");
		session.put("skin", "default");
		
		out.print("success");
		return null;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}
