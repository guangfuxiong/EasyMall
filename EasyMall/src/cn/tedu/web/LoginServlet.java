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
 * 处理登陆请求
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		// 1.解决请求参数乱码
		//request.setCharacterEncoding("utf-8");

		// 2.获取请求参数
		String username = request
				.getParameter("username");
		String password = request
				.getParameter("password");
		String remname = request
				.getParameter("remname");
		//A1、接收autologin
		String autologin = request.getParameter("autologin");

		// >>记住用户名
		if ("true".equals(remname)) {// 记住用户名 //URL编码 -- 将中文字符进行编码
			Cookie remnameCookie = new Cookie(
					"remname", URLEncoder.encode(
							username, "utf-8"));

			// 设置最大生存时间(保存30天)
			remnameCookie.setMaxAge(3600 * 24 * 30);

			// 设置path, 让浏览器访问当前WEB应用下任何一个资源都能带Cookie!!
			remnameCookie.setPath(request
					.getContextPath() + "/");

			// 将Cookie添加到response中发送给浏览器
			response.addCookie(remnameCookie);

		} else {// 取消记住用户名 -- 删除Cookie
			Cookie remnameCookie = new Cookie(
					"remname", "");
			remnameCookie.setMaxAge(0);
			remnameCookie.setPath(request
					.getContextPath() + "/");
			response.addCookie(remnameCookie);
		}

		// 3.调用Service层的方法根据用户名和密码查询用户
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		password = MD5Util.md5(password);
		User user = service.loginUser(username.trim(),
				password);
		if (user == null) { // 用户名或密码不正确
			request.setAttribute("msg", "用户名或密码不正确");
			request.getRequestDispatcher("/login.jsp")
					.forward(request, response);
			return;
		} else {
			//A2是否选择了30天自动登录
			if("true".equals(autologin)){//需要--添加自动的Cookie
				Cookie autoCk = new Cookie("autologin",
						URLEncoder.encode(username+":"+password, "UTF-8"));
				autoCk.setMaxAge(60*60*24*30);
				autoCk.setPath(request.getContextPath()+"/");
				response.addCookie(autoCk);
			}else{//不需要自动登录--删除自动登录对应的Cookie
				Cookie autoCk = new Cookie("autologin","");
				autoCk.setMaxAge(0);
				autoCk.setPath(request.getContextPath()+"/");
				response.addCookie(autoCk);
			}
			// 登陆用户
			request.getSession().setAttribute("user", user);
			// 登陆成功, 跳转回首页
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
