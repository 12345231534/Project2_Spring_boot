package com.javaweb.utils;

public class NumberUtil {
	public static boolean isNumber(String s) {
		try {
			Integer number = Integer.parseInt(s);
		}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
}
