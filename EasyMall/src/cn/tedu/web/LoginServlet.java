package cn.tedu.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.domain.User;
import cn.tedu.service.UserService;
import cn.tedu.utils.MD5Util;

/**
 * �����½����
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		// 1.��������������
		//request.setCharacterEncoding("utf-8");

		// 2.��ȡ�������
		String username = request
				.getParameter("username");
		String password = request
				.getParameter("password");
		String remname = request
				.getParameter("remname");
		//A1������autologin
		String autologin = request.getParameter("autologin");

		// >>��ס�û���
		if ("true".equals(remname)) {// ��ס�û��� //URL���� -- �������ַ����б���
			Cookie remnameCookie = new Cookie(
					"remname", URLEncoder.encode(
							username, "utf-8"));

			// �����������ʱ��(����30��)
			remnameCookie.setMaxAge(3600 * 24 * 30);

			// ����path, ����������ʵ�ǰWEBӦ�����κ�һ����Դ���ܴ�Cookie!!
			remnameCookie.setPath(request
					.getContextPath() + "/");

			// ��Cookie��ӵ�response�з��͸������
			response.addCookie(remnameCookie);

		} else {// ȡ����ס�û��� -- ɾ��Cookie
			Cookie remnameCookie = new Cookie(
					"remname", "");
			remnameCookie.setMaxAge(0);
			remnameCookie.setPath(request
					.getContextPath() + "/");
			response.addCookie(remnameCookie);
		}

		// 3.����Service��ķ��������û����������ѯ�û�
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		password = MD5Util.md5(password);
		User user = service.loginUser(username.trim(),
				password);
		if (user == null) { // �û��������벻��ȷ
			request.setAttribute("msg", "�û��������벻��ȷ");
			request.getRequestDispatcher("/login.jsp")
					.forward(request, response);
			return;
		} else {
			//A2�Ƿ�ѡ����30���Զ���¼
			if("true".equals(autologin)){//��Ҫ--����Զ���Cookie
				Cookie autoCk = new Cookie("autologin",
						URLEncoder.encode(username+":"+password, "UTF-8"));
				autoCk.setMaxAge(60*60*24*30);
				autoCk.setPath(request.getContextPath()+"/");
				response.addCookie(autoCk);
			}else{//����Ҫ�Զ���¼--ɾ���Զ���¼��Ӧ��Cookie
				Cookie autoCk = new Cookie("autologin","");
				autoCk.setMaxAge(0);
				autoCk.setPath(request.getContextPath()+"/");
				response.addCookie(autoCk);
			}
			// ��½�û�
			request.getSession().setAttribute("user", user);
			// ��½�ɹ�, ��ת����ҳ
			response.sendRedirect(request.getContextPath() 
					+ "/index.jsp");
		}
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
