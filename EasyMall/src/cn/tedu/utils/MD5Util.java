package cn.tedu.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	/*public static void main(String[] args) {
		System.out.println(md5("123"));
	}*/
	/** 	 * ʹ��md5���㷨���м��� 	 */ 	
	public static String md5(String plainText) {
		byte[] secretBytes = null; 		
		if(plainText!=null){
			try { 			
				secretBytes = MessageDigest.getInstance("md5").digest(
						plainText.getBytes()); 		
			} catch (NoSuchAlgorithmException e) { 			
				throw new RuntimeException("û��md5����㷨��"); 		
			} 		
			String md5code = new BigInteger(1, secretBytes).toString(16); 		
			for (int i = 0; i < 32 - md5code.length(); i++) { 
				md5code = "0" + md5code; 		
			} 		
			return md5code;
		}else{
			return null;
		}
		
	}
}