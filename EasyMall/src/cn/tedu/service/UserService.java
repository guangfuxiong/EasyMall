package cn.tedu.service;

import cn.tedu.dao.UserDao;
import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.dao.impl.UserDaoImpl;
import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;

public interface UserService {
	/**
	 * 注册用户
	 * 
	 * @param user
	 * @throws MsgException
	 */
	public void registUser (User user) throws MsgException;
	/**
	 * 登陆用户
	 * 
	 * @param password
	 * @param username
	 */
	public User loginUser(String username,
			String password);
	
	/**
	 * 根据用户名查询用户是否存在
	 * 
	 * @param username
	 * @return
	 */
	public boolean hasUser(String username);
}
