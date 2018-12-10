package cn.tedu.dao.impl;

import java.sql.Connection;
import java.util.List;

import cn.tedu.dao.ProdDao;
import cn.tedu.domain.Product;
import cn.tedu.utils.BeanHandler;
import cn.tedu.utils.BeanListHandler;
import cn.tedu.utils.DaoUtils;

public class ProdDaoImpl implements ProdDao {
	public List<Product> findAll() {
		String sql = "select * from products";
		return DaoUtils.query(sql,new BeanListHandler
				<Product>(Product.class));
	}
	public int changePnum(String pid, int pnum) {
		//1、编写sql语句
		String sql = "update products set pnum =? where id=?";
		//2、执行修改操作，并返回影响的行数
		return DaoUtils.update(sql,pnum,pid);
	}
	public int deleteProdById(String pid) {
		//1、编写sql语句
		String sql = "delete from products where id = ?";
		return DaoUtils.update(sql, pid);
	}
	public List<Product> findAllByKey(String name, String category,
			Double minprice, Double maxprice) {
		String sql = "select * from products where name like ? and category like ? ";
		if(minprice==null&&maxprice==null){
			return DaoUtils.query(sql, new BeanListHandler<Product>
				(Product.class), "%"+name+"%","%"+category+"%");
		}else if(minprice!=null&&maxprice==null){
			sql += " and price >=?";
			return DaoUtils.query(sql, new BeanListHandler<Product>
				(Product.class), "%"+name+"%","%"+category+"%",minprice);
		}else if(minprice==null&&maxprice!=null){
			sql +=" and price <= ?";
			return DaoUtils.query(sql, new BeanListHandler<Product>
				(Product.class), "%"+name+"%","%"+category+"%",maxprice);
		}else{
			sql +=" and price<=? and price>=?";
			return DaoUtils.query(sql, new BeanListHandler<Product>(Product.class),
					"%"+name+"%","%"+category+"%",maxprice,minprice);
		}
	}
	public Product findProdById(String pid) {
		String sql = "select * from products where id = ?";
		return DaoUtils.query(sql, new BeanHandler<Product>(Product.class), pid);
	}
}
