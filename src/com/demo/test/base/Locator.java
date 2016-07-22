package com.demo.test.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.demo.test.exception.ElementsNotFound;
import com.demo.test.functions.HandleString;
import com.demo.test.utils.Config;
import com.demo.test.utils.Log;
import com.demo.test.utils.ParseXml;
import com.google.common.base.Function;

/**
* 
* @ClassName: Locator 
* @Description: 对象查找引擎模块
* 提供18个公共接口方法：action/selectByValue/selectByIndex/selectByVisibleText/selectEveryOne/
* 					   removeReadOnly/element/elementNoWait/elementExist/elements/waitElementToBeNonDisplayed
*                      elementsAttribute/elementsText/linkAndGetText/linkAndCheckPage/waitForPageLoad/sleep
*                      
* @date 2015年6月10日 下午2:38:29
*/
public class Locator {	
	private WebDriver driver;
	private String filePath; 	//filePath 对象库xml文件的路径，在config文件中配置
	private ParseXml px;
	private Boolean existFlag;
	
	/**
	 * @param driver
	 */
	public Locator(WebDriver driver){
		this.driver = driver;
		filePath=Config.objectRespository;
		px = new ParseXml(filePath);
		existFlag =true;
	}	
	
	/**
	* @Description 实例化webdriver的原始Actions类，方便使用
	*/
	public Actions action(){	
		Actions action = new Actions(driver);
		return action;
	}
	
	/**
	* @Description  操作下拉列表，通过下拉列表中的选项的value属性选中某项
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @param value
	* @return void
	*/
	public void selectByValue(String pageKey,String objKey,String value){
		Select select = new Select(this.getLocator(pageKey, objKey, true,true));
		select.selectByValue(value);	
	}
	public void selectByValue(WebElement element,String value){
		Select select = new Select(element);
		select.selectByValue(value);	
	}
	
	/**
	* @Description  操作下拉列表，通过下拉列表中选项的索引选中某项
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @param index 索引值
	* @return void
	*/
	public void selectByIndex(String pageKey,String objKey,int index){
		Select select = new Select(this.getLocator(pageKey, objKey, true,true));
		select.selectByIndex(index);		
	}
	public void selectByIndex(WebElement element,int index){
		Select select = new Select(element);
		select.selectByIndex(index);		
	}
	
	/**
	* @Description  操作下拉列表，通过下拉列表中选项的可见文本选中某项
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @param text 文本值
	* @return void
	*/
	public void selectByVisibleText(String pageKey,String objKey,String text){
		Select select = new Select(this.getLocator(pageKey, objKey, true,true));
		select.selectByVisibleText(text);	
	}
	public void selectByVisibleText(WebElement element,String text){
		Select select = new Select(element);
		select.selectByVisibleText(text);	
	}
	/**
	* @Description  操作下拉列表，遍历下拉列表所有选项，用click进行选中选项
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @return void
	*/
	public void selectEveryOne(String pageKey,String objKey){
	  Select select = new Select(this.getLocator(pageKey, objKey, true,true));
	  for(WebElement e : select.getOptions())
	   e.click();
	}
	
	/**
	* @Description  通过引用JavaScript脚本，去除指定元素的read only属性。
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @return void
	*/
	public void removeReadOnly(String pageKey,String objKey){
    	if (px.isExist("/对象/"+pageKey+"/"+objKey)) {	 
		String type = px.getElementText("/对象/"+pageKey+"/"+objKey+"/type");
		String value = px.getElementText("/对象/"+pageKey+"/"+objKey+"/value");	  
		JavascriptExecutor removeAttribute = (JavascriptExecutor)driver;
		
		//document中的getElementBy的属性值首字母都是大写，所以转换一下
		String js ="var js=document.getElementBy"+HandleString.toUpperCaseFirstOne(type)+"(\""+value+"\");js.removeAttribute('readonly');";
		removeAttribute.executeScript(js);
    	}else{
    		Log.logError("【Locator】 "+ pageKey+"-"+objKey + " is not exist in " + filePath);//xml中别名不存在
    	}
	}
	public void removeReadOnly(String pageKey,String objKey,String index){
    	if (px.isExist("/对象/"+pageKey+"/"+objKey)) {	 
		String type = px.getElementText("/对象/"+pageKey+"/"+objKey+"/type");
		String value = px.getElementText("/对象/"+pageKey+"/"+objKey+"/value");	  
		JavascriptExecutor removeAttribute = (JavascriptExecutor)driver;
		
		//document中的getElementBy的属性值首字母都是大写，所以转换一下
		String js ="var js=document.getElementBy"+HandleString.toUpperCaseFirstOne(type)+"(\""+value+index+"\");js.removeAttribute('readonly');";
		removeAttribute.executeScript(js);
    	}else{
    		Log.logError("【Locator】 "+pageKey+"-"+objKey + " is not exist in " + filePath);//xml中别名不存在
    	}
	}
	public void addJs(String js){
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript(js);
	}

	
	
