package com.base.lib.db.utils;

import java.util.Random;
import java.util.UUID;

public class CodeUtils
{
	public static int generateCode(int length)
	{
		int base=10;
		for(int i=1;i<length;i++){
			base=base*10;
		}
		Random rnd = new Random();
		int randomVerificationCode = base+ rnd.nextInt(base*9);
		return randomVerificationCode;
	}
	

	public static String generateToken( )
	{
		return UUID.randomUUID().toString().replace("-", "");
	}
	public static int lettersCount(String input) {
		int r = 0;
		char[] letters = input.toLowerCase().toCharArray();
		for (int count2 = 0; count2 < letters.length; count2++) {
			char lett = letters[count2];
			if ( (lett >= 'a') & (lett <= 'z') ) {
				r++;
			}
		}
		return r;
	}
}
