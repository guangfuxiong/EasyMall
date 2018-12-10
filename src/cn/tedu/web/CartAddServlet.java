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
		//��ȡ�ύ�Ĳ���
		String id=request.getParameter("pid");
		//����ҵ������
		ProdService ps=BasicFactory.getFactory().getInstance(ProdService.class);
		//����ҵ��㷽����ͨ��id��ѯ��Ʒ����
		Product prod=ps.findProdById(id);
		//�������ﳵ����
		Map<Product,Integer> cart=null;
		//��ȡsession�е�cart����
		Object objCart=request.getSession().getAttribute("cart");
		if(null!=objCart){
			//session��������˹��ﳵ
			cart=(Map<Product,Integer>)objCart;
		}else{
			cart=new HashMap<Product, Integer>();
			request.getSession().setAttribute("cart", cart);
		}
		//���ˣ�session�п϶�����cart����
		//�ж�cart���������޲�ѯ�����Ķ���
		if(cart.containsKey(prod)){//֮ǰ����Ʒ�Ѽ����˹��ﳵ
			cart.put(prod, cart.get(prod)+1);
		}else{
			cart.put(prod, 1);
		}
		//�ض��򵽹��ﳵ�б�ҳ��
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
