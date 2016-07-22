package com.demo.test.utils;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;
import com.demo.test.base.Data;;

/**
 * @Package com.aoyou.test.util
 * @ClassName Log
 * @Description 日志打印模块.日志级别:Info<Warn<Error
 * 3个公共接口方法:logError/logInfo/logWarn
 * 
 */
 
public class Log {
	
	private static Logger logger;
	private static String filePath="src/log4j.properties";
	private static boolean flag=false;
	
	private static synchronized void getPropertyFile(){	
		logger=Logger.getLogger("");
		PropertyConfigurator.configure(new File(filePath).getAbsolutePath());
		flag=true;
	}
	
	/**
	 * 初始化检查，如果是首次调用重新加载文件路径
	 */
	private static void getFlag(){		
		if(flag == false)
			Log.getPropertyFile();
	}
	
	/**
	 * 打印Info级别的日志
	 * @param message 需要打印的信息
	 */
	public static void logInfo(String message){
		Log.getFlag();
		logger.info("["+Data.currentClassName+"]"+message);
	}
	
	/**
	 * 打印Error级别的日志
	 * @param message 需要打印的信息
	 */
	public static void logError(String message){
		Log.getFlag();
		logger.error("["+Data.currentClassName+"]"+message);
		reporter(1,message);
	}
	
	/**
	 * 打印Warning级别的日志
	 * @param message 需要打印的信息
	 */
	public static void logWarn(String message){
		Log.getFlag();
		logger.warn("["+Data.currentClassName+"]"+message);
		reporter(2,message);
	}
	
	public static void reporter(int level,String message){
		switch(level){
		case 0:
			Reporter.log("["+Data.currentClassName+"]"+" [PASS]"+message);
			break;
		case 1:
			Reporter.log("["+Data.currentClassName+"]"+" [FAILED] "+message);
			break;	
		case 2:
			Reporter.log("["+Data.currentClassName+"]"+" [WARNING] "+message);
			break;	
		default:System.out.println("报告级别错误!");	
		}
		
	}
	
	
}
