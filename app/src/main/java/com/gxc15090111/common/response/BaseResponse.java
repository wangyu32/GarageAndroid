package com.gxc15090111.common.response;

import java.io.Serializable;

/**
 * 微服务返回信息父类
 * @author
 *
 */
public class BaseResponse implements Serializable {
	
	/**
	 * 响应码
	 */
	private String code = Code.SUCCESS;
	
	/**
	 * 响应信息
	 */
	private String message = "";

	public BaseResponse() {
	}

	public BaseResponse(String message) {
		this.message = message;
	}

	public BaseResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public BaseResponse setCode(String code) {
		this.code = code;
		return this;
	}

	public BaseResponse setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
