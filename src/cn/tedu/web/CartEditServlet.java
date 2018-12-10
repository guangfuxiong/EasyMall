package cn.tedu.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Product;

/**
 * Servlet implementation class CartEditServlet
 */
public class CartEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		System.out.println("id:"+id);
		String newNum=request.getParameter("newNum");
		System.out.println("newNum:"+newNum);
		Product prod=new Product();
		prod.setId(id);
		Map<Product, Integer> cart=(Map<Product, Integer>) request.getSession().getAttribute("cart");
		if(cart.containsKey(prod)){
			cart.put(prod, Integer.parseInt(newNum));
			response.getWriter().write("true");
		}
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
