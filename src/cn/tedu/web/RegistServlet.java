package cn.tedu.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.service.UserService;
import cn.tedu.utils.DaoUtils;
import cn.tedu.utils.MD5Util;
import cn.tedu.utils.WebUtils;

/**
 * 处理注册请求的Servlet
 */
public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 1.解决乱码
			// >>响应正文乱码
			//response.setContentType("text/html;charset=utf-8");
			// >>请求参数乱码(post提交)
			//request.setCharacterEncoding("utf-8");

			// 2.校验验证码
			// 获取用户提交的验证码
			String valistr1 = request
					.getParameter("valistr");

			// 获取保存在session中的验证码
			String valistr2 = (String) request
					.getSession().getAttribute(
							"valistr");

			// >>检查验证码是否为空
			if (WebUtils.isNull(valistr1)) {// 校验失败
				// 设置提示消息
				request.setAttribute("msg", "验证码不能为空");
				// 转发回注册页面进行提示
				request.getRequestDispatcher(
						"/regist.jsp").forward(
						request, response);
				return;
			}
			// >>验证码是否正确
			if (!valistr1.equalsIgnoreCase(valistr2)) {
				// 设置提示消息
				request.setAttribute("msg", "验证码不正确");
				// 转发回注册页面进行提示
				request.getRequestDispatcher(
						"/regist.jsp").forward(
						request, response);
				return;
			}

			// 3.封装数据到javaBean(利用BeanUtils)
			User user = new User();
			BeanUtils.populate(user,
					request.getParameterMap());
			System.out.println(user);

			// 4.调用JavaBean中的方法校验数据
			user.checkData();

			// 5.调用Service层的方法将数据保存进数据库
			
			UserService service = BasicFactory.getFactory().getInstance(UserService.class);
			//将密码加密
			user.setPassword(MD5Util.md5(user.getPassword()));
			service.registUser(user);

			// 6.注册成功, 提示用户跳转回首页
			response.getWriter()
					.write("<h1 style='text-align:center; color:red;'>恭喜您, 注册成功! 3秒之后跳转到首页...</h1>");
			response.setHeader("refresh", "3;url=" + request.getContextPath()
					+ "/index.jsp");

		} catch (MsgException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/regist.jsp")
					.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
