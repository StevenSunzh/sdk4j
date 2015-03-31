package com.ximalaya.sdk4j;

import java.io.Serializable;

import com.ximalaya.sdk4j.http.HttpClient;
import com.ximalaya.sdk4j.model.HttpParameter;
import com.ximalaya.sdk4j.util.CrypterUtil;
import com.ximalaya.sdk4j.util.SignatureUtil;
import com.ximalaya.sdk4j.util.XimalayaConfig;

public class Ximalaya implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2462822863708818946L;
	
	/*
	 * 全局配置
	 */
	protected static final String BASE_URL = XimalayaConfig.getValue("ximalaya.openapi.baseURL");
	protected static final String APP_KEY = XimalayaConfig.getValue("ximalaya.openapi.appKey");
	protected static final String APP_SECRET = XimalayaConfig.getValue("ximalaya.openapi.appSecret");
	protected static final String SERVER_AUTHENTICATE_STATIC_KEY = XimalayaConfig.getValue("ximalaya.openapi.serverAuthenticateStaticKey");
	
	protected static final HttpClient client = new HttpClient();
	protected static final HttpParameter[] DEFAULT_SPECIFIC_PARAMS = new HttpParameter[0];
	
	private static final int SERVER_CLIENT_OS_TYPE = 4;   // 服务端client_os_type参数固定为4
	
	/**
	 * 组装HTTP请求参数
	 * @return
	 */
	public final HttpParameter[] assembleHttpParams() {
		HttpParameter[] resultParams = null;
		HttpParameter[] commonParams = assembleCommonParams();
		HttpParameter[] specificParams = assembleSpecificParams();
		resultParams = new HttpParameter[commonParams.length + specificParams.length + 1];
		if(specificParams.length > 0) {
			System.arraycopy(commonParams, 0, resultParams, 0, commonParams.length);
			System.arraycopy(specificParams, 0, resultParams, commonParams.length, specificParams.length);
		}
		else {
			System.arraycopy(commonParams, 0, resultParams, 0, commonParams.length);
		}
		String seed = APP_SECRET + SERVER_AUTHENTICATE_STATIC_KEY;
		String sig = SignatureUtil.caculateSignature(resultParams, seed);
		resultParams[resultParams.length - 1] = new HttpParameter("sig", sig);
		return resultParams;
	}
	
	/**
	 * 组装通用HTTP请求参数
	 * 
	 * @return
	 */
	public final HttpParameter[] assembleCommonParams() {
		HttpParameter[] commonParams = new HttpParameter[4];
		commonParams[0] = new HttpParameter("app_key", APP_KEY);
		commonParams[1] = new HttpParameter("client_os_type", SERVER_CLIENT_OS_TYPE);
		commonParams[2] = new HttpParameter("nonce", CrypterUtil.getRandKey());  // nonce是一个随机字符串
		commonParams[3] = new HttpParameter("timestamp", System.currentTimeMillis() / 1000);
		return commonParams;
	}
	
	/**
	 * 组装每个API接口特有的参数，子类可以重写该方法以提供自己的特有参数
	 * 
	 * @return
	 */
	protected HttpParameter[] assembleSpecificParams() {
		return DEFAULT_SPECIFIC_PARAMS;
	}
	
}
