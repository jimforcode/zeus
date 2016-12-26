package com.zeus.dto;


/**
 * 保存主机信息请求Dto
 */
public class ZeusHostSaveResDto {
	
	/**
	 * 返回结果
	 */
	private String resultCode;
	
	/**
	 * 返回结果信息
	 */
	private String resultMessage;
	
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
}
