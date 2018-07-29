package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//ע���û���½״̬
		if(request.getSession(false) != null){
			request.getSession().invalidate();
		}
		//cookie����
		Cookie autoCk = new Cookie("autologin","");
		autoCk.setMaxAge(0);
		autoCk.setPath(request.getContextPath()+"/");
		response.addCookie(autoCk);
		//��ת����ҳ
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
