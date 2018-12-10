package cn.tedu.dao;

import java.sql.Connection;

import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;

public interface OrderDao {

	/**
	 * 向Orders表中添加一条订单信息
	 */
	void addOrder(Order order);
	/**
	 * 向OrderItem表中添加一条订单条目信息
	 */
	void addOrderItem(OrderItem orderItem);
}
