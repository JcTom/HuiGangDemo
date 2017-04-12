package com.example.androidbase.utils;

/**
 * Created by yao on 16/8/5.
 */
public class UTF8Tools {


	public static String encode(String str){
		String strInput =str;
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < strInput.length(); i++) {
			output.append("&#x" +Integer.toString(strInput.charAt(i), 16));
		}
		return output.toString();
	}
}
