package com.example.androidbase.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Eriksson
 *
 */
public class DateUtils {

	private static SimpleDateFormat sdf = null;
	
	private static long serverDelay = 0l;
	
	static{
		sdf = new SimpleDateFormat();
	}
	
	public static long markServerDelay(String serverTime){
		if(VerifyTool.isNumeric(serverTime)){
			serverDelay = (new Date().getTime()) - Long.parseLong(serverTime);
		}
		return serverDelay;
	}
	
	/**
	 * 把日期转换成日期字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date,String pattern){
		String result = "";
		if(date==null){
			return result;
		}
		if(pattern==null || "".equals(pattern)){
			return result;
		}
		sdf.applyPattern(pattern);
		result = sdf.format(date);
		return result;
	}
	
	/**
	 * 把日期字符串转换成java.util.Date
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static Date parse(String source,String pattern){
		Date result = null;
		if(source==null || "".equals(source)){
			return result;
		}
		if(pattern==null || "".equals(pattern)){
			return result;
		}
		sdf.applyPattern(pattern);
		try {
			result = sdf.parse(source);
		} catch (ParseException e) {}
		return result;
	}
	
	/**
	 * 智能时间格式
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static String formatSmartDate(String source,String pattern){
		String result = "";
		Date _tempD = parse(source,pattern);
		if(_tempD==null){
			return "";
		}
		long _d = _tempD.getTime();
		long _now = new Date().getTime() - serverDelay;
		long _range = _now - _d;
		if(_range<0){
			result = "刚刚";
		}else if(_range < 1000*60){
			result = String.valueOf(((int)(_range%1000>0?(_range/1000+1):(_range/1000))))+" 秒前";
		}else if(_range < 1000*60*60){
			result = String.valueOf(((int)(_range%(1000*60)>0?(_range/1000/60+1):(_range/1000/60))))+" 分钟前";
		}else if(_range < 1000*60*60*2){
			result = String.valueOf(((int)(_range%(1000*60*60)>0?(_range/1000/60/60+1):(_range/1000/60/60)))) + "小时前";
		}else{
			result = format(_tempD,"MM-dd HH:mm");
		}
		return result;
	}

	/**
	 * 智能时间格式
	 * @param time
	 * @return
	 */
	public static String formatSmartDate(long time,String pattern){
		String result = "";
		long _d = time;
		long _now = new Date().getTime() - serverDelay;
		long _range = _now - _d;
		if(_range<0){
			result = "刚刚";
		}else if(_range < 1000*60){
			result = String.valueOf(((int)(_range%1000>0?(_range/1000+1):(_range/1000))))+" 秒前";
		}else if(_range < 1000*60*60){
			result = String.valueOf(((int)(_range%(1000*60)>0?(_range/1000/60+1):(_range/1000/60))))+" 分钟前";
		}else if(_range < 1000*60*60*2){
			result = String.valueOf(((int)(_range%(1000*60*60)>0?(_range/1000/60/60+1):(_range/1000/60/60)))) + "小时前";
		}else{
			SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
			return sDateFormat.format(new Date(time + 0));
		}
		return result;
	}
		
}
