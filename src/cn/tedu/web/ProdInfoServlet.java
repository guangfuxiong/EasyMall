package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.domain.Product;
import cn.tedu.service.ProdService;

public class ProdInfoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1�����ղ���
		String pid = request.getParameter("pid");
		//2��������ʵ����ҵ������
		ProdService prodService = BasicFactory.getFactory().getInstance(ProdService.class);
		//3�����ö�Ӧ�ķ���
		Product prod = prodService.findProdById(pid);
		//4����prod���浽request��������
		request.setAttribute("prod", prod);
		//5��ת����prod_info.jsp
		request.getRequestDispatcher("/prod_info.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
