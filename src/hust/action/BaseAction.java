package hust.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

//无需实例化
public class BaseAction extends ActionSupport implements ServletResponseAware, ServletRequestAware, ServletContextAware, SessionAware {

	private static final long serialVersionUID = -8354742755236139622L;

	// 供子类继承，使用protected修饰
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;
	protected Map<String, Object> session;
	
	@Override
	public void setServletContext(ServletContext application) {
		this.application = application;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
