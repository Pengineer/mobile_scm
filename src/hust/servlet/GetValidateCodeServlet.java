package hust.servlet;

import hust.utils.ValidateCodeGenerator;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码的获取和更换比较简单，就不用action来实现了，直接使用servlet
 * @author liangjian
 *
 */
public class GetValidateCodeServlet extends HttpServlet {

	private static final long serialVersionUID = -3369041574040076410L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		OutputStream os = response.getOutputStream();
		ValidateCodeGenerator vg = new ValidateCodeGenerator(os);
		String codes = vg.drawCode();
		request.getSession().setAttribute("vcode", codes);
		os.close();
	}
}
