/**
 * 
 */
package com.yuanxd.wx.wechat4j.lang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ChengNing
 * @date   2014��10��30��
 */
public class DateTimeUtil {
	public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";
	public static final String DEFAULT_FORMAT_TIME = "HH:mm:ss";
	public static final String DEFAULT_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * �ַ���ת��Ϊ����
	 * @param dateString   �����ַ���
	 * @param format       ��ʽ���ַ���
	 * @return
	 */
	public static Date getDateTime(String dateString,String format){
		SimpleDateFormat sf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * ͨ��ʱ���ַ����õ�һ��Date
	 * @param dateString eg:2010-01-01 10:10:00
	 * @return
	 */
	public static Date getDateTime(String dateTimeString){
		return getDateTime(dateTimeString,DEFAULT_FORMAT_DATETIME);
	}
	
	/**
	 * ת��һ�����ڴ�ΪDate
	 * @param date eg:2010-01-01
	 * @return
	 */
	public static Date getDate(String date){
		return getDateTime(date, DEFAULT_FORMAT_DATE);
	}
	
	/**
	 * ת��һ��ʱ�䴮ΪDate
	 * @param time 10:10:00
	 * @return
	 */
	public static Date getTime(String time){
		return getDateTime(time,DEFAULT_FORMAT_TIME);
	}
	
	
	/**
	 * ����������ת��Ϊ�����ַ���
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toDateString(Date date,String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}
	
	/**
	 * ������������ʱ�����ΪĬ���ַ�����ʽ
	 * @param date eg:2010-01-01 10:10��00
	 * @return
	 */
	public static String toDateTimeStr(Date date){
		return toDateString(date, DEFAULT_FORMAT_DATETIME);
	}
	
	/**
	 * ������������ʱ�����ΪĬ�ϸ�ʽ�����ַ���
	 * @param date eg:2010-01-01
	 * @return
	 */
	public static String toDateStr(Date date){
		return toDateString(date, DEFAULT_FORMAT_DATE);
	}
	
	/**
	 * ������������ʱ�����ΪĬ�ϸ�ʽʱ���ַ���
	 * @param date eg:10:10��00
	 * @return
	 */
	public static String toTimeStr(Date date){
		return toDateString(date, DEFAULT_FORMAT_TIME);
	}
	
	/**
	 * ����������ַ���
	 * @return
	 */
	public static String todayStr(){
		return currentDateStr();
	}
	
	/**
	 * ����������ַ���
	 * @return
	 */
	public static Date today(){
		return current();
	}
	
	/**
	 * ��ý���ָ��ʱ���Date
	 * @param time
	 * @return
	 */
	public static Date getToday(String time){
		String today = todayStr();
		return getDateTime(today + " " + time,DEFAULT_FORMAT_DATETIME);
	}
	
	/**
	 * ��ȡ��ǰʱ��
	 * @return eg:2012-01-01 01:01:00
	 */
	public static Date now(){
		return current();
	}
	
	/**
	 * ��ȡ��ǰʱ��
	 * @return eg:2012-01-01 01:01:00
	 */
	public static String nowStr(){
		return currentStr();
	}
	
	/**
	 * ��ȡ��ǰ��ʱ��
	 * @return eg:10:30:00
	 */
	public static String currentTimeStr(){
		return toDateString(new Date(), DEFAULT_FORMAT_TIME);
	}
	
	/**
	 * ��ȡ��������
	 * @return eg:2012-01-01
	 */
	public static String currentDateStr(){
		return toDateString(new Date(), DEFAULT_FORMAT_DATE);
	}
	
	/**
	 * ��ǰ�����ں�ʱ�䣬����now()
	 * @return
	 */
	public static String currentStr(){
		return toDateString(new Date(), DEFAULT_FORMAT_DATETIME);
	}
	
	/**
	 * ϵͳ��ǰ����
	 * @return
	 */
	public static Date current(){
		return new Date();
	}
	
	/**
	 * ��ʽ���ַ�����ʹ��ָ��Date�滻���е�ʱ�����,ʱ���ʹ��{yyyy-mm-dd}��ʶ
	 * @param text
	 * @param date
	 * @return
	 */
	public static String format(String text,Date date){
		int start = text.indexOf("{");
		int end = text.indexOf("}");
		while(start > 0 && end > 0){
			String subStr = text.substring(start, end+1);
			String format = text.substring(start+1, end);
			String dateStr = toDateString(date, format);
			text = text.replace(subStr, dateStr);
			
			start = text.indexOf("{");
			end = text.indexOf("}");
		}
		return text;
	}
	
	/**
	 * �Ƿ�һ���Ϸ�������
	 * @param date
	 * @return
	 */
	public static boolean isDate(String dateString){
		return tryParse(dateString);
	}
	
	/**
	 * �Ƿ���Խ���
	 * @param date
	 * @return
	 */
	public static boolean tryParse(String dateString){
		Date date = getDateTime(dateString);
		return (date == null? false:true);
	}
}
