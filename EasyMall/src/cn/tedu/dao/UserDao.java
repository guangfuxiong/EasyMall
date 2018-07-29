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
	 * �����û�����ѯ�û���Ϣ
	 * 
	 * @param username
	 */
	public User findUserByUsername(String username);

	/**
	 * �����û���Ϣ�����ݿ�
	 * 
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * �����û�������������û�
	 * 
	 * @param username
	 * @param password
	 * @return User����
	 */
	public User findUserByUsernameAndPassword(
			String username, String password);

}
