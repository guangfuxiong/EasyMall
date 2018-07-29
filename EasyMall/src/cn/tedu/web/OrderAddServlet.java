package cn.tedu.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;
import cn.tedu.domain.Product;
import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.service.OrderService;

/**
 * Servlet implementation class OrderAddServlet
 */
public class OrderAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1���ж��û��Ƿ��¼
		Object userObj=request.getSession().getAttribute("user");
		if(userObj==null){
			request.setAttribute("msg", "��Ӷ���ǰ���ȵ�¼��");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}else{
			//�ѵ�¼�û�
			//��ȡ���ﳵ����
			Map<Product,Integer>  cart=(Map<Product, Integer>) request.getSession().getAttribute("cart");
			//������������Ϊ�����Ը�ֵ
			Order order=new Order();
			order.setId(UUID.randomUUID().toString());
			order.setOrdertime(new Date());
			order.setPaystate(0);
			order.setUser_id(((User)userObj).getId());
			order.setReceiverinfo((String)request.getAttribute("receiverinfo"));
			double money=0;
			//����Map����cart,����money,��ȡ������Ŀ
			List<OrderItem> list=new ArrayList<OrderItem>();
			for(Map.Entry<Product, Integer> entry:cart.entrySet() ){
				OrderItem oi=new OrderItem();
				oi.setOrder_id(order.getId());
				oi.setProd_id(entry.getKey().getId());
				oi.setBuyNum(entry.getValue());
				//��oi��ӵ�list��
				list.add(oi);
				money+=entry.getKey().getPrice();
			}
			//Ϊmoney��ֵ
			order.setMoney(money);
			try {
				//����ҵ��㷽��
				OrderService orderService=BasicFactory.getFactory().getInstance(OrderService.class);
				orderService.addOrder(order, list);
				//��չ��ﳵ
				request.setAttribute("cart", null);
				//��ʾ��תҳ��
				response.getWriter().write("������ӳɹ���3�����ת");
				response.setHeader("Refresh", "3;url="+request.getContextPath()+"/order_add.jsp");
			} catch (MsgException e) {
				//��Ӷ���ʧ�ܣ���治�㣩
				request.setAttribute("msg", e.getMessage());
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
