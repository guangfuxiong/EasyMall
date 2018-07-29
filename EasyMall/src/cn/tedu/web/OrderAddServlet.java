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
		//1、判断用户是否登录
		Object userObj=request.getSession().getAttribute("user");
		if(userObj==null){
			request.setAttribute("msg", "添加订单前请先登录！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}else{
			//已登录用户
			//获取购物车对象
			Map<Product,Integer>  cart=(Map<Product, Integer>) request.getSession().getAttribute("cart");
			//创建订单对象并为其属性赋值
			Order order=new Order();
			order.setId(UUID.randomUUID().toString());
			order.setOrdertime(new Date());
			order.setPaystate(0);
			order.setUser_id(((User)userObj).getId());
			order.setReceiverinfo((String)request.getAttribute("receiverinfo"));
			double money=0;
			//遍历Map集合cart,计算money,获取订单条目
			List<OrderItem> list=new ArrayList<OrderItem>();
			for(Map.Entry<Product, Integer> entry:cart.entrySet() ){
				OrderItem oi=new OrderItem();
				oi.setOrder_id(order.getId());
				oi.setProd_id(entry.getKey().getId());
				oi.setBuyNum(entry.getValue());
				//把oi添加到list中
				list.add(oi);
				money+=entry.getKey().getPrice();
			}
			//为money赋值
			order.setMoney(money);
			try {
				//调用业务层方法
				OrderService orderService=BasicFactory.getFactory().getInstance(OrderService.class);
				orderService.addOrder(order, list);
				//清空购物车
				request.setAttribute("cart", null);
				//提示跳转页面
				response.getWriter().write("订单添加成功，3秒后跳转");
				response.setHeader("Refresh", "3;url="+request.getContextPath()+"/order_add.jsp");
			} catch (MsgException e) {
				//添加订单失败（库存不足）
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
