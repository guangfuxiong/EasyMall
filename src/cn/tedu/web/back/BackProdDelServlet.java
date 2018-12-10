package cn.tedu.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.exception.MsgException;
import cn.tedu.service.ProdService;

public class BackProdDelServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、接收商品id
		String pid = request.getParameter("pid");
		//2、创建业务层对象
		ProdService prodService = BasicFactory.getFactory().
				getInstance(ProdService.class);
		try{
			//3、调用删除的方法
			prodService.deleteProdById(pid);
			//4、删除成功
			response.getWriter().write("删除成功！3秒后自动自动跳转，如果没有跳" +
					"转，请<a href='"+request.getContextPath()+"/servlet/BackProdListServlet'>点击此次</a>");
		}catch (MsgException e) {
			//4、删除失败
			response.getWriter().write(e.getMessage()+"3秒后自动自动跳转，如果没有跳" +
					"转，请<a href='"+request.getContextPath()+"/servlet/BackProdListServlet'>点击此次</a>");
		}
		//5、设置自动跳转
		response.setHeader("Refresh", "3;url="+
		    request.getContextPath()+"/servlet/BackProdListServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
