package com.demo.test.testcases.home;

import java.util.Map;
import org.testng.annotations.Test;
import com.demo.test.base.BaseParpare;
import com.demo.test.pageshelper.*;


/**
 * @author Shane
 * @description 检测首页Banner的超链接，是不是正确的
 * */

public class HomePage_002_CheckHomeHref_Test extends BaseParpare{
  @Test(dataProvider="testData")
  public void checkHomeHref(Map<String, String> data) {
	  //等待登录页面加载
	  LoginPageHelper.waitLoginPageLoad(seleniumUtil, timeOut);
	  // 输入登录信息
	  LoginPageHelper.typeLoginInfo(seleniumUtil,data.get("USERNAME"), data.get("PASSWORD"),data.get("CAPTCHA"));
	  //等待首页元素加载完毕
	  HomePageHelper.waitHomePageLoad(seleniumUtil, timeOut);
	  
	//获得首页banner链接
	  HomePageHelper.getHomeBanner(seleniumUtil);
  }
  
 
}
