package com.traveler54.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期转换工具
 */
public class DateUtil
{
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    /**
     * 返回开始日期+有效天数后的结束日期
     * @param startDate 开始日期
     * @param dayNum  有效天数
     * @return Date 结束日期
     */
    public static Date getDay(Date startDate,int validDay){
    	  //SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATETIME);
    	  Calendar ca = Calendar.getInstance();
    	  ca.setTime(startDate);
		  ca.add(Calendar.DATE, validDay);// 30为增加的天数，可以改变的
		  Date endDate = ca.getTime();
    	return endDate;
    }
    
    public static boolean isDate(String date){
    	return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
    
    public static String getCurrentDateStr(){
    	return getCurrentDateStr(FORMAT_DATETIME);
    }
    
    public static String getDatetimeStr(long datetime){
    	return format(new Date(datetime),FORMAT_DATETIME);
    }
    
    public static String getDateStr(long datetime){
    	return format(new Date(datetime),FORMAT_DATE);
    }
	
    public static Date getCurrentDate(){
	     Calendar cal = Calendar.getInstance();
	     Date currDate = cal.getTime();
	     return currDate;
    }

    /**
	 * param   2010-09-01
	 */
    public static Date parseStringToDate(String date_str){
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
		Date date = null;
		try {
			date = sdf.parse(date_str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
    /**
     * param   2010-09-01 19:29:10
     */
    public static Date parseStringToDateTime(String date_str){
    	SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATETIME);
    	Date date = null;
    	try {
    		date = sdf.parse(date_str);
    	} catch (ParseException e) {
    		//e.printStackTrace();
    	}
    	return date;
    }
	
    public static Long parseStringToTime(String date_str){
    	Date date = parseStringToDateTime(date_str);
    	if(date==null)
    		return null;
    	else
    		return date.getTime();
    }
    private static String getCurrentDateStr(String strFormat){
	     Calendar cal = Calendar.getInstance();
	     Date currDate = cal.getTime();
	     return format(currDate, strFormat);
	 }

    private static String format(Date aTs_Datetime, String as_Pattern){
      if (aTs_Datetime == null || as_Pattern == null){
    	  return null;
      }
      SimpleDateFormat dateFromat = new SimpleDateFormat(as_Pattern);
      return dateFromat.format(aTs_Datetime);
    }
    public static String stampToString(long dateStamp){
    	SimpleDateFormat dateFromat = new SimpleDateFormat(FORMAT_DATETIME);
    	String date = dateFromat.format(new Date(dateStamp));
    	System.out.println(date);
    	return date;
    }
    
    public static String dateToStamp(String date) {  
    	SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATETIME);  
    	Date d;
    	String stampTime = null;
    	try {
    		d = sdf.parse(date);  
	    	long l = d.getTime();
	    	stampTime = String.valueOf(l);
    	} catch (ParseException e) {  
    		e.printStackTrace();  
    	}  
    		return stampTime;
    }
    public static Timestamp getTimestamp(String date){
    	Timestamp ts = Timestamp.valueOf(date);
//    	System.out.println(ts.getTime());
    	return ts;
    }
    
    public  static Timestamp getTimestamp(){
		String currentDate = DateUtil.getCurrentDateStr();
		Timestamp ts = DateUtil.getTimestamp(currentDate);
		return ts;
	}
    
	public static void main(String[] args){
//		System.out.println(dateToStamp("2010-10-10 01:02:30"));
//		getDay(new Date(),5);
//		System.out.println(getCurrentDateStr());
//		System.out.println(parseStringToDateTime(getCurrentDateStr()).getTime());
//		System.out.println(getDatetimeStr(System.currentTimeMillis()));
//        System.out.println(getCurrentDateStr("yyyy.MM.dd HH:mm:ss"));
//        System.out.println(format(new Date(System.currentTimeMillis()),"yyyy.MM.dd HH:mm:ss"));
//		stampToString(Long.parseLong(dateToStamp("2010-10-10 01:02:30")));
//		System.out.println(getTimestamp("2012-10-10 01:02:30"));
		System.out.println(getDatetimeStr(1337938509000L));
		Long time = parseStringToTime("2012-05-25 10:33:01");
		System.out.println(time);
		//Long time = date.getTime();
		System.out.println(getTimestamp("2012-05-25 10:33:01").getTime());
		System.out.print(getDatetimeStr(time));
	 }
} 


