package cn.tedu.service;

import java.util.List;

import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;
import cn.tedu.exception.MsgException;

public interface OrderService {

	/**
	 * @param order：封装了订单信息的Order类对象
	 * @param list：封装了订单条目的集合对象
	 * @throws MsgException 库存不足时抛出自定义异常
	 */
	void addOrder(Order order,List<OrderItem> list) throws MsgException ;
}
