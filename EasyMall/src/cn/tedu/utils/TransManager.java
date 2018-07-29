package cn.tedu.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class TransManager {

	private static Connection conn=null;
	static{
		try {
			conn=DaoUtils.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ����
	 * @return
	 */
	public static Connection getConn(){
		return conn;
	}
	/**
	 * ��������
	 */
	public static void startTran(){
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �ύ����
	 */
	public static void commitTran(){
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �ع�����
	 */
	public static void rollbackTran(){
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeConn(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
