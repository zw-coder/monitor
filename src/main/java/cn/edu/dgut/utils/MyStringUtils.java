package cn.edu.dgut.utils;

import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;
public class MyStringUtils {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String getMD5Value(String value){
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] md5ValueByteArray = digest.digest(value.getBytes());
			BigInteger bigInteger = new BigInteger(1, md5ValueByteArray); 
			return bigInteger.toString(16);
		} catch (Exception e) {
			return value;
		}
	}

}
