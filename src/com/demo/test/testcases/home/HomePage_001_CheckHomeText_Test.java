package com.demo.test.testcases.home;
import java.util.Map;
import org.testng.annotations.Test;
import com.demo.test.base.BaseParpare;
import com.demo.test.pageshelper.HomePageHelper;
import com.demo.test.pageshelper.LoginPageHelper;
/**
 * @author Shane
 * @description 检测首页的菜单，链接是不是正确的
 * */

public class HomePage_001_CheckHomeText_Test extends BaseParpare{
  @Test(dataProvider="testData")
  public void checkHomeText(Map<String, String> data) {
	  //等待登录页面加载
	  LoginPageHelper.waitLoginPageLoad(seleniumUtil, timeOut);
	  // 输入登录信息
	  LoginPageHelper.typeLoginInfo(seleniumUtil,data.get("USERNAME"), data.get("PASSWORD"),data.get("CAPTCHA"));
	  //等待首页元素加载完毕
	  HomePageHelper.waitHomePageLoad(seleniumUtil, timeOut);
	  //检查用户登录欢迎是不是显示正确
	  //TODO HomePageHelper.checkUserName(seleniumUtil);
	//检查菜单名称是不是"系统首页"
	  HomePageHelper.checkHomeText(seleniumUtil);
  }
}
