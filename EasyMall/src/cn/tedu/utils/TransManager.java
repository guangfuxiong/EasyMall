package cn.tedu.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class TransManager {

	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>(){
		protected Connection initialValue(){
			try {
				return DaoUtils.getConn();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	};
	/**
	 * ��ȡ����
	 * @return
	 */
	public static Connection getConn(){
		return tl.get();
	}
	/**
	 * ��������
	 */
	public static void startTran(){
		try {
			tl.get().setAutoCommit(false);
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
			tl.get().commit();
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
			tl.get().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeConn(){
		try {
			tl.get().close();
			tl.remove();//map{tl:conn}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
