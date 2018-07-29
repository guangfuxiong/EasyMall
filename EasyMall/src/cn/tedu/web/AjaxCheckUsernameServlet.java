package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.service.UserService;

/**
 * 利用ajax检查用户名是否存在
 */
public class AjaxCheckUsernameServlet extends
		HttpServlet {
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		// 1.处理乱码
		// 处理请求参数乱码
		//request.setCharacterEncoding("utf-8");
		// 处理响应数据乱码
		//response.setContentType("text/html;charset=utf-8");

		// 2.获取请求参数
		String username = request.getParameter("username");
		
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		
		//调用service层的方法查询用户名是否存在
		if (service.hasUser(username)) { // 用户名存在
			response.getWriter()
					.write("<font style='color:red'>用户名已存在!</font>");
		} else {
			response.getWriter()
					.write("<font style='color:red'>恭喜, 用户名可以使用!</font>");
		}
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
