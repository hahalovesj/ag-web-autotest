package com.demo.test.pages;

import org.openqa.selenium.By;

/**
 * @author Young
 * @description 首页面元素定位声明
 * */
public class HomePage {
	
	
	
	/**系统首页*/
	public static final By HP_HREF_TOINDEX = By.id("toIndex");
	
	/**产品查询*/
	public static final By HP_HREF_PRODUCT = By.xpath(".//*[@id='product']");
	
	/**推荐产品*/
	public static final By HP_HREF_RECOMMEND = By.xpath(".//*[@id='recommend']");
	
	/**特价产品*/
	public static final By HP_HREF_SPECIAL = By.xpath(".//*[@id='special']");
	
	/**我的订单*/
	public static final By HP_HREF_MYORDER= By.xpath(".//*[@id='myOrder']");

	/**礼品兑换*/
	public static final By HP_HREF_GIFT= By.xpath(".//*[@id='gift']");
	
}
