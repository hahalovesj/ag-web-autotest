package com.demo.test.pages;

import org.openqa.selenium.By;

/**
 * @author Shane
 * @description 首页页面元素定位声明
 * */
public class HomePage {
	/**
	 * 页首 用户名登录欢迎信息
	 */
	public static final By HP_div_GREET = By.id(".//*[@id='blackbox']/div/div[1]");
	
	
	/**
	 * 系统中心下面
	 * */
	//1.系统首页
	public static final By HP_HREF_TOINDEX = By.id("toIndex");

	//TODO 2.分销动态
	//TODO 3.同业计划
	//TODO 4.签证资料
		
	/**
	 * 产品管理
	 * */
	//产品查询
	public static final By HP_HREF_PRODUCT = By.xpath(".//*[@id='product']");
	
	//推荐产品
	public static final By HP_HREF_RECOMMEND = By.xpath(".//*[@id='recommend']");
	
	//特价产品
	public static final By HP_HREF_SPECIAL = By.xpath(".//*[@id='special']");
	
	//TODO 奇迹旅行
	
	/**
	 * 销售查询
	 * */
	//我的订单
	public static final By HP_HREF_MYORDER= By.xpath(".//*[@id='myOrder']");
	
	//TODO 我的客人
	
	//TODO 积分账户
	
	//TODO 我的等级
	
	//TODO 兑换记录

	/**
	 * 在线活动
	 * */	
	//TODO 积分签到
	//TODO 积分抽奖
	
	//礼品兑换
	public static final By HP_HREF_GIFT= By.xpath(".//*[@id='gift']");
	
	/**
	 * 个人信息
	 * */
	//TODO 密码修改
	//TODO 域名管理
	//TODO 资料更新
	//TODO 登录日志
	
	
	/**
	 * 关于我们
	 * */
	//TODO 系统简介
	//TODO 会员服务
	

	/**
	 * 首页banner广告
	 * */
	//轮播图,多个
	public static final By HP_HREF_BANNER= By.xpath(".//*[@id='D1pic1']/div[@class='fcon']/a");
}
