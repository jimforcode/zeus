package com.zeus.controller;

import com.google.gson.Gson;
import com.zeus.common.config.ApiCfg;
import com.zeus.common.exception.AuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/***
 *
 * @author zengjin
 *
 */
public abstract class BaseController {

    @Autowired
    ApiCfg apiCfg;

    public static final String RESULT_STATUS_OK = "OK";
    public static final String RESULT_STATUS_ERROR = "ERROR";

    public static final String RESULT_KEY_MESSAGE = "message";

    public static final String RESULT_KEY_RESULT = "result";
    public static final String RESULT_KEY_STATUS = "status";

    Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Map<String, Object> resultOK() {
        HashMap<String, Object> messageOK;

        messageOK = new HashMap<String, Object>();
        messageOK.put(RESULT_KEY_STATUS, RESULT_STATUS_OK);
        return messageOK;
    }

    protected Map<String, Object> resultOK(Object obj) {
        HashMap<String, Object> messageOK;

        messageOK = new HashMap<String, Object>();
        messageOK.put(RESULT_KEY_STATUS, RESULT_STATUS_OK);
        messageOK.put(RESULT_KEY_RESULT, new Gson().toJson(obj));
        return messageOK;
    }

    protected Map<String, Object> resultOK(String key, Object value) {
        HashMap<String, Object> messageOK;

        messageOK = new HashMap<String, Object>();
        messageOK.put(RESULT_KEY_STATUS, RESULT_STATUS_OK);
        messageOK.put(key, new Gson().toJson(value));
        return messageOK;
    }

    protected Map<String, Object> resultError(String message) {
        HashMap<String, Object> ret;
        ret = new HashMap<String, Object>();
        ret.put(RESULT_KEY_STATUS, RESULT_STATUS_ERROR);
        ret.put(RESULT_KEY_MESSAGE, message);
        return ret;
    }

    protected void isHostNameNull(String hostName) {
        if (StringUtils.isEmpty(hostName)) {
            throw new AuthException("hostName is null");
        }
    }

}
