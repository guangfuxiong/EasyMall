package cn.tedu.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.tedu.dao.UserDao;
import cn.tedu.domain.User;
import cn.tedu.utils.BeanHandler;
import cn.tedu.utils.DaoUtils;

public class UserDaoImpl implements UserDao {
	public void addUser(User user) {
		//1、编写sql语句
		String sql = "insert into user(username,password,nickname,email)" +
				" values(?, ?, ?, ?)";
		//2、调用DaoUtils类的update方法
		DaoUtils.update(sql,user.getUsername(),user.getPassword(),
				user.getNickname(),user.getEmail());
	}
	public User findUserByUsername(String username) {
		String sql = "select * from user where username=?";
		return  DaoUtils.query(sql,new BeanHandler<User>(User.class),
				username);
	}
	public User findUserByUsernameAndPassword(
			String username, String password) {
		String sql = "select * from user where username=? and password=?";
		return DaoUtils.query(sql,new BeanHandler<User>(User.class),
				username,password);
	}

}
