package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.tedu.domain.User;
import cn.tedu.utils.DaoUtils;

public interface UserDao {

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 */
	public User findUserByUsername(String username);

	/**
	 * 保存用户信息到数据库
	 * 
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * 根据用户名和密码查找用户
	 * 
	 * @param username
	 * @param password
	 * @return User对象
	 */
	public User findUserByUsernameAndPassword(
			String username, String password);

}
