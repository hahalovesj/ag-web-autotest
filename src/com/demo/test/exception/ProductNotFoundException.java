/**   
* @Title: ProductNotFound.java 
* @Package com.aoyou.test.exception 
* @Description: TODO 
* @date 2015��7��20�� ����2:52:43 
* @version V1.0   
*/
package com.demo.test.exception;

import com.demo.test.utils.Log;

/** 
 * @ClassName: ProductNotFound 
 * @Description: TODO
 * @date 2015��7��20�� ����2:52:43  
 */
@SuppressWarnings("serial")
public class ProductNotFoundException extends AoYouException{

	public ProductNotFoundException(String message) {
		super(message);
		Log.reporter(1, message);
	}

}
