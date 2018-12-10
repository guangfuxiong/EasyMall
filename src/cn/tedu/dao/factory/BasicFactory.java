package cn.tedu.dao.factory;

import cn.tedu.dao.UserDao;
import cn.tedu.utils.PropUtils;

public class BasicFactory {
	private static BasicFactory factory = new BasicFactory();
	
	//����
	private BasicFactory(){}
	
	//���ع���ʵ��
	public static BasicFactory getFactory(){
		return factory;
	}
	
	//���������ļ��е�����������UserDaoʵ��
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
