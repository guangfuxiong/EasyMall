package cn.tedu.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class AjaxProdEditPnumServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、接收参数
		String pid = request.getParameter("pid");
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		//2、调用业务成的方法
		//2.1声明并实例化业务层的对象
		ProdService prodService = BasicFactory.getFactory().getInstance(ProdService.class);
		//2.2调用对应的方法并发执行的结果
		boolean result = prodService.changePnum(pid,pnum);
		//3、将结果输出
		response.getWriter().write(result+"");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
