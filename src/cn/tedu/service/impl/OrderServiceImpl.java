package cn.tedu.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sun.org.apache.xml.internal.security.transforms.implementations.TransformBase64Decode;

import cn.tedu.dao.OrderDao;
import cn.tedu.dao.ProdDao;
import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;
import cn.tedu.domain.Product;
import cn.tedu.exception.MsgException;
import cn.tedu.service.OrderService;
import cn.tedu.utils.DaoUtils;
import cn.tedu.utils.TransManager;

public class OrderServiceImpl implements OrderService{

	private OrderDao orderDao = BasicFactory.getFactory().getInstance(OrderDao.class);
	private ProdDao prodDao = BasicFactory.getFactory().getInstance(ProdDao.class);
	public void addOrder(Order order, List<OrderItem> list) throws MsgException {
		//1���������ݿ����Ӷ���
		//Connection conn=null;
		try {
			//2����ȡ���Ӷ���
			//conn=DaoUtils.getConn();
			//3��������������ݿ������ύ��ʽ��Ϊ�ֶ�
			//conn.setAutoCommit(false);
			TransManager.startTran();
			//4����Order�������һ������
			orderDao.addOrder(order);
			//5������list
			for(OrderItem oi:list){
				//������Ʒid����ѯ��Ʒ��Ϣ
				Product pd=prodDao.findProdById(oi.getProd_id());
				//�ж���Ʒ�����Ƿ��㹻
				if(pd.getPnum()<oi.getBuyNum()){//������������ڿ�棬���쳣
					throw new MsgException("��Ʒ��治�㣬��Ʒ��Ϣ��"+pd.getId()+","+pd.getName()+","+pd.getPnum());
				}else{//������
					//�޸Ŀ��
					prodDao.changePnum(pd.getId(),pd.getPnum()-oi.getBuyNum());
					//��OrderItem�����һ����¼
					orderDao.addOrderItem(oi);
				}
			}
			TransManager.commitTran();
		} catch (MsgException e) {
			//6���ع�����
			TransManager.rollbackTran();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			TransManager.rollbackTran();
			
		} finally{
			TransManager.closeConn();
		}
		/*
		//1����orders�����һ������
		orderDao.addOrder(order);
		//2������List����
	    for (OrderItem orderItem : list) {
			//3��������Ʒ��id��ѯ��Ʒ����Ϣ
	    	Product prod = prodDao.findProdById(orderItem.getProd_id());
	    	//4���жϿ���Ƿ����
	    	if(prod.getPnum() < orderItem.getBuyNum()){//��治��
	    		throw new MsgException("��治�㣡��Ʒ��Ϣ��"+prod.getId()+","+prod.getName()+","+prod.getPnum());
	    	}else{//������
	    		//�޸Ŀ��
	    		prodDao.changePnum(prod.getId(), prod.getPnum()-orderItem.getBuyNum());
	    		//��orderitem�������һ����¼
	    		orderDao.addOrderItem(orderItem);
	    	}
		}
		*/
	}

	
}
