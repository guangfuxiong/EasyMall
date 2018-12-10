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
		//1、接收参数
		String pid = request.getParameter("pid");
		//2、声明并实例化业务层对象
		ProdService prodService = BasicFactory.getFactory().getInstance(ProdService.class);
		//3、调用对应的方法
		Product prod = prodService.findProdById(pid);
		//4、将prod保存到request作用域中
		request.setAttribute("prod", prod);
		//5、转发到prod_info.jsp
		request.getRequestDispatcher("/prod_info.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
