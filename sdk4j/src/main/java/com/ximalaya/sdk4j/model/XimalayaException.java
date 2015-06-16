package com.ximalaya.sdk4j.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 调用喜马拉雅OpenAPI出错时的异常类
 * @author will
 */
public class XimalayaException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3790211061675143505L;
	
	private int statusCode = -1;   // HTTP状态码
	private int errorNo;           // 错误码
    private String errorCode;      // 错误编号
    private String errorDesc;      // 错误描述
    private String service;        // 调用出错的服务，用于提交给喜马拉雅开放平台开发人员来诊断错误
    
    public XimalayaException(String msg) {
        super(msg);
    }
    
    public XimalayaException(Exception cause) {
        super(cause);
    }
    
    public XimalayaException(String msg , int statusCode) throws JSONException {
    	super(msg);
    	this.statusCode = statusCode;
    }
    
    public XimalayaException(String msg, JSONObject json, int statusCode) throws JSONException {
        super(msg + "\n { error_no: " + json.getInt("error_no") + ", error_code: \"" + json.getString("error_code")
        	+ "\", error_desc: \"" + json.getString("error_desc") + "\"" + (json.has("service") ? (", service: \"" 
        	+ json.getString("service") + "\"") : "" + "}"));
        this.statusCode = statusCode;
        this.errorNo = json.getInt("error_no");
        this.errorCode = json.getString("error_code");
        this.errorDesc = json.getString("error_desc");
        if(json.has("service")) {
        	this.service = json.getString("service");
        }
    }

    public XimalayaException(String msg, Exception cause) {
        super(msg, cause);
    }

    public XimalayaException(String msg, Exception cause, int statusCode) {
        super(msg, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
    
    public int getErrorNo() {
    	return errorNo;
    }

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}
	
	public String getService() {
		return service;
	}
}
