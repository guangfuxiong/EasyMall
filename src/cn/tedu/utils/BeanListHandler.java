package cn.tedu.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//User query(sql,new BeanHandler<User>(User.class),...)
//List<User> query(sql,new BeanListHandler<User>(User.class),...)
public class BeanListHandler<T> implements ResultSetHandler<List<T>> {
	private Class<T> type;
	public BeanListHandler(Class<T> type){
		this.type = type;
	}
	public List<T> handle(ResultSet rs) throws SQLException {
		//1�����弯��
		List<T> list = new ArrayList<T>();
		//3�����������rs  ->List<T> list
		while(rs.next()){
			try {
				//4ʵ��������
				T t = type.newInstance();
				//6�õ�BeanInfo����
				BeanInfo info = Introspector.getBeanInfo(type);
				//7����ȡ  �����Զ�Ӧ�Ķ���"s->����
				//pds[0]  int id ��getId(),setId(..)
				//pds[1]  String username��getUserName(),setUsername(..)
				PropertyDescriptor[] pds = info.getPropertyDescriptors();
				//8��������
				for(int i=0;i<pds.length;i++){
					//9.1��ȡsetXxx(...)����
					Method mt = pds[i].getWriteMethod();
					//9.2��ȡ���Ե�����
					String pname = pds[i].getName();
					//9.3ִ�и÷�����Ϊ����t������pname��ֵ
					try{
						mt.invoke(t, rs.getObject(pname));
					}catch (SQLException e) {
						//˵�������������ݿ��Ӧ�ı��в����ڸ��ֶ�
						//Ҫ�뱻ȡ�������ʵ�����е���������Ҫ�����ݿ����ֶ���Ҫһ����
						continue;
					}
				}
				//5����t��ӵ�list��
				list.add(t);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		//2�����ؽ��
		return list;
	}
}
