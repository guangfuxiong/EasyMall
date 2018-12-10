package cn.tedu.dao.impl;

import java.sql.Connection;

import cn.tedu.dao.OrderDao;
import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;
import cn.tedu.utils.DaoUtils;

public class OrderDaoImpl implements OrderDao{

	public void addOrder(Order order) {
		String sql = "insert into orders(id,money,receiverinfo," +
				"paystate,ordertime,user_id) values(?,?,?,?,?,?)";
		DaoUtils.update(sql, order.getId(),order.getMoney(),order.getReceiverinfo(),
				order.getPaystate(),order.getOrdertime(),order.getUser_id());
	}
	public void addOrderItem(OrderItem orderItem) {
		String sql = "insert into orderitem(order_id,product_id,buynum) values(?,?,?)";
		DaoUtils.update(sql, orderItem.getOrder_id(),orderItem.getProd_id(),orderItem.getBuyNum());
	}
}
