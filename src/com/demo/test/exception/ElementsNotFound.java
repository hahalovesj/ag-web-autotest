/**   
* @Title: ElementsNotFound.java 
* @Package com.aoyou.test.exception 
* @Description: TODO 
* @date 2015��7��31�� ����10:35:51 
* @version V1.0   
*/
package com.demo.test.exception;

import com.demo.test.utils.Log;

/** 
 * @ClassName: ElementsNotFound 
 * @Description: TODO
 * @date 2015��7��31�� ����10:35:51  
 */
@SuppressWarnings("serial")
public class ElementsNotFound extends AoYouException{


	/**
	 * @param message
	 */
	public ElementsNotFound(String message) {
		super(message);
		Log.logWarn(message);
	}

}
