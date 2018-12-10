package cn.tedu.service.impl;

import cn.tedu.dao.UserDao;
import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.dao.impl.UserDaoImpl;
import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao dao = BasicFactory.getFactory().getInstance(UserDao.class);

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @throws MsgException
	 */
	public void registUser(User user)
			throws MsgException {
		// 检查用户名是否存在
		User u = dao.findUserByUsername(user
				.getUsername());

		if (u != null) {
			throw new MsgException("用户名已存在");
		}

		// 注册用户
		dao.addUser(user);
	}

	/**
	 * 登陆用户
	 * 
	 * @param password
	 * @param username
	 */
	public User loginUser(String username,
			String password) {
		return dao.findUserByUsernameAndPassword(
				username, password);
	}

	/**
	 * 根据用户名查询用户是否存在
	 * 
	 * @param username
	 * @return
	 */
	public boolean hasUser(String username) {
		return dao.findUserByUsername(username) != null;
	}
}
