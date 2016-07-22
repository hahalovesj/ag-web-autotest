package com.demo.test.pageshelper;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.demo.test.pages.FramePage;
import com.demo.test.pages.HomePage;
import com.demo.test.utils.*;

/**
 * @author Shane
 * @desciption 首页帮助类：专门提供在这个页面进行操作的方法封装*/
public class HomePageHelper {
	//提供本类中日志输出对象
	public static Logger logger = Logger.getLogger(HomePageHelper.class);
	public static String bannerHrefs[];
	
	/**
	 * @description 等待首页元素加载
	 * @param seleniumUtil selenium api封装引用对象
	 * @param timeOut 等待元素超时的时间
	 * */
	public static void waitHomePageLoad(SeleniumUtil seleniumUtil,int timeOut){
		logger.info("开始等待首页元素加载");
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_HREF_TOINDEX);//系统首页
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_HREF_PRODUCT);//产品查询
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_HREF_RECOMMEND);//推荐产品
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_HREF_SPECIAL);//特价产品
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_HREF_MYORDER);//我的订单
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_HREF_GIFT);//礼品兑换
		seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_HREF_BANNER);//Banner广告轮播
		logger.info("首页元素加载完毕");		
	}
	/**
	 * @Description 登录成功之后验证菜单名是不是正确的
	 * */
	public static void checkUserName(SeleniumUtil seleniumUtil){	
		logger.info("开始检查用户名是不是："+"申静，您好，欢迎登录网络销售系统！");
		//判断实际文本时候包含期望文本(实际文本,期望文本)
		seleniumUtil.isContains(seleniumUtil.getText(HomePage.HP_div_GREET),"申静，您好，欢迎登录网络销售系统！");		
		logger.info("用户名检查完毕,用户名是："+"申静，您好，欢迎登录网络销售系统！");	
	}
	
	/**
	 * @Description 登录成功之后验证菜单名是不是正确的
	 * */
	public static void checkToIndex(SeleniumUtil seleniumUtil,int timeOut,String toIndex){
		logger.info("开始检查菜单是不是："+toIndex);
		seleniumUtil.isTextCorrect(seleniumUtil.getText(HomePage.HP_HREF_TOINDEX), toIndex);
		logger.info("菜单检查完毕,菜单是："+toIndex);
		
	}

	/**
	 * @description 登录成功之后验证首页的文本内容
	 * */
	public static void checkHomeText(SeleniumUtil seleniumUtil){
		//TODO 需要优化
		logger.info("开始检查菜单是不是："+"系统首页");
		//seleniumUtil.getAttributeText(elementLocator, attribute)
		seleniumUtil.isTextCorrect(seleniumUtil.getText(HomePage.HP_HREF_TOINDEX), "系统首页");
		logger.info("菜单检查完毕,菜单是："+"系统首页");
	}
	

	/**
	 * @description 登录成功之后验证首页轮播广告的链接
	 * */
	public static void getHomeBanner(SeleniumUtil seleniumUtil) {
		logger.info("开始检查Banner："+"。。。。");
		List<WebElement> elements =seleniumUtil.findElementsBy(HomePage.HP_HREF_BANNER);
		
		for (WebElement e : elements) {
		System.out.println(e.getAttribute("href"));
	}
		
		
//		for(int i=0;i<elements.size();i++){
//			//System.out.println(e);
//			if (!elements.get(i).toString().isEmpty()) {
//				bannerHrefs[i]=elements.get(i).getAttribute("href").toString();
//				System.out.println(bannerHrefs[i]);
//				
//			}
//		}
		logger.info("Banner检查完毕"+"。。。。");
	}
	

}
