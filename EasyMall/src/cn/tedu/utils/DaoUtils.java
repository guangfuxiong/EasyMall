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
 * JDBC������
 */
public class DaoUtils {
	private static DataSource pool = new ComboPooledDataSource();
	private static Properties prop = new Properties();
	private DaoUtils(){}
	
	/**
	 * ��ȡ�����е����ӳ�ʵ��
	 * @return DataSource
	 */
	public static DataSource getPool(){
		return pool;
	}
	
	/**
	 * �����ӳ��л�ȡһ������ʵ��
	 * @return Connection
	 * @throws Exception
	 */
	public static Connection getConn() throws Exception{
		return pool.getConnection();
	}
	/**��ѯ������������һ������Ͳ�������
	 * @param sql����ѯ��sql���
	 * @param params�������б�
	 * @return �������T t ���磺User��List<User>  ��
	 */
	public static <T> T query(String sql,ResultSetHandler<T> rsh,Object... params){
		//1��������������conn,ps,rs
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			//2����ȡ���ݿ�����
			conn = TransManager.getConn();
			//3��Ԥ����sqlԤ�ƣ����������ݲ�����ps
			ps = conn.prepareStatement(sql);
			//4��Ϊռλ����ֵ
			for(int i = 0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			//5��ִ�в�ѯ�����������ؽ����
			rs = ps.executeQuery();
			//rs ->T t
			//6�����ؽ��
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
	/**ʵ�����/�޸�/ɾ�� ĳһ����¼.
	 * @param sql:sql���
	 * @param params�����������Ĳ����б�
	 * @return Ӱ�������
	 * @throws SQLException
	 */
	public static int update(String sql,Object... params){
		//1���������ݿ����Ӷ���conn,"���ݵİ��˹�"ps
		Connection conn = null;
		PreparedStatement ps =null;
		try {
			//2����ȡ���ݿ�����
			conn = TransManager.getConn();
			//3Ԥ����sql��䣬���������ݵİ��˹�
			ps = conn.prepareStatement(sql);
			//4��Ϊռλ����ֵ
			for(int i =0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			//5��ִ�в�����������Ӱ�������
			int row = ps.executeUpdate();
			//6�����������
			return row;
		} catch (Exception e) {
			e.printStackTrace();
			//6�����ؽ��
			return 0;
		}finally{
			//7���ر����ݿ�������صĶ���
			close(null, ps, null);
		}
	}
	/**
	 * ���߷���: �ͷ���Դ
	 * @param conn ���Ӷ���
	 * @param stat ����������
	 * @param rs ���������
	 */
	public static void close(Connection conn, Statement stat, ResultSet rs){
		// �ͷ���Դ
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
