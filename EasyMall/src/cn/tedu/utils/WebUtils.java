package cn.tedu.utils;

public class WebUtils {
	private WebUtils(){}
	
	/**
	 * 判断字符串是否为空对象或者空字符串
	 * @return
	 */
	public static boolean isNull(String str){
		return (str==null || "".equals(str.trim()));
	}
}
