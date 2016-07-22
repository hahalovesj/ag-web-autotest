package com.practice;

import java.io.File;
import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class partialLinkText {
  
  
  public static void main(String[] args) {
	  WebDriver driver ;
	  System.setProperty("webdriver.chrome.driver","res/driver/chrome/win/chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get("http://www.baidu.com");
	  driver.manage().window().maximize();
	  WebElement wet1;
	  wet1 = driver.findElement(By.partialLinkText("米"));
	  String string1 = wet1.getText();
	  System.out.println(string1);
	  wet1.click();
	
	  
	  
  }
  
}
