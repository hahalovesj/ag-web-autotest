package com.demo.test.functions;
import java.util.Date; 
import java.util.Calendar; 
import java.text.ParseException;
import java.text.SimpleDateFormat; 

/**
* @ClassName: HandleDate 
* @Description: ���ڴ���ģ��
* 4�������ӿڷ�����getFormatDate/ParseDate/getNowDayString/getForwardDayString
* @date 2015��6��9�� ����5:31:21
*/
public class HandleDate {
	
	/**
	* @Description ��Date��ʽ�����ڣ�ת����"yyyy-MM-dd" String��ʽ
	* @param  date  ��Ҫ���и�ʽ�������ڲ���
	* @return String ת�����ַ���"yyyy-MM-dd"������
	*/
	public static String getFormatDate(Date date){	
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);	
	}
	
	/**
	* @Description ��String��ʽ�����ڣ�ת����Date��ʽ
	* @param  String  ��Ҫ���и�ʽ��������
	* @return Date ת�������ڸ�ʽ"yyyy-MM-dd"
	* @throws ����Ĳ���Date��ʽ�׳��쳣
	*/
	public static Date ParseDate(String date) {
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(date);			
			} 	
		catch (ParseException e) {			
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	* @Description �õ���ǰ����"yyyy-MM-dd"���ַ���
	* @return String 
	*/
	public static String getNowDayString(){		
		return getFormatDate(new Date());
	}
	
	/**
	* @Description �õ�date���ڣ�ǰ���Number����ַ�����ʽ"yyyy-MM-dd"����
	* @param date ��������
	* @param Number ��������  ����Ϊ֮ǰ������Ϊ֮��
	* @return String 
	*/
	public static String getForwardDayString(String date,int Number) {	
		Calendar AddDay = Calendar.getInstance();
		AddDay.setTime(ParseDate(date));
		AddDay.add(Calendar.DATE, Number);
		return getFormatDate(AddDay.getTime());		
	}
	
	
}
