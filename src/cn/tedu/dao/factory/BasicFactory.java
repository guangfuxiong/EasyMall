package cn.tedu.dao.factory;

import cn.tedu.dao.UserDao;
import cn.tedu.utils.PropUtils;

public class BasicFactory {
	private static BasicFactory factory = new BasicFactory();
	
	//单例
	private BasicFactory(){}
	
	//返回工厂实例
	public static BasicFactory getFactory(){
		return factory;
	}
	
	//根据配置文件中的配置项生产UserDao实例
	public <T> T getInstance(Class<T> clazz){
		try {
			String className = PropUtils.getValue(clazz.getSimpleName());
			Class clz = Class.forName(className);
			return (T)clz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
}
