package com.javaweb.utils;

public final class ConversionUtils {
	private ConversionUtils() {
		
	}
	public static Integer convertToInteger(Object value, String name) {
		if(value instanceof Integer) {
			return (Integer) value;
		}
		else if(value  instanceof String) {
			try {
				return Integer.parseInt((String) value);
			}
			catch(NumberFormatException e) {
				throw new IllegalArgumentException( name + " du lieu khong phai la so " );
			}
		}
		return null;
	}
}
