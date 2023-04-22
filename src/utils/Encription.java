package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Encription {

	public static String getMd5(String chain){
		MessageDigest md5;
		String retorno = "";
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(chain.getBytes());
			byte[] keys = md5.digest();
			retorno = new String(new BASE64Encoder().encode(keys));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		}
		return retorno;
	}
}