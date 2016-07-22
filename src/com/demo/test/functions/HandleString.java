package com.demo.test.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
* @ClassName: HandleString 
* @Description: �ַ�������ģ��
* 3�������ӿڷ�����subTextString/toLowerCaseFirstOne/toUpperCaseFirstOne
* @date 2015��6��9�� ����5:50:43
*/
public class HandleString {
	
	/**
	* @Description ���ַ���text����startString��ͷ��endString��β������ƥ���ַ�����һ���ַ��������з���
	* @param text����������ַ���
	* @param startString��������ʼ�ַ���
	* @param endString��������β�ַ���
	* @return String[] �����������ַ�������
	*/
	public static String[] subTextString(String text,String startString,String endString){

		String[]Text = text.split(startString);
		String []finalText = new String[Text.length-1];

		for (int i=1;i<Text.length;i++){		
			finalText[i-1] = Text[i].substring(0, Text[i].indexOf(endString)).trim();
		}	
		return finalText;
	}
	
	/**
	* @Description ����ĸתСд
	* @param String����������ַ���
	* @return String
	*/
    public static String toLowerCaseFirstOne(String s)
    {
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
	/**
	* @Description ����ĸת��д
	* @param String����������ַ���
	* @return String
	*/
    public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
	
	public static Object[] swtichMapKeyToArray(Map<String, String> map){
		return map.keySet().toArray();
	}
	
	 /**
		* @Description ���ַ�������String[]ת��List<String>
		* @param String[]����������ַ�������
		* @return List<String>
		*/
	    public static List<String> stringArrayToList(String[] str){
	    List<String> list = new ArrayList<String>();
		for(int i=0;i<str.length;i++){
			if(str[i].startsWith("http"))
			list.add(str[i]);
		}
		   return list; 
	    }
	    
	    public static List<String> handleUrlForList(List<String> list){
		    List<String> url = new ArrayList<String>();
			for(int i=0;i<list.size();i++){
				if(list.get(i).startsWith("http"))
				url.add(list.get(i));
			}
			   return url; 
		    }
	    
	    public static List<String> handlePriceForList(List<String> list,int subIndex){			
	    	
	    	String[] str= new String[list.size()];
	    		for(int i=0;i<list.size();i++){
	    			str[i] = list.get(i).substring(subIndex);
	    		}
	    		list= Arrays.asList(str);
	    		return list; 		
	    	}
	    
}