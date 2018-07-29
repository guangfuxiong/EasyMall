package cn.tedu.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Product;

/**
 * Servlet implementation class CartDelServlet
 */
public class CartDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		//创建Product对象
		Product prod=new Product();
		prod.setId(id);
		//从session里取得购物车对象
		Map<Product,Integer> cart=(Map<Product, Integer>) request.getSession().getAttribute("cart");
		//从购物车对象中移除要删除的Product对象
		if(cart.containsKey(prod)){
			cart.remove(prod);
			response.getWriter().write("true");
		}
		//request.getSession().setAttribute("cart", cart);
		//转发到购物车列表页面
	    //request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
