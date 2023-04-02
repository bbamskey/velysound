package kr.co.velysound.common.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Slf4j
public class CryptoUtil {
	/**
	 * 비밀번호를 SHA256 방식으로 암호화한다.
	 *
	 * @param password 비밀번호
	 * @return 비밀번호 문자열
	 */
	public static String encryptSHA256(String password, String salt) {
		String result = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

			// 초기화
			messageDigest.reset();
			// 비밀번호 입력
			messageDigest.update(salt.getBytes());
			byte[] stringBytes = messageDigest.digest(password.getBytes());

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < stringBytes.length; i++) {
				sb.append(Integer.toString((stringBytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			log.error("No Such Algorithm!");
		}
		return result;
	}

	/**
	 * salt Key 생성
	 *
	 * @return saltKey
	 */
	public static String getSalt() {
		String result = "";
		try {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[16];
			sr.nextBytes(salt);
			result = salt.toString();
		} catch (NoSuchAlgorithmException e) {
			log.error("No Such Algorithm!");
		}
		return result;

	}
}
