package cn.tedu.dao;

import java.sql.Connection;

import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;

public interface OrderDao {

	/**
	 * ��Orders�������һ��������Ϣ
	 */
	void addOrder(Order order);
	/**
	 * ��OrderItem�������һ��������Ŀ��Ϣ
	 */
	void addOrderItem(OrderItem orderItem);
}