	/**
	* @Description 根据对象xml，查找相应的单个页面元素，返回WebElement。默认最长等待时间为config.xml中的配置
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @see getLocator
	* @return WebElement 
	*/
	public WebElement element(String pageKey,String objKey){
		return this.getLocator(pageKey, objKey,true,true);				
	}

	/**
	* @Description 根据对象xml，查找相应的单个页面元素，返回WebElement。无等待时间
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @see getLocator
	* @return WebElement 
	*/
	public WebElement elementNoWait(String pageKey,String objKey) {		
		return this.getLocator(pageKey, objKey, false,true);		
	}
	
	/**
	* @Description 根据对象xml，查找同一个定位条件下的多个页面元素，返回WebElement列表
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @see getLocators
	* @return List<WebElement>
	*/
	public List<WebElement> elements(String pageKey,String objKey){
		return this.getLocators(pageKey,objKey);		
	}
	
	/** 
	* @Description 根据对象xml，查找同一个定位条件下的多个页面元素，返回列表中第一个对象
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @return WebElement 
	*/
	public WebElement theFirstElement(String pageKey,String objKey){
		List<WebElement> elements =this.getLocators(pageKey,objKey);	
		return elements.get(0);
	}
	
	/** 
	* @Description 根据对象xml，查找同一个定位条件下的多个页面元素，返回列表中最后一个对象
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @return WebElement 
	*/
	public WebElement theLastElement(String pageKey,String objKey){
		List<WebElement> elements =this.getLocators(pageKey,objKey);	
		return elements.get(elements.size()-1);
	}
	
	/** 
	* @Description 根据对象xml，查找同一个定位条件下的多个页面元素，返回列表中的一个随机对象
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @return WebElement 
	*/
	public WebElement theRandomElement(String pageKey,String objKey){
		List<WebElement> elements =this.getLocators(pageKey,objKey);	
		int index = (int)(Math.random()*elements.size());
		return elements.get(index);
	}
	
	/**
	* @Description 根据对象xml，判断某个页面元素是否存在，返回布尔表达式
	* @param wait  布尔型变量，是否需要等待
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @see getLocator
	* @return boolean
	*/
	public boolean elementExist(boolean wait,String pageKey,String objKey){		
		if (wait){
			this.getLocator(pageKey, objKey,true,false);
			return existFlag;
		}
		else{
			this.getLocator(pageKey, objKey,false,false);
			return existFlag;
		}
	}
	
