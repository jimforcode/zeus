package com.zeus.controller;

import java.util.HashMap;
import java.util.Map;

/***
 * 封装json返回数据
 * 
 * @author zengjin
 *
 */
public abstract class BaseController {

	public static final String RESULT_STATUS_OK = "OK";
	public static final String RESULT_STATUS_ERROR = "ERROR";

	public static final String RESULT_KEY_MESSAGE = "message";

	public static final String RESULT_KEY_RESULT = "result";
	public static final String RESULT_KEY_STATUS = "status";

	protected Map<String, Object> resultOK() {
		HashMap<String, Object> messageOK;

		messageOK = new HashMap<>();
		messageOK.put(RESULT_KEY_STATUS, RESULT_STATUS_OK);
		return messageOK;
	}

	protected Map<String, Object> resultOK(Object obj) {
		HashMap<String, Object> messageOK;

		messageOK = new HashMap<>();
		messageOK.put(RESULT_KEY_STATUS, RESULT_STATUS_OK);
		messageOK.put(RESULT_KEY_RESULT, obj);
		return messageOK;
	}

	protected Map<String, Object> resultOK(String key, Object value) {
		HashMap<String, Object> messageOK;

		messageOK = new HashMap<>();
		messageOK.put(RESULT_KEY_STATUS, RESULT_STATUS_OK);
		messageOK.put(key, value);
		return messageOK;
	}

	protected Map<String, Object> resultError(String message) {
		HashMap<String, Object> ret;
		ret = new HashMap<>();
		ret.put(RESULT_KEY_STATUS, RESULT_STATUS_ERROR);
		ret.put(RESULT_KEY_MESSAGE, message);
		return ret;
	}

}
