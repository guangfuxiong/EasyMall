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
	 * ע���û�
	 * 
	 * @param user
	 * @throws MsgException
	 */
	public void registUser(User user)
			throws MsgException {
		// ����û����Ƿ����
		User u = dao.findUserByUsername(user
				.getUsername());

		if (u != null) {
			throw new MsgException("�û����Ѵ���");
		}

		// ע���û�
		dao.addUser(user);
	}

	/**
	 * ��½�û�
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
	 * �����û�����ѯ�û��Ƿ����
	 * 
	 * @param username
	 * @return
	 */
	public boolean hasUser(String username) {
		return dao.findUserByUsername(username) != null;
	}
}
