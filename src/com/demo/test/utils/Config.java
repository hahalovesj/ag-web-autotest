package com.demo.test.utils;


import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @Package com.aoyou.test.util
 * @ClassName Config
 * @Description ȫ�����ã�ֵ�־û�ģ��.��ÿ��buildǰ����config.xml,����������Ϣ.��ֹ�ڲ����������޸����ò�����Ӱ��
 * 
 *  browser:�����������������������(chrome/firefox/ie)
 *  browserWaitTime:��������ص���ȴ�ʱ�䣬��λΪs
 *  objectWaitTime��������ҵ���ȴ�ʱ�䣬��λΪs
 *  pageWaitTime��ҳ�������ɵ���ȴ�ʱ�䣬��λΪs
 *  siteName���������е�Ĭ�ϳ���վ��
 *  objectRespository����������������ҳ������·��
 * 
 */

public class Config {
	public static Map<String,String> hostMap = new LinkedHashMap<String,String>();	
	public static String browser;
	public static String siteName;
	public static int browserWaitTime;
	public static int objectWaitTime;
	public static int pageWaitTime;
	public static String objectRespository;
	public static int hostNumber=0;
	public static int openNewBrowser;
	public static int retryTimes;
	static{		
		ParseXml pxConfig=new ParseXml("config/config.xml");	
		browser=pxConfig.getElementText("/config/browser");
		browserWaitTime=Integer.valueOf(pxConfig.getElementText("/config/browserWaitTime"));
		objectWaitTime=Integer.valueOf(pxConfig.getElementText("/config/objectWaitTime"));
		pageWaitTime=Integer.valueOf(pxConfig.getElementText("/config/pageWaitTime"));
		siteName=pxConfig.getElementText("/config/siteName");
		objectRespository=pxConfig.getElementText("/config/objectRespository");	
		hostMap=pxConfig.getChildrenInfo("/config/hostList/host","ip","hostName");
		openNewBrowser=Integer.valueOf(pxConfig.getElementText("/config/openNewBrowser"));
		retryTimes=Integer.valueOf(pxConfig.getElementText("/config/retryTimes"));
	}
	
}


