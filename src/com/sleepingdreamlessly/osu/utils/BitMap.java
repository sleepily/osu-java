package com.sleepingdreamlessly.osu.utils;

public class BitMap
{
	public static boolean[] asArray(int bits, int input)
	{
		boolean[] bitmap = new boolean[bits];
		
		String binary = asString(bits, input);
		
		for (int c = 0; c < binary.length(); c++)
			bitmap[c] = (binary.charAt(c) == '1');
		
		return bitmap;
	}
	
	public static String asString(int bits, int input)
	{
		return new StringBuilder(Integer.toBinaryString(input)).reverse().toString();
	}
}