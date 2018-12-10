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
 * ����ע�������Servlet
 */
public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 1.�������
			// >>��Ӧ��������
			//response.setContentType("text/html;charset=utf-8");
			// >>�����������(post�ύ)
			//request.setCharacterEncoding("utf-8");

			// 2.У����֤��
			// ��ȡ�û��ύ����֤��
			String valistr1 = request
					.getParameter("valistr");

			// ��ȡ������session�е���֤��
			String valistr2 = (String) request
					.getSession().getAttribute(
							"valistr");

			// >>�����֤���Ƿ�Ϊ��
			if (WebUtils.isNull(valistr1)) {// У��ʧ��
				// ������ʾ��Ϣ
				request.setAttribute("msg", "��֤�벻��Ϊ��");
				// ת����ע��ҳ�������ʾ
				request.getRequestDispatcher(
						"/regist.jsp").forward(
						request, response);
				return;
			}
			// >>��֤���Ƿ���ȷ
			if (!valistr1.equalsIgnoreCase(valistr2)) {
				// ������ʾ��Ϣ
				request.setAttribute("msg", "��֤�벻��ȷ");
				// ת����ע��ҳ�������ʾ
				request.getRequestDispatcher(
						"/regist.jsp").forward(
						request, response);
				return;
			}

			// 3.��װ���ݵ�javaBean(����BeanUtils)
			User user = new User();
			BeanUtils.populate(user,
					request.getParameterMap());
			System.out.println(user);

			// 4.����JavaBean�еķ���У������
			user.checkData();

			// 5.����Service��ķ��������ݱ�������ݿ�
			
			UserService service = BasicFactory.getFactory().getInstance(UserService.class);
			//���������
			user.setPassword(MD5Util.md5(user.getPassword()));
			service.registUser(user);

			// 6.ע��ɹ�, ��ʾ�û���ת����ҳ
			response.getWriter()
					.write("<h1 style='text-align:center; color:red;'>��ϲ��, ע��ɹ�! 3��֮����ת����ҳ...</h1>");
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
