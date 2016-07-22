/**   
* @Title: RetryListener.java 
* @Package com.aoyou.test.util 
* @Description: TODO 
* @date 2015��8��3�� ����2:03:25 
* @version V1.0   
*/
package com.demo.test.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

/** 
 * @ClassName: RetryListener 
 * @Description: TODO
 * @date 2015��8��3�� ����2:03:25  
 */
public class RetryListener implements IAnnotationTransformer{
	
	@Override
	public void transform(ITestAnnotation annotation, @SuppressWarnings("rawtypes") Class testClass, 

	@SuppressWarnings("rawtypes") 
	Constructor testConstructor, Method testMethod) {
	IRetryAnalyzer retry = annotation.getRetryAnalyzer();
       if (retry == null) {
          annotation.setRetryAnalyzer(TestngRetry.class);
        }		
	}
	
	
}
