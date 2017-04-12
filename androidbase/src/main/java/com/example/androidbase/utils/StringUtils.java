package com.example.androidbase.utils;

import java.text.DecimalFormat;

/**
 * 字符串工具类
 *
 * Created by yao on 16/6/24.
 */
public class StringUtils {

	/**
	 * 浮点型模式转换，当为整数时不显示小数点后数字
	 * @param f
	 * @return
	 */
	public static String formatFloat(float f){
		String _rst = "0";
		if(f%((int)f)>0){
			_rst = ""+f;
		}else{
			_rst = ""+(int)f;
		}
		return _rst;
	}

	/**
	 * 文件大小单位转换
	 * @param size
	 * @return
	 */
	public static String sizeChange(long size){
		DecimalFormat df = new DecimalFormat("#");
		String changeSize = "";
		if (size < 1024) {
			changeSize = df.format((double) size) + "B";
		} else if (size < 1048576) {
			changeSize = df.format((double) size / 1024) + "K";
		} else if (size < 1073741824) {
			changeSize = df.format((double) size / 1048576) + "M";
		} else {
			changeSize = df.format((double) size / 1073741824) + "G";
		}
		return changeSize;
	}


}
