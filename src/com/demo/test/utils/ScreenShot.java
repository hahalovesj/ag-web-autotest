package com.demo.test.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
/**
 * @Package com.aoyou.test.util
 * @ClassName ScreenShot
 * @Description ���Խ�ͼģ��
 * 1�������ӿڷ���:takeScreenshot
 */

public class ScreenShot {
	public WebDriver driver;
	
	/**
	 * @param drvier:��ǰ��Ҫ��ͼ��driver
	 */
	public ScreenShot(WebDriver driver){
		this.driver=driver;
	}
	
	private void takeScreenshot(String screenPath){
		try{
			File srcFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(screenPath));
		}catch(IOException e){
			Log.logError("Screen shot error:" + screenPath);
		}
	}
	
	/**
	 *��ͼ������Ĭ��·��Ϊ��ǰprojectĿ¼�£�test-output/snapshot
	 */
    public void takeScreenshot(){
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String mDateTime = formatter.format(new Date().getTime());
		String screenName = mDateTime+".bmp";
    	File dir=new File("test-output/snapshot");
    	if(!dir.exists())
    		dir.mkdirs();
    	String screenPath=dir.getAbsolutePath() + "/" + screenName;
    	this.takeScreenshot(screenPath);
    }
}