
package com.demo.test.exception;

import com.demo.test.utils.Log;

/** 
 * @ClassName: ProductCanNotOrder 
 * @Description: TODO
 * @date 2015��7��28�� ����2:59:07  
 */
@SuppressWarnings("serial")
public class ProductCanNotOrder extends AoYouException{
	public ProductCanNotOrder(String message) {
		super(message);
		Log.reporter(1, message);
	}
}
