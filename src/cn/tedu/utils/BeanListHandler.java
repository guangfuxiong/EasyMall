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
		//1、定义集合
		List<T> list = new ArrayList<T>();
		//3遍历结果集合rs  ->List<T> list
		while(rs.next()){
			try {
				//4实例化对象
				T t = type.newInstance();
				//6得到BeanInfo对象
				BeanInfo info = Introspector.getBeanInfo(type);
				//7、获取  “属性对应的对象"s->数组
				//pds[0]  int id ；getId(),setId(..)
				//pds[1]  String username；getUserName(),setUsername(..)
				PropertyDescriptor[] pds = info.getPropertyDescriptors();
				//8遍历数组
				for(int i=0;i<pds.length;i++){
					//9.1获取setXxx(...)方法
					Method mt = pds[i].getWriteMethod();
					//9.2获取属性的名称
					String pname = pds[i].getName();
					//9.3执行该方法：为对象t的属性pname赋值
					try{
						mt.invoke(t, rs.getObject(pname));
					}catch (SQLException e) {
						//说明属性名在数据库对应的表中不存在该字段
						//要想被取出结果，实体类中的属性名称要和数据库表的字段名要一样。
						continue;
					}
				}
				//5、将t添加到list中
				list.add(t);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		//2、返回结果
		return list;
	}
}
