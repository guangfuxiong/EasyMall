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
		//����Product����
		Product prod=new Product();
		prod.setId(id);
		//��session��ȡ�ù��ﳵ����
		Map<Product,Integer> cart=(Map<Product, Integer>) request.getSession().getAttribute("cart");
		//�ӹ��ﳵ�������Ƴ�Ҫɾ����Product����
		if(cart.containsKey(prod)){
			cart.remove(prod);
			response.getWriter().write("true");
		}
		//request.getSession().setAttribute("cart", cart);
		//ת�������ﳵ�б�ҳ��
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
