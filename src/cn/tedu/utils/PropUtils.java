package cn.tedu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropUtils {
	private static Properties prop = new Properties();
	private PropUtils() {
	}

	static {
		try {
			// 读取配置文件(conf.properties)
			String path = PropUtils.class.getClassLoader()
					.getResource("conf.properties").toURI()
					.getPath();
			prop.load(new FileInputStream(new File(path)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}

	/**
	 * 根据key返回对应的value
	 * 
	 * @param key 配置文件中的key
	 * @return value 配置文件中的value
	 */
	public static String getValue(String key) {
		return prop.getProperty(key);
	}
}
