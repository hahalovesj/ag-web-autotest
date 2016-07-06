package com.demo.test.pageshelper;

import org.apache.log4j.Logger;

import com.demo.test.pages.FramePage;
import com.demo.test.pages.HomePage;
import com.demo.test.utils.SeleniumUtil;

/**
 * @author Young
 * @desciption 首页帮助类：专门提供在这个页面进行操作的方法封装*/
public class HomePageHelper {
	//提供本类中日志输出对象
	public static Logger logger = Logger.getLogger(HomePageHelper.class);
	
	/**
	 * @author Young
	 * @description 等待首页元素加载
	 * @param seleniumUtil selenium api封装引用对象
	 * @param timeOut 等待元素超时的时间
	 * */
	public static void waitHomePageLoad(SeleniumUtil seleniumUtil,int timeOut){
		logger.info("开始等待首页元素加载");
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_HREF_TOINDEX);
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_HREF_PRODUCT);
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_HREF_RECOMMEND);
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_HREF_SPECIAL);
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_HREF_MYORDER);
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_HREF_GIFT);
		logger.info("首页元素加载完毕");
	
		
	}
	
	/**
	 * @author Young
	 * @Description 登录成功之后验证用户名是不是正确的
	 * 
	 * 
	 * */
	public static void checkToIndex(SeleniumUtil seleniumUtil,int timeOut,String toIndex){
		logger.info("开始检查菜单是不是："+toIndex);
		seleniumUtil.isTextCorrect(seleniumUtil.getText(HomePage.HP_HREF_TOINDEX), toIndex);
		logger.info("菜单检查完毕,菜单是："+toIndex);
		
	}

	/**
	 * @author Young
	 * @description 登录成功之后验证首页的文本内容
	 * */
	public static void checkHomeText(SeleniumUtil seleniumUtil){
		seleniumUtil.isTextCorrect(seleniumUtil.getText(HomePage.HP_HREF_TOINDEX), "系统首页");
		
	}
	

}
