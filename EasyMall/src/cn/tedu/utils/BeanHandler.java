package cn.tedu.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
/**处理当个对象的 ，rs中要么有一条，要么一条没有。
 * @param <T>
 */
public class BeanHandler<T> implements ResultSetHandler<T> {
	private Class<T> type;
	public BeanHandler(Class<T> type){
		this.type = type;
	}
	/**将rs->T t
	 */
	public T handle(ResultSet rs) throws SQLException {
		//1使用反射技术创建T类型的对象t
		//type ->User.class  
		//type.newInstance() “等价于”User user = new User();
		T t =null;
		try {
			if(rs.next()){
				t = type.newInstance();
				//User.class ->BeanInfo info
				BeanInfo info = Introspector.getBeanInfo(type);
				//从info中获取所有属性对应的对象（属性类型，属性名称，setXxx和getXxx）
				PropertyDescriptor[] pds = info.getPropertyDescriptors();
				//遍历数组
				for(int i =0;i<pds.length;i++){
					//获得当前t对象的当前属性对应的setXxx(..)
					Method mt = pds[i].getWriteMethod();
					//获取当前属性的名称 比如：username
					String pname = pds[i].getName();
					//t.setXxx(rs.getString("属性名称"))
					try{
						mt.invoke(t, rs.getObject(pname));
					}catch (SQLException e) {
						continue;
					}
				}
			}
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return t;
	}
}
