package hust.tool.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ServletContextListener属于javax.servlet.jar包中的接口
 * 
 * @author liangjian
 *
 */
public class SysInit implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext application = event.getServletContext();
		application.setAttribute("sysname", application.getInitParameter("sysname"));
	}

}
