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
			// ��ȡ�����ļ�(conf.properties)
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
	 * ����key���ض�Ӧ��value
	 * 
	 * @param key �����ļ��е�key
	 * @return value �����ļ��е�value
	 */
	public static String getValue(String key) {
		return prop.getProperty(key);
	}
}
