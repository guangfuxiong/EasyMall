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
	 * 获取连接
	 * @return
	 */
	public static Connection getConn(){
		return conn;
	}
	/**
	 * 开启事物
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
	 * 提交事物
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
	 * 回滚事物
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
