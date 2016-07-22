package com.demo.test.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.demo.test.base.Data;
import com.demo.test.base.Locator;
import com.demo.test.exception.ProductNotFoundException;
import com.demo.test.utils.Config;
import com.demo.test.utils.Log;
import com.demo.test.utils.Regex;

/**
* 
* @ClassName: Action 
* @Description: 固定业务流程模块
* @date 2015年6月9日 下午5:17:07
*/

public class Action  {

	private Locator locator;
	private WebDriver driver;
	
	public Action(WebDriver driver){
		this.driver=driver;
		locator = new Locator(driver);
	}
	
	/**
	* @Description 测试结束后关闭浏览器
	*/
	public void closeBrowser(){		
		Data.theDriver.close();
		Data.theDriver.quit();
	}
	
	/** 
	* @Description 从首页顶部菜单项登录
	* @param username:登录用户名
	* @param pwd:登录密码
	*/
	public void login1(String username,String pwd){
		if(!locator.elementExist(false, "首页", "退出")){
	        locator.element("首页", "登录").click();
	        locator.element("登陆页", "用户名").clear();
	        locator.element("登陆页", "用户名").sendKeys(username);
	        locator.element("登陆页", "密码").sendKeys(pwd);
	        locator.element("登陆页", "提交").click();      
//	        	if(locator.elementExist(true, "首页", "欢迎信息"), true, "登录失败！用户名:"+username+"密码:"+pwd);
//	        checkPoint.result("登录成功！用户名:"+username+"密码:"+pwd);
		}else{
			Log.logInfo("已登录");
		}
	}
	
	/** 
	* @Description 搜索距离当前day天后的首个旅游产品，进入产品详情页
	* @param proType:产品类型  "全部","自由行","跟团","邮轮","耀悦","签证"
	* @param day:搜索的起始日期处理当前日期的天数
	*/
	public void searchProduct(String begincity){
		driver.get("http://www.aoyou.com/search/b0");
		locator.waitForPageLoad();
    	if(locator.elements("度假产品查询页", "更多城市").get(0).isDisplayed()){
    		locator.elements("度假产品查询页", "更多城市").get(0).click();
		}
		if(driver.findElement(By.xpath("//div[@class='screening-content-boxList search_cdts_items_div']/a[text()='"+begincity+"']")).isDisplayed()){
			driver.findElement(By.xpath("//div[@class='screening-content-boxList search_cdts_items_div']/a[text()='"+begincity+"']")).click();
		}else{
			throw new ProductNotFoundException(begincity+"出发，无产品可选");
		}
    	locator.element("度假产品查询页", "确认").click();
    	locator.sleep(2);
	}
	
	
	public void searchProduct(String proType,int day){
		List<String> por = new ArrayList<String>(Arrays.asList("全部","自由行","跟团","邮轮","耀悦","签证"));
		if(por.contains(proType)){
			
		try {
			driver.get("http://www.aoyou.com/search/b0");
		} catch (TimeoutException e) {
			Log.reporter(2, "页面加载超时:"+"http://www.aoyou.com/search/b0");
		}
		locator.waitForPageLoad();
		
    	if(locator.elements("度假产品查询页", "更多城市").get(0).isDisplayed()){
    		locator.elements("度假产品查询页", "更多城市").get(0).click();
		}
    	
		if(driver.findElement(By.xpath("//div[@class='screening-content-boxList search_cdts_items_div']/a[text()='"+Config.siteName+"']")).isDisplayed()){
			driver.findElement(By.xpath("//div[@class='screening-content-boxList search_cdts_items_div']/a[text()='"+Config.siteName+"']")).click();
		}else{
			throw new ProductNotFoundException(Config.siteName+"出发，无产品可选");
		}
		
		locator.removeReadOnly("度假产品查询页", "最早出发");
    	locator.element("度假产品查询页", "最早出发").clear();
    	locator.element("度假产品查询页", "最早出发").sendKeys(HandleDate.getForwardDayString(HandleDate.getNowDayString(), day));
    	locator.element("度假产品查询页", "确认").click();
    	if(locator.elementExist(false, "度假产品查询页", proType)){
    		locator.element("度假产品查询页", proType).click();
    	}else throw new ProductNotFoundException("未搜索到"+proType+"产品!");

    	locator.sleep(2);
		}else{
			Log.logInfo("产品类型错误！");
		}
	}
	
