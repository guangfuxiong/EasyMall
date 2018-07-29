package cn.tedu.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
/**����������� ��rs��Ҫô��һ����Ҫôһ��û�С�
 * @param <T>
 */
public class BeanHandler<T> implements ResultSetHandler<T> {
	private Class<T> type;
	public BeanHandler(Class<T> type){
		this.type = type;
	}
	/**��rs->T t
	 */
	public T handle(ResultSet rs) throws SQLException {
		//1ʹ�÷��似������T���͵Ķ���t
		//type ->User.class  
		//type.newInstance() ���ȼ��ڡ�User user = new User();
		T t =null;
		try {
			if(rs.next()){
				t = type.newInstance();
				//User.class ->BeanInfo info
				BeanInfo info = Introspector.getBeanInfo(type);
				//��info�л�ȡ�������Զ�Ӧ�Ķ����������ͣ��������ƣ�setXxx��getXxx��
				PropertyDescriptor[] pds = info.getPropertyDescriptors();
				//��������
				for(int i =0;i<pds.length;i++){
					//��õ�ǰt����ĵ�ǰ���Զ�Ӧ��setXxx(..)
					Method mt = pds[i].getWriteMethod();
					//��ȡ��ǰ���Ե����� ���磺username
					String pname = pds[i].getName();
					//t.setXxx(rs.getString("��������"))
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
