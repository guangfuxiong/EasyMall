package cn.tedu.service;

import java.util.List;

import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;
import cn.tedu.exception.MsgException;

public interface OrderService {

	/**
	 * @param order����װ�˶�����Ϣ��Order�����
	 * @param list����װ�˶�����Ŀ�ļ��϶���
	 * @throws MsgException ��治��ʱ�׳��Զ����쳣
	 */
	void addOrder(Order order,List<OrderItem> list) throws MsgException ;
}