	public void searchProduct(String proType,String begincity,String arrycity,int day){
		List<String> por = new ArrayList<String>(Arrays.asList("全部","自由行","跟团","邮轮","耀悦","签证"));
		if(por.contains(proType)){
		driver.get("http://www.aoyou.com/search/b0");

    	locator.element("度假产品查询页","目的地").clear();
    	locator.element("度假产品查询页","目的地").sendKeys(arrycity);
    	locator.element("度假产品查询页","相关目的地").click();
    	locator.element("度假产品查询页", "去度假").click();
    	locator.waitForPageLoad();
    	
    	if(locator.elements("度假产品查询页", "更多城市").get(0).isDisplayed()){
    		locator.elements("度假产品查询页", "更多城市").get(0).click();
		}
    	
		if(driver.findElement(By.xpath("//div[@class='screening-content-boxList search_cdts_items_div']/a[text()='"+begincity+"']")).isDisplayed()){
			driver.findElement(By.xpath("//div[@class='screening-content-boxList search_cdts_items_div']/a[text()='"+begincity+"']")).click();
		}else{
			throw new ProductNotFoundException(begincity+"出发，无产品可选");
		}
    	
		locator.removeReadOnly("度假产品查询页", "最早出发");
    	locator.element("度假产品查询页", "最早出发").clear();
    	locator.element("度假产品查询页", "最早出发").sendKeys(HandleDate.getForwardDayString(HandleDate.getNowDayString(), day));
    	locator.element("度假产品查询页", "确认").click();
    	locator.waitForPageLoad();
    	
	    	if(locator.elementExist(false, "度假产品查询页", proType)){
	    		locator.element("度假产品查询页", proType).click();
	    	}else throw new ProductNotFoundException(begincity+"->"+arrycity+"未搜索到"+proType+"产品!");

    	locator.sleep(2);
		}else{
			Log.logInfo("产品类型错误！");
		}
	}
	
	public void searchProduct(String proType,String arrycity,int day){
		List<String> por = new ArrayList<String>(Arrays.asList("全部","自由行","跟团","邮轮","耀悦","签证"));
		if(por.contains(proType)){
		driver.get("http://www.aoyou.com/search/b0");
		locator.waitForPageLoad();
    	locator.element("度假产品查询页","目的地").clear();
    	locator.element("度假产品查询页","目的地").sendKeys(arrycity);
    	locator.element("度假产品查询页","相关目的地").click();
    	locator.element("度假产品查询页", "去度假").click();
    	locator.waitForPageLoad();

    	if(locator.elements("度假产品查询页", "更多城市").get(0).isDisplayed()){
    		locator.elements("度假产品查询页", "更多城市").get(0).click();
		}
    	
		if(driver.findElement(By.xpath("//div[@class='screening-content-boxList search_cdts_items_div']/a[text()='"+Config.siteName+"']")).isDisplayed()){
			driver.findElement(By.xpath("//div[@class='screening-content-boxList search_cdts_items_div']/a[text()='"+Config.siteName+"']")).click();
		}else{
			throw new ProductNotFoundException(Config.siteName+"出发，无产品可选");
		}
		
		locator.removeReadOnly("度假产品查询页", "最早出发");
    	locator.element("度假产品查询页", "最早出发").clear();
    	locator.element("度假产品查询页", "最早出发").sendKeys(HandleDate.getForwardDayString(HandleDate.getNowDayString(), day));
    	locator.element("度假产品查询页", "确认").click();
    	if(locator.elementExist(false, "度假产品查询页", proType)){
    		locator.element("度假产品查询页", proType).click();
    	}else throw new ProductNotFoundException(Config.siteName+"->"+arrycity+"未搜索到"+proType+"产品!");
    	locator.sleep(2);
		}else{
			Log.logInfo("产品类型错误！");
		}
	}
	
	
	/**
	* @Description 随机选择产品列表中的一个,并返回随机索引值
	* @return int 
	 */
	public int selectOneProduct(){
    	int size = locator.elements("度假产品查询页", "立即预定").size();
    	int index = (int)(Math.random()*size);
    	driver.get(locator.elements("度假产品查询页", "立即预定").get(index).getAttribute("href"));
    	locator.waitForPageLoad();
		return index;
	}
	
