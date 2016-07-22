/**   
* @Title: TestngRetry.java 
* @Package com.aoyou.test.util 
* @Description: TODO 
* @date 2015��8��3�� ����1:57:22 
* @version V1.0   
*/
package com.demo.test.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

/** 
 * @ClassName: TestngRetry 
 * @Description: TODO
 * @date 2015��8��3�� ����1:57:22  
 */
public class TestngRetry  implements IRetryAnalyzer {
	private static Logger logger = Logger.getLogger(TestngRetry.class);
	private int retryCount = 1;
	
	@Override
	public boolean retry(ITestResult result){
		if (retryCount <= Config.retryTimes) {
			String message = "running retry for  '" + result.getName() + "' on class " + 
            this.getClass().getSimpleName()+ " Retrying " + retryCount + " times";
			logger.info(message);
//			Reporter.setCurrentTestResult(result);
			retryCount++;
			return true;
		}
		return false;
	}
	
	
	
}
	
	

