package cn.tedu.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.domain.Product;
import cn.tedu.service.ProdService;

/**
 * Servlet implementation class CartAddServlet
 */
public class CartAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取提交的参数
		String id=request.getParameter("pid");
		//创建业务层对象
		ProdService ps=BasicFactory.getFactory().getInstance(ProdService.class);
		//调用业务层方法，通过id查询商品对象
		Product prod=ps.findProdById(id);
		//声明购物车对象
		Map<Product,Integer> cart=null;
		//获取session中的cart对象
		Object objCart=request.getSession().getAttribute("cart");
		if(null!=objCart){
			//session中已添加了购物车
			cart=(Map<Product,Integer>)objCart;
		}else{
			cart=new HashMap<Product, Integer>();
			request.getSession().setAttribute("cart", cart);
		}
		//至此，session中肯定存在cart对象
		//判断cart对象中有无查询出来的对象
		if(cart.containsKey(prod)){//之前将商品已加入了购物车
			cart.put(prod, cart.get(prod)+1);
		}else{
			cart.put(prod, 1);
		}
		//重定向到购物车列表页面
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