	/**
	* @Description 选择产品列表中的的第index个
	* @return void 
	 */
	public void selectTheProduct(int index){
    	driver.get(locator.elements("度假产品查询页", "立即预定").get(index).getAttribute("href"));
    	locator.waitForPageLoad();
	}
		
	public String getOneMonthPrice(){
		String Text = null;
        String[] text = null;
		text = locator.elementsText("产品详情页", "日历价格");
			for(int i=0;i<text.length;i++){	
				text[i]=text[i].substring(1);
				text[i]="￥"+text[i];
			}
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < text.length; i ++) {
			sb.append(text[i]);
			}
			Text = sb.toString();
		return Text;		
	}
	
	public ArrayList<String> getAllPrices(){
		locator.sleep(5);
        ArrayList<String> results = new ArrayList<String>();
        String text=null;
        
        int size = locator.elements("产品详情页", "日历月份").size()-1;   
    	if(size==-1){
    		Log.logError("从价格日历获取信息失败");
    		return null;
    	}
	    	if(size==0){
	    		text=this.getOneMonthPrice();
	    		results = Regex.matchStringList(text, "￥[0-9]+",1);
    	}else if(size>0){
    		text = text + this.getOneMonthPrice();
	    	for(int i=0;i<size;i++){
	    		locator.element("产品详情页", "下一个月").click();
	    		locator.action().moveToElement(locator.element("产品详情页", "产品特色")).perform();  
	    		text = text + this.getOneMonthPrice();
	    	}
	    	results = Regex.matchStringList(text,"￥[0-9]+",1);
    	 }else Log.logError("价格日历没有成功加载");
		return results;
	}
	
	/** 
	* @Description 选择当前旅游产品详情页中日期最晚的报价，进入填单页
	*/
	public void selectTheLastPrice(){
		locator.sleep(3);
    	int size = locator.elements("产品详情页", "日历月份").size()-1;
    	if(size==-1){
    		Log.logError("从价格日历获取信息失败");
    		return;
    	}
    	if(size==0){
    		;
    	}else{
	    	for(int i=0;i<size;i++){
	    		locator.element("产品详情页", "下一个月").click();
	    		locator.action().moveToElement(locator.element("产品详情页", "产品特色")).perform();
	    	}
    	 }
    	if(locator.elements("产品详情页", "低价价格链接").size()>0){
    	locator.theLastElement("产品详情页", "低价价格链接").click();
    	locator.sleep(2);
    	}else {
    		Log.logWarn("当月无可售团期");
    		return;
    	 }
    	if (locator.elementExist(false, "产品详情页", "成人数")){
//    	locator.selectByValue("产品详情页", "成人数", "2");
    	locator.selectByValue("产品详情页", "儿童数", "0");
    	locator.sleep(1);
    	}
    	locator.element("产品详情页", "下一步").click();
    	locator.waitForPageLoad();
	}
	
	/**
	* @Description 填写订单并提交，返回填单中的产品信息和金额信息
	* @return String[] 返回存有下单过程中重要信息的字符串数组。String[0]：产品编号。 String[1]：产品标题。 String[2]：订单总金额
	*/
	public String[] fillInTheOrder(String linkname,String name,String idCardType,String idCardNo,String phoneNo,String birthday){
		String[] proDetil = new String[4];
		int passengerNumber=0;
		if(locator.elementExist(false, "填单页", "下一步2")){
			locator.element("填单页", "下一步2").click();
			locator.waitForPageLoad();
		}
	
		if(locator.elementExist(false, "填单页", "二次确认提示")){
			Log.logWarn("该产品为二次确认产品，暂时不能下单");
			return null;
		}
		
		passengerNumber = locator.elements("填单页","旅客信息").size();
	   	locator.element("填单页", "联系人").sendKeys(linkname);
	   	
	   	//填写N个一样的人员信息
	   	for(int i=0;i<passengerNumber;i++){
	    	locator.elements("填单页", "姓名").get(i).sendKeys(name);
	    	locator.selectByValue(locator.elements("填单页", "证件类型").get(i), idCardType);
	    	locator.elements("填单页", "证件号").get(i).sendKeys(idCardNo);
	    	locator.elements("填单页", "手机号").get(i).sendKeys(phoneNo);
	    	
	    	if(!idCardType.equals("1")){
	    	String js = "var js=document.getElementById('txtbirth"+i+"');js.removeAttribute('readonly');";
	    	//"var js=document.getElementById('txtbirth0');js.removeAttribute('readonly');"
	    	locator.addJs(js);
	    	locator.elements("填单页", "出生日期").get(i).sendKeys(birthday);
	    	}
	    	
	    	if(locator.elementExist(false, "填单页", "稍后提供")){
	    		locator.elements("填单页", "稍后提供").get(i).click();
	    	}
//	    		locator.element("填单页", "旅行证件号").sendKeys("222");
//	    		locator.removeReadOnly("填单页", "旅行证件有效期");
//	    		locator.element("填单页", "旅行证件有效期").sendKeys("2019-05-01");
//	    		locator.removeReadOnly("填单页", "旅行证件签发地");
//	    		locator.element("填单页", "旅行证件签发地").sendKeys("北京");

	   	}
    	locator.element("填单页", "下一步").click();
    	locator.waitForPageLoad();
    	proDetil[0] =locator.element("填单页","产品编号").getText().trim();
    	proDetil[1] = locator.element("填单页","产品标题").getText().trim();
    	proDetil[2] = locator.element("填单页","合计").getText().trim();   
    	proDetil[3] = locator.element("填单页","合同内容").getText();  
    	locator.element("填单页", "已阅读订单信息").click();
    	locator.element("填单页", "下一步").click();
    	locator.waitForPageLoad();
		return proDetil;
	}
	
	/**
	* @Description 检索支付页订单信息
	* @return String[] 返回存有支付页面订单重要信息的字符串数组。
	* String[0]：产品编号。 String[1]：产品标题。 String[2]：待支付金额。String[3]：待支付金额。
	*/
	public String[] getOrderPayInfo(){
		String[] proDetil = new String[4];
    	proDetil[0] = locator.element("支付页","产品编号").getText().trim();
    	proDetil[1] = locator.element("支付页","产品标题").getText().trim();
    	proDetil[2] = Regex.matchString(locator.element("支付页","待支付金额").getText().trim(), "￥[0-9]+",1);
    	proDetil[3] = Regex.matchString(locator.element("支付页","订单号").getText().trim(),"[0-9]+",0);
		return proDetil;
	}
	
	/** 
	* @Description 取消订单，并检查状态（最多尝试取消3次）
	* @param url:订单中心地址
	*/
	public boolean cancelTheOrder(String url){
	   	int count=0;			//循环次数
    	boolean flag=true;	//循环标志位
    	boolean result=true;
    	
//    	打开订单中心连接，遍历订单状态。当出现"待支付"时，取消订单。此时不退出，再刷新页面检查一下
    	while(flag){
    		flag=false;		//当所有订单都没有"待支付"状态时，退出循环
    		if(count<6){
    			for(int i=0;i<3;i++){
    			driver.get(url);
    			locator.sleep(2);
    			}
    	    	String[] order = locator.elementsText("会员中心", "订单状态");
			    	for(int i=1;i<order.length;i++){
			    		if(order[i].equalsIgnoreCase("待支付")){
			    			locator.element("会员中心", "取消订单").click();
			    			locator.element("会员中心", "取消订单原因").sendKeys("测试");
			    			locator.element("会员中心", "确认取消").click();
			    			driver.switchTo().alert().accept();			    			
			    			flag=true;
			    		}
			    	}	
    		}   	
    		count=count+1;  
    	}
//    	当循环5次后，仍然有未取消的订单。输出错误日志，短信报警
    	if(count>5){			
    		Log.logError("因取消订单失败!本次构建已停止");
    		result=false;
    		System.exit(1);
    	}
		return result;
    }
    
	public boolean cancelTheOrderForTicket(String url){
	   	int count=0;			//循环次数
    	boolean flag=true;	//循环标志位
    	boolean result=true;
    	
//    	打开订单中心连接，遍历订单状态。当出现"支付"时，取消订单。此时不退出，再刷新页面检查一下
    	while(flag){
    		flag=false;		//当所有订单都没有"待支付"状态时，退出循环
    		if(count<6){
    			for(int i=0;i<2;i++){
    			driver.get(url);
    			locator.sleep(2);
    			}
    				if(!locator.elementExist(false, "门票会员中心", "订单状态")){
    					break;
    				}
    	    		String[] order = locator.elementsText("门票会员中心", "订单状态");
    	    		
			    	for(int i=0;i<order.length;i++){
			    		if(order[i].equalsIgnoreCase("支付")){
			    
			    			locator.element("门票会员中心", "取消订单").click();
			    			locator.element("会员中心", "取消订单原因").sendKeys("测试");
			    			locator.element("会员中心", "确认取消").click();
			    			driver.switchTo().alert().accept();			    			
			    			flag=true;
			    		}
			    	}	
    		}   	
    		count=count+1;  
    	}
//    	当循环5次后，仍然有未取消的订单。输出错误日志，短信报警
    	if(count>5){			
    		Log.logError("因取消订单失败!本次构建已停止");
    		result=false;
    		System.exit(1);
    	}
		return result;
    }

	
	public boolean checkIamges(String pageKey,String objKey){
		int failedNumber=0;
		Boolean flag =null;
		List<WebElement> imageList = locator.elements(pageKey, objKey);
		
			for(WebElement e:imageList){
				if(!e.isDisplayed()){
					Log.logError("图片未显示");
					failedNumber = failedNumber+1;
				}
			}
		
		String[] imageSrc = locator.elementsAttribute(pageKey, objKey, "src");
		flag = URLAvailability.isConnect(imageSrc);
		
		if(flag && failedNumber==0 ){
			return true;
		}else return false;
	}
	
	/**
	* @Description 根据产品类型，获取该类产品的所有产品详情页静态链接
	* @param proName产品类型：自由行、跟团、耀悦
	* @return List<String> 
	*/
	 public List<String> getAllProductLink(String proName){
		 List<String>domesticUrl = new ArrayList<String>(); 
		 List<String>temp = new ArrayList<String>();
		 int pageSize = 0;
		 
		 locator.element("度假产品查询页", proName).click();
		 locator.waitForPageLoad();

		 if(locator.elementExist(false, "度假产品查询页", "翻页跳转")&&locator.elementExist(false, "度假产品查询页", "页数链接")){
			 pageSize = Integer.parseInt(locator.elements("度假产品查询页", "页数链接").get(locator.elements("度假产品查询页", "页数链接").size()-4).getText().trim());
		 }else if(locator.elementExist(false, "度假产品查询页", "页数链接")&&!locator.elementExist(false, "度假产品查询页", "翻页跳转")){
			 pageSize = Integer.parseInt(locator.elements("度假产品查询页", "页数链接").get(locator.elements("度假产品查询页", "页数链接").size()-2).getText().trim());
		 }else{
			 pageSize=0;
			 temp = locator.elementsAttributeToList("度假产品查询页", "立即预定", "href");
			 domesticUrl.addAll(temp);
		 }
		 
		 for(int i=0;i<pageSize;i++){
			 temp = locator.elementsAttributeToList("度假产品查询页", "立即预定", "href");
			 domesticUrl.addAll(temp);
			 locator.element("度假产品查询页", "下一页").click();
			 locator.waitForPageLoad();
		 }
		return domesticUrl;	 
	 }
	 
	 public List<String> getAllProductPrice(String proName){
	     List<String>temp = new ArrayList<String>();
	     List<String>results = new ArrayList<String>();
	     String text;
	     int pageSize;
		 locator.element("度假产品查询页", proName).click();
		 locator.waitForPageLoad();

		 if(locator.elementExist(false, "度假产品查询页", "翻页跳转")&&locator.elementExist(false, "度假产品查询页", "页数链接")){
			 pageSize = Integer.parseInt(locator.elements("度假产品查询页", "页数链接").get(locator.elements("度假产品查询页", "页数链接").size()-4).getText().trim());
		 }else if(locator.elementExist(false, "度假产品查询页", "页数链接")&&!locator.elementExist(false, "度假产品查询页", "翻页跳转")){
			 pageSize = Integer.parseInt(locator.elements("度假产品查询页", "页数链接").get(locator.elements("度假产品查询页", "页数链接").size()-2).getText().trim());
		 }else{
			 pageSize=0;
			 temp = locator.elementsAttributeToList("度假产品查询页", "立即预定", "href");
			 results.addAll(temp);
		 }
		 
	    	for(int i=0;i<pageSize;i++){
				 text = locator.element("度假产品查询页", "产品内容信息").getText();
				 temp = Regex.matchStringList(text, "￥[0-9]+ ",1);
				 results.addAll(temp);
				 locator.element("度假产品查询页", "下一页").click();
				 locator.waitForPageLoad();
			}
		return results;	 
	 }
	 
}
