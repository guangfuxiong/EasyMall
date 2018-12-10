package cn.tedu.utils;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface ResultSetHandler<T> {
	/**��������е�����->T t  (User user,List<User> list)
	 * @param rs �����
	 * @return T ���͵Ķ��� 
	 * @throws SQLException
	 */
	T handle(ResultSet rs) throws SQLException;
}
