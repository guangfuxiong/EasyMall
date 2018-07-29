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
		//1�����ղ���
		String nameStr = request.getParameter("name");
		String cateStr= request.getParameter("category");
		String minpriceStr = request.getParameter("minprice");
		String maxpriceStr = request.getParameter("maxprice");
		//2��Ϊnull����
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
		//3��������ʵ����ҵ������
		ProdService prodService = BasicFactory.getFactory().
				getInstance(ProdService.class);
		//4�����ø��ݹؼ��ֲ�ѯ�ķ���
		List<Product> list = prodService.findAllByKey(name,category,
				minprice,maxprice);
		//5������ѯ����Ͳ�ѯ�ؼ��ֱ��浽request
		request.setAttribute("list", list);
		request.setAttribute("name", name);
		request.setAttribute("category", category);
		request.setAttribute("minprice", minprice);
		request.setAttribute("maxprice", maxprice);
		//6��ת������Ʒ�б�ҳ
		request.getRequestDispatcher("/prod_list.jsp").forward(request, response);
	}
}
