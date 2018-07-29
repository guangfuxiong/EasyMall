package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.domain.Product;
import cn.tedu.service.ProdService;

public class ProdListServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、接收参数
		String nameStr = request.getParameter("name");
		String cateStr= request.getParameter("category");
		String minpriceStr = request.getParameter("minprice");
		String maxpriceStr = request.getParameter("maxprice");
		//2、为null处理
		String name = "";
		String category = "";
		if(nameStr!=null&&!"".equals(nameStr.trim())){
			name = nameStr;
		}
		if(cateStr!=null&&!"".equals(cateStr)){
			category = cateStr;
		}
		Double minprice = null;
		Double maxprice = null;
		if(minpriceStr!=null&&!"".equals(minpriceStr.trim())){
			minprice = Double.parseDouble(minpriceStr.trim());
		}
		if(maxpriceStr!=null&&!"".equals(maxpriceStr.trim())){
			maxprice = Double.parseDouble(maxpriceStr.trim());
		}
		//3、声明并实例化业务层对象
		ProdService prodService = BasicFactory.getFactory().
				getInstance(ProdService.class);
		//4、调用根据关键字查询的方法
		List<Product> list = prodService.findAllByKey(name,category,
				minprice,maxprice);
		//5、将查询结果和查询关键字保存到request
		request.setAttribute("list", list);
		request.setAttribute("name", name);
		request.setAttribute("category", category);
		request.setAttribute("minprice", minprice);
		request.setAttribute("maxprice", maxprice);
		//6、转发到商品列表页
		request.getRequestDispatcher("/prod_list.jsp").forward(request, response);
	}
}
