package com.demo.test.functions;

import org.openqa.selenium.WebDriver;
/**
* @ClassName HandleWindows 
* @Description ��������ڴ���ģ��
* 5�������ӿڷ�����getWindowHandles/getCurrentHandle/swtichToNewWindow/swtichBackOldWindow/swtichToWindow
* @date 2015��6��9�� ����5:56:18
*/
public class HandleWindows {
	
	/**
	* @Description �õ���ǰ����webdriver��������������ڵľ����Ϣ�����ַ���������ʽ����
	* @param  driver
	* @return String[] 
	*/
	public static String[] getWindowHandles(WebDriver driver) {
							
		String[] handles=new String[driver.getWindowHandles().size()];
		driver.getWindowHandles().toArray(handles); 
		return handles;		
	}
	
	/**
	* @Description �õ���ǰ�������������ڵľ����Ϣ�����ַ���ʽ����
	* @param  driver
	* @return String
	*/
	public static String getCurrentHandle(WebDriver driver){
		
		return driver.getWindowHandle();		
	}
	
	/**
	* @Description swtich���´򿪵����������
	* @param  driver
	*/
	public static WebDriver swtichToNewWindow(WebDriver driver){
		
		String[] handles=getWindowHandles(driver);
		int i=handles.length-1;
		WebDriver childwindow=driver.switchTo().window(getWindowHandles(driver)[i]);
		return childwindow;		
	}
	
	/**
	* @Description swtich��֮ǰһ�����������
	* @param  driver
	*/
	public static WebDriver swtichBackOldWindow(WebDriver driver){
		
		String[] handles=getWindowHandles(driver);
		int i=handles.length-1;
		WebDriver childwindow=driver.switchTo().window(getWindowHandles(driver)[i-1]);
		return childwindow;		
	}
	
	/**
	* @Description swtich����i���򿪵����������
	* @param  driver
	* @param  i ��������ֵ
	*/
	public static WebDriver swtichToWindow(WebDriver driver,int windowNumber){

		WebDriver childwindow=driver.switchTo().window(getWindowHandles(driver)[windowNumber]);
		return childwindow;	
	}
	
}