	/**
	* @Description 等待指定的element是否在当前页面不可见。隐藏时返回true
	* @param  element WebElement对象
	* @return boolean 
	* @throws Exception：element.isDisplayed() 记录错误日志
	*/
	public boolean waitElementToBeNonDisplayed(final WebElement element) {
	    boolean wait = false;
	    if (element == null)
	        return wait;
	    try {
	        wait = new WebDriverWait(driver, Config.objectWaitTime)
	                .until(new ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver d) {
	                        return !element.isDisplayed();
	                    }
	                });
	    } catch (Exception e) {
//	        Log.logError("Locator [" + element.toString()
//	                + "] is also displayed");
	    }
	    return wait;
	}
	
	/**
	* @Description 根据对象xml，查找同一个定位条件下的多个页面元素的固定一个属性值，返回字符串数组
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @param attribute 要得到的属性名
	* @return String[]
	*/
	public String[] elementsAttribute(String pageKey,String objKey,String attribute){		
		String []attributes = new String[this.elements(pageKey, objKey).size()];
		int size = this.elements(pageKey, objKey).size();
			for(int i=0;i<size;i++){		
				attributes[i] = this.elements(pageKey, objKey).get(i).getAttribute(attribute);
//				可以还这种写法
//				String [] arrtibutest2=new String[size];
//				arrtibutest2[i] = this.getLocators(pageKey, objKey).get(i).getAttribute(attribute);
			}		
		return attributes;		
	}
	
	public List<String> elementsAttributeToList(String pageKey,String objKey,String attribute){		
		List<String> list= new ArrayList<String>();
		int size = this.elements(pageKey, objKey).size();
			for(int i=0;i<size;i++){		
				list.add(i, this.elements(pageKey, objKey).get(i).getAttribute(attribute));				
			}		
		return list;		
	}
	
	/**
	* @Description 根据对象xml，查找同一个定位条件下的多个页面元素的Text值，返回字符串数组
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @return String[] 
	*/
	public String[] elementsText(String pageKey,String objKey){		
		String []text = new String[this.elements(pageKey, objKey).size()];		
			for(int i=0;i<this.elements(pageKey, objKey).size();i++){			
				text[i] = this.elements(pageKey, objKey).get(i).getText();			
			}		
		return text;		
	}
	
	public List<String> elementsTextForList(String pageKey,String objKey){	
		List<String> results = new ArrayList<String>();
		String []text = new String[this.elements(pageKey, objKey).size()];		
			for(int i=0;i<this.elements(pageKey, objKey).size();i++){			
				text[i] = this.elements(pageKey, objKey).get(i).getText();	
			}	
			
			results =Arrays.asList(text);
			
		return results;		
	}

	
	/**
	* @Description 遍历指定url，跳转后获取页面指定元素的Text值。返回字符串数组
	* @param url 需要遍历的url数组
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @return String[]
	*/
	public  String[] linkAndGetText(String[] url,String pageKey,String objKey){	
		String[] texts = new String[url.length];	
			for(int i=0;i<url.length;i++){		
				try {
					driver.get(url[i]);
				} catch (TimeoutException e) {
					Log.reporter(2, "页面加载超时:"+url[i]);
				}
				texts[i] = this.element(pageKey,objKey).getText();
			}	
		return texts;		
	}
	
	public  String[]  linkAndGetText(List<String> url,String pageKey,String objKey){	
		String[] texts = new String[url.size()];
		int size = url.size();
			for(int i=0;i<size;i++){		
				try {
					driver.get(url.get(i));
				} catch (TimeoutException e) {
					Log.reporter(2, "页面加载超时:"+url.get(i));
				}
				texts[i] = this.element(pageKey,objKey).getText().trim();
			}	
		return texts;		
	}
	
	
	/**
	* @Description 遍历指定url，跳转后检查页面某个指定元素是否存在。返回布尔表达式
	* @param url 需要遍历的url数组
	* @param pageKey 对象xml中的页面名称
	* @param objKey  对象xml中的对象名称
	* @return boolean
	*/
	public  boolean linkAndCheckPage(String[] url,String pageKey,String objKey){	
		boolean existFlag = true;		
			for(int i=0;i<url.length;i++){			
				try {
					driver.get(url[i]);
				} catch (TimeoutException e) {
					Log.reporter(2, "页面加载超时:"+url[i]);
				}
				this.waitForPageLoad();			
					if(!this.elementExist(false, pageKey, objKey)){
						Log.logError(url[i]+":链接异常！");
						existFlag=false;
					}
			}
		return existFlag;		
	}
	
	public  boolean linkAndCheckPage(String url,String pageKey,String objKey){	
		boolean existFlag = true;			
		try {
			driver.get(url);
		} catch (TimeoutException e) {
			Log.reporter(2, "页面加载超时:"+url);
		}
				this.waitForPageLoad();			
					if(!this.elementExist(false, pageKey, objKey)){
						Log.logError(url+":链接异常！");
						existFlag=false;
					}
		return existFlag;		
	}
	
	/**
	* @Description 等待页面加载完成。最长等待时间在config.xml中配置
	* @return void 
	* @throws Exception:超过config.xml配置的时间后，页面加载超时
	*/
	public void waitForPageLoad() {		
		try {			
			WebDriverWait wait = new WebDriverWait(driver, Config.pageWaitTime);
			wait.until(isPageLoaded());	        
	    }catch(Exception e){	    	
	    	Log.logError("【Locator】 "+" page is not load until "+driver.getCurrentUrl()+ Config.pageWaitTime);
	    }	
	}
	
	/**
	* @Description 固定时间等待
	* @param time固定等待的时长，单位为：秒
	* @return void 
	*/
	public void sleep(int time){
		try {
			int sTime = time*1000;
			Thread.sleep(sTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	根据从对象xml读取的type和value值，组合成find element 所需参数值。返回By对象

	private By getBy(String type ,String value){
		By by = null;
		switch(type){
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "className":
			by = By.className(value);	
			break;
		case "tagName":
			by = By.tagName(value);	
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;	
		case "cssSelector":
			by = By.cssSelector(value);
			break; 	
		}
		return by;	 
	}
	
//	在config.xml配置的对象最长等待时间内，定位指定by的元素
//	定位成功，existFlag置为true。否则抛出Exception，打印错误日志
	private WebElement waitForElement(final By by,boolean log,String pageKey,String objKey) {
	    WebElement element = null;    
	    try {
	        element = new WebDriverWait(driver, Config.objectWaitTime)
	                .until(new ExpectedCondition<WebElement>() {
	                    public WebElement apply(WebDriver d) {
	                    	existFlag = true;	//对象是否存在的标志位
	                        return d.findElement(by);
	                    }
	                });
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	if(log){
	        Log.logError("【Locator】 "+pageKey + "  "+objKey+" is not exist until " + Config.objectWaitTime);
	    	}
	        existFlag = false;
	    }
	    return element;
	}
	
//	在config.xml配置的对象最长等待时间内，判断页面元素是否可见
//	元素可见，返回值为true。否则抛出Exception，打印错误日志	
	private boolean waitElementToBeDisplayed(final WebElement element,String page,String obj) {
	    boolean wait = false;
	    if (element == null)
	        return wait;
	    try {
	        wait = new WebDriverWait(driver, Config.objectWaitTime)
	                .until(new ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver d) {
	                        return element.isDisplayed();
	                    }
	                });
	    } catch (Exception e) {
	    	Log.logError("【Locator】 "+page + "  "+obj + " is not displayed");
	    }
	    return wait;
	}
	
	/**
	* @Description 单个页面元素查找引擎
	* @param wait 是否需要等待,最长等待时间在config.xml中配置
	* @param log  是否需要打印错误日志
	* @return WebElement 
	* @throws Exception:捕获driver.findElement异常。未定位到页面元素，打印错误日志
	*/
	private WebElement getLocator(String pageKey,String objKey, boolean wait,boolean log) {	
	    WebElement element = null;
	    	if (px.isExist("/对象/"+pageKey+"/"+objKey)) {	    	
	    		//动态查找xml中的对象对应的type和value
	        	String type = px.getElementText("/对象/"+pageKey+"/"+objKey+"/type");
	        	String value = px.getElementText("/对象/"+pageKey+"/"+objKey+"/value");	    		
	        By by = this.getBy(type, value);	        
	        if (wait) {
	        	//判断页面元素是否存在且可见
	            element = this.waitForElement(by,log,pageKey,objKey);
	            boolean flag = this.waitElementToBeDisplayed(element,pageKey,objKey);
	            if (!flag)
	                element = null;
	        } else {
	            try {
	                element = driver.findElement(by);
	                existFlag = true;	  //elementExist使用              
	            } catch (Exception e) {
	                element = null;
	                if(log){
	                Log.logError("【Locator】 "+pageKey+"-"+objKey+" "+type+":"+value+" is not found!!!");
	                }
	                existFlag = false;	 //elementExist使用
	            }
	        }
	    } else
	        Log.logError("【Locator】 "+ pageKey+"-"+objKey + " is not exist in " + filePath);//xml中别名不存在
	    return element;
	}
	
	/**
	* @Description 多个页面元素查找引擎，返回WebElement列表。当有元素定位失败时，打印错误日志。终止当前.java代码
	* @return List<WebElement> 
	*/
	private List<WebElement> getLocators(String pageKey,String objKey) {	
	    List <WebElement> elements = null;
	    	if (px.isExist("/对象/"+pageKey+"/"+objKey)) {	    		
	        	String type = px.getElementText("/对象/"+pageKey+"/"+objKey+"/type");
	        	String value = px.getElementText("/对象/"+pageKey+"/"+objKey+"/value");	    		
	        By by = this.getBy(type, value);	        
	                elements = driver.findElements(by);	 
	                
	                if(elements.size()==0){
	                	throw new ElementsNotFound("【Locators】"+pageKey+"-"+objKey+" :objects not found!!!");
	                }
	                	for(WebElement e : elements){
	                		if(e.equals("")){
	                			Log.logError("【Locators】 "+pageKey+"-"+objKey+" :"+type+" :"+value+" some objects not found!!!");
	                			Assert.assertFalse(true);
	                		}
	                	}
//	                	if (elements.size()==0){
//	                		Log.logError(pageKey+"-"+objKey+" :"+type+" :"+value+" is not found!!!");
//	                		Assert.assertFalse(true);
//	                	}                   	
	    	}else 	    		
	           Log.logError("【Locator】 "+pageKey+"-"+objKey + " is not exist in " + filePath);	    	
	    return elements;
	}
	
	//waitForPageLoad方法的具体实现函数
	private Function<WebDriver, Boolean> isPageLoaded() {
	        return new Function<WebDriver, Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
	 }
	
}


