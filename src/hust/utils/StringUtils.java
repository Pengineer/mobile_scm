package hust.utils;

import java.util.UUID;

public class StringUtils {

	public static String uniqueKey() {
		String key = UUID.randomUUID().toString();
		key.replaceAll("-", "");
		return key.toUpperCase();
	}
	
	public static void main(String[] args) {
		String key = "E2667602-19EB-47DE-A06E-EED65E8B07E5";
		System.out.println(key.replaceAll("-", ""));
	}
}

