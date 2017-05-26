package io.github.brunovcosta.quillbill.helpers;

import java.math.BigInteger;
import java.security.SecureRandom;

public class HashHelper {
	private static SecureRandom random = new SecureRandom();
	public static String hash(){
		return new BigInteger(130, random).toString(32);
	}
}