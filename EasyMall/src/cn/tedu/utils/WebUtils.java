package cn.tedu.utils;

public class WebUtils {
	private WebUtils(){}
	
	/**
	 * �ж��ַ����Ƿ�Ϊ�ն�����߿��ַ���
	 * @return
	 */
	public static boolean isNull(String str){
		return (str==null || "".equals(str.trim()));
	}
}
