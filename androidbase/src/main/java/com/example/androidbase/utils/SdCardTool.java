package com.example.androidbase.utils;

import android.os.Environment;
/**
 * 获取SD卡地址工具
 * @author chenxiaozhou
 *
 */
public class SdCardTool {
	
	public static boolean hasSDCard() {
		String status = Environment.getExternalStorageState();
		if (!status.equals(Environment.MEDIA_MOUNTED)) {
			return false;
		}
		return true;
	}

	public static String getRootFilePath() {
		if (hasSDCard()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
		} else {
			return Environment.getDataDirectory().getAbsolutePath() + "/data/";
		}
	}
	
}
