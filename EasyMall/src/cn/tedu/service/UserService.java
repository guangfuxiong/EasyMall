package cn.tedu.service;

import cn.tedu.dao.UserDao;
import cn.tedu.dao.factory.BasicFactory;
import cn.tedu.dao.impl.UserDaoImpl;
import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;

public interface UserService {
	/**
	 * ע���û�
	 * 
	 * @param user
	 * @throws MsgException
	 */
	public void registUser (User user) throws MsgException;
	/**
	 * ��½�û�
	 * 
	 * @param password
	 * @param username
	 */
	public User loginUser(String username,
			String password);
	
	/**
	 * �����û�����ѯ�û��Ƿ����
	 * 
	 * @param username
	 * @return
	 */
	public boolean hasUser(String username);
}
