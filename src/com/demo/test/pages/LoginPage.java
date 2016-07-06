package com.demo.test.pages;

import org.openqa.selenium.By;
/**
 * @author Young
 * @description 登录页面元素定位声明
 * */
public class LoginPage {
	/**用户名输入框*/
	public static final By LP_INPUT_USERNAME = By.name("userName");
	
	/**密码输入框，有两个，在password的上面有个pwdText，需要先对pwdText做点击操作，才能往password输入密码*/
	public static final By LP_INPUT_PASSWORD = By.id("password");
	public static final By LP_INPUT_PWDTEXT = By.id("pwdText");
	
	/**验证码输入框*/
	public static final By LP_INPUT_CAPTCHA = By.name("captcha");
	
	/**登录按钮*/
	public static final By LP_BUTTON_LOGIN = By.id("loginBtn");
	
	/**登录错误信息*/
	public static final By LP_TEXT_ERROR= By.xpath(".//*[@id='loginStatus']/td");

	
	
}
