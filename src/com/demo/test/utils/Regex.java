package com.demo.test.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Package com.aoyou.test.util
 * @ClassName Regex
 * @Description ������ʽ����ģ��
 * 3�������ӿڷ�����matchStringList/isFind/isMatch
 */

public class Regex {
	
	/**
	 *@param text:�����ҵ��ַ���
	 *@param regularExpression:������ʽ�ַ���
	 *@return ����ƥ����ַ������飬����ΪArrayList
	 */
	public static ArrayList<String> matchStringList(String text,String regularExpression,boolean subTheEnd){	
	Pattern p = Pattern.compile(regularExpression); 
	Matcher m = p.matcher(text);
	ArrayList<String> results = new ArrayList<String>();	
		while (m.find()) {  
			if(!subTheEnd){
			results.add(m.group());
			}else results.add(m.group().substring(0, m.group().length()-1));
	    }	
	return results; 	
	}
	
	public static String matchString(String text,String regularExpression){	
	Pattern p = Pattern.compile(regularExpression); 
	Matcher m = p.matcher(text);
		if (m.find()) {  			
			return m.group(0);
	    }else
	    	return null; 	
	}
	/**
	 * matchStringList����
	 *@param subIndex:��ȡ������
	 *@return ����ƥ��ģ��ѱ���ȡ�����ַ�������
	 */
	public static ArrayList<String> matchStringList(String text,String regularExpression,int subIndex){
	Pattern p = Pattern.compile(regularExpression); 
	Matcher m = p.matcher(text);
	ArrayList<String> results = new ArrayList<String>();
		while (m.find()) {  	
			results.add(m.group().substring(subIndex));
	    }	
	return results; 	
	}
	
	
	public static ArrayList<String> matchStringList(String[] text,String regularExpression,int subIndex){			
	Pattern p = Pattern.compile(regularExpression); 
	ArrayList<String> results = new ArrayList<String>();
		for(int i=0;i<text.length;i++){
			Matcher m = p.matcher(text[i]);		
				while (m.find()) {  	
					results.add(m.group().substring(subIndex));
				}
		}
		return results; 		
	}
	
	public static String matchString(String text,String regularExpression,int subIndex){	
	Pattern p = Pattern.compile(regularExpression); 
	Matcher m = p.matcher(text);
		if (m.find()) {  			
			return m.group(0).substring(subIndex);
	    }else
	    	return null; 	
	}
	
	/**
	 * �Ƿ����
	 * @return boolean
	 */
	public static boolean isFind(String text,String regularExpression){
	boolean falg = false;
	Pattern p = Pattern.compile(regularExpression); 
	Matcher m = p.matcher(text);
	
		if (m.find()) {  	
			falg=true;
	    }	
	return falg; 	
	}
	
	/**
	 * �Ƿ�ƥ���ַ�
	 * @return boolean
	 */
	public static boolean isMatch(String text,String regularExpression){
	boolean falg = false;
	Pattern p = Pattern.compile(regularExpression); 
	Matcher m = p.matcher(text);
	
		if (m.matches()) {  	
			falg=true;
	    }	
	return falg; 	
	}
	
}
