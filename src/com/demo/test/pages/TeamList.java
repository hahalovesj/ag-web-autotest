package com.demo.test.pages;

import org.openqa.selenium.By;

/**
 * @author Shane
 * @description 产品查询页面元素定位声明
 * */
public class TeamList {
	
	
	
	/**目的地*/
	//全部
	public static final By TL_A_MDDALL = By.id(".//*[@id='filter']/dl[1]/span[1]/div/dd[1]/div/a");
	
	//欧洲
	public static final By TL_A_MDDEurope = By.xpath(".//*[@id='filter']/dl[1]/span[1]/div/dd[2]/div/a");
	
	/**出发地*/
	//全部
	public static final By TL_A_CFDALL = By.xpath(".//*[@id='filter']/dl[2]/span[1]/div/dd[1]/div/a");
	
	//北京
//TODO 
	public static final By TL_A_CFDBEIJING = By.xpath(".//*[@id='filter']/dl[2]/span[1]/div/dd[1]/div/a");
	/**特价产品*/
	public static final By TL_A_SPECIAL = By.xpath(".//*[@id='special']");
	
	/**我的订单*/
	public static final By TL_A_MYORDER= By.xpath(".//*[@id='myOrder']");

	/**礼品兑换*/
	public static final By TL_A_GIFT= By.xpath(".//*[@id='gift']");
	
}
