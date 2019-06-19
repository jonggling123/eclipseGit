package kr.or.ddit.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;


public class EncryptUtils {
	public static SecretKey generate128bitSecretKey() {
		SecretKey key = null;
		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		keyGenerator.init(128);
		key = keyGenerator.generateKey();
		return key;
	}
	
	public static byte[] generate128Iv(String str) {
		byte[] initializeVector = null;
		try {
			initializeVector = MessageDigest.getInstance("md5").digest(str.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return initializeVector;
	}
	
	public static String encodingBase64(byte[] arr) {
		return Base64.encodeBase64String(arr);
	}
	
	public static byte[] decryptAESFromBase64(String encoded, SecretKey key, byte[] iv) {
		byte[] decrypted = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			byte[] encrypted = Base64.decodeBase64(encoded);
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
			decrypted = cipher.doFinal(encrypted);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
		return decrypted;
	}
}
