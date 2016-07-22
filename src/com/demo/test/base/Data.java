package com.demo.test.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.demo.test.utils.ParseXml;


/* 框架代码中用的公共数据模块
 * 
 * 
 * 
 * */ 

public class Data {
	
	public static String currentClassName=null;  //记载了当前正在运行的类名，实际就是当前运行的case名称,在每个case开始前要修改此字段值!	
	public static WebDriver theDriver;
	public static Map<String,String> globalMap = new HashMap<String,String>();
	public static List<String> staticUrl = new ArrayList<String>();
	public static List<String> dynamicUrl = new ArrayList<String>();
	
	static{	
		ParseXml px = new ParseXml("data/global.xml");
		globalMap=px.getChildrenInfo("/data");
	}
	
}



