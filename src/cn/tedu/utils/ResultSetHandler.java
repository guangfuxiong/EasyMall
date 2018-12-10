package cn.tedu.utils;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface ResultSetHandler<T> {
	/**将结果集中的数据->T t  (User user,List<User> list)
	 * @param rs 结果集
	 * @return T 类型的对象 
	 * @throws SQLException
	 */
	T handle(ResultSet rs) throws SQLException;
}
