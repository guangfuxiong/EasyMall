package cn.tedu.service.impl;

import java.io.File;
import java.util.List;

import cn.tedu.dao.ProdDao;
import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.domain.Product;
import cn.tedu.exception.MsgException;
import cn.tedu.service.ProdService;

public class ProdServiceImpl implements ProdService {
	private ProdDao prodDao = BasicFactory.getFactory().getInstance(ProdDao.class);
	public List<Product> findAll() {
		return prodDao.findAll();
	}
	public boolean changePnum(String pid, int pnum) {
		int row = prodDao.changePnum(pid,pnum);
		/*if(row==1){
			return true;
		}else{
			return false;
		}*/
		return row==1;
	}
	public void deleteProdById(String pid) throws MsgException {
		//调用dao层的方法，并返回影响的行数
		int row = prodDao.deleteProdById(pid);
		if(row!=1){
			throw new MsgException("删除失败!");
		}
	}
	public List<Product> findAllByKey(String name, String category,
			Double minprice, Double maxprice) {
		return prodDao.findAllByKey(name,category,minprice,maxprice);
	}
	public Product findProdById(String pid) {
		return prodDao.findProdById(pid);
	}
}
