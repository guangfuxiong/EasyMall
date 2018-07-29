package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.service.UserService;

/**
 * ����ajax����û����Ƿ����
 */
public class AjaxCheckUsernameServlet extends
		HttpServlet {
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		// 1.��������
		// ���������������
		//request.setCharacterEncoding("utf-8");
		// ������Ӧ��������
		//response.setContentType("text/html;charset=utf-8");

		// 2.��ȡ�������
		String username = request.getParameter("username");
		
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		
		//����service��ķ�����ѯ�û����Ƿ����
		if (service.hasUser(username)) { // �û�������
			response.getWriter()
					.write("<font style='color:red'>�û����Ѵ���!</font>");
		} else {
			response.getWriter()
					.write("<font style='color:red'>��ϲ, �û�������ʹ��!</font>");
		}
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
