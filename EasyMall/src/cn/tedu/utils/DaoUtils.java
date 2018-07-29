package cn.tedu.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import cn.tedu.domain.User;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC工具类
 */
public class DaoUtils {
	private static DataSource pool = new ComboPooledDataSource();
	private static Properties prop = new Properties();
	private DaoUtils(){}
	
	/**
	 * 获取程序中的连接池实例
	 * @return DataSource
	 */
	public static DataSource getPool(){
		return pool;
	}
	
	/**
	 * 从连接池中获取一个连接实例
	 * @return Connection
	 * @throws Exception
	 */
	public static Connection getConn() throws Exception{
		return pool.getConnection();
	}
	/**查询方法（包括查一个对象和查多个对象）
	 * @param sql：查询的sql语句
	 * @param params：参数列表
	 * @return 结果集（T t 比如：User或List<User>  ）
	 */
	public static <T> T query(String sql,ResultSetHandler<T> rsh,Object... params){
		//1、声明三个对象：conn,ps,rs
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			//2、获取数据库链接
			conn = TransManager.getConn();
			//3、预编译sql预计，并返回数据操作者ps
			ps = conn.prepareStatement(sql);
			//4、为占位符赋值
			for(int i = 0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			//5、执行查询操作，并返回结果集
			rs = ps.executeQuery();
			//rs ->T t
			//6、返回结果
			return rsh.handle(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(null, ps, rs);
		}
		return null;
	}
	public static void main(String[] args) {
		String sql = "select * from user";
		List<User> list = query(sql, new BeanListHandler<User>(User.class));
		for (User user : list) {
			System.out.println(user.getId()+","+user.getEmail()+","+user.getUsername()+","+user.getNickname()+","+user.getPassword());
		}
	}
	/**实现添加/修改/删除 某一条记录.
	 * @param sql:sql语句
	 * @param params：不定个数的参数列表
	 * @return 影响的行数
	 * @throws SQLException
	 */
	public static int update(String sql,Object... params){
		//1、声明数据库连接对象conn,"数据的搬运工"ps
		Connection conn = null;
		PreparedStatement ps =null;
		try {
			//2、获取数据库连接
			conn = TransManager.getConn();
			//3预编译sql语句，并返回数据的搬运工
			ps = conn.prepareStatement(sql);
			//4、为占位符赋值
			for(int i =0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			//5、执行操作，并返回影响的行数
			int row = ps.executeUpdate();
			//6、将结果返回
			return row;
		} catch (Exception e) {
			e.printStackTrace();
			//6、返回结果
			return 0;
		}finally{
			//7、关闭数据库连接相关的对象
			close(null, ps, null);
		}
	}
	/**
	 * 工具方法: 释放资源
	 * @param conn 连接对象
	 * @param stat 传输器对象
	 * @param rs 结果集对象
	 */
	public static void close(Connection conn, Statement stat, ResultSet rs){
		// 释放资源
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				stat = null;
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
}
