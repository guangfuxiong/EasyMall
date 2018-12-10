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
		//1、声明数据库连接对象
		//Connection conn=null;
		try {
			//2、获取连接对象
			//conn=DaoUtils.getConn();
			//3、开启事物：将数据库事物提交方式改为手动
			//conn.setAutoCommit(false);
			TransManager.startTran();
			//4、向Order表中添加一条数据
			orderDao.addOrder(order);
			//5、遍历list
			for(OrderItem oi:list){
				//根据商品id，查询商品信息
				Product pd=prodDao.findProdById(oi.getProd_id());
				//判断商品数量是否足够
				if(pd.getPnum()<oi.getBuyNum()){//购买的数量大于库存，抛异常
					throw new MsgException("商品库存不足，商品信息："+pd.getId()+","+pd.getName()+","+pd.getPnum());
				}else{//库存充足
					//修改库存
					prodDao.changePnum(pd.getId(),pd.getPnum()-oi.getBuyNum());
					//往OrderItem中添加一条记录
					orderDao.addOrderItem(oi);
				}
			}
			TransManager.commitTran();
		} catch (MsgException e) {
			//6、回滚事物
			TransManager.rollbackTran();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			TransManager.rollbackTran();
			
		} finally{
			TransManager.closeConn();
		}
		/*
		//1、向orders表添加一条数据
		orderDao.addOrder(order);
		//2、遍历List集合
	    for (OrderItem orderItem : list) {
			//3、根据商品的id查询商品的信息
	    	Product prod = prodDao.findProdById(orderItem.getProd_id());
	    	//4、判断库存是否充足
	    	if(prod.getPnum() < orderItem.getBuyNum()){//库存不足
	    		throw new MsgException("库存不足！商品信息："+prod.getId()+","+prod.getName()+","+prod.getPnum());
	    	}else{//库存充足
	    		//修改库存
	    		prodDao.changePnum(prod.getId(), prod.getPnum()-orderItem.getBuyNum());
	    		//向orderitem表中添加一条记录
	    		orderDao.addOrderItem(orderItem);
	    	}
		}
		*/
	}

	
}
