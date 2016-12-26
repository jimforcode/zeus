package com.zeus.service;

import com.zeus.dto.LocalHostResDto;
import com.zeus.dto.ZeusHostGetReqDto;
import com.zeus.dto.ZeusHostGetResDto;
import com.zeus.dto.ZeusHostSaveReqDto;
import com.zeus.dto.ZeusHostSaveResDto;


public interface ZeusHostService {
	
	/**
	 * 获取局域网主机列表
	 * @return
	 */
	public LocalHostResDto getLocalHost();
  
	/**
	 * 保存主机信息
	 * @param zeusHostSaveReqDto
	 * @return
	 */
	public ZeusHostSaveResDto saveZeusHost(ZeusHostSaveReqDto zeusHostSaveReqDto);
	
	/**
	 * 获取主机信息
	 * @param zeusHostGetReqDto
	 * @return
	 */
	public ZeusHostGetResDto getZeusHost(ZeusHostGetReqDto zeusHostGetReqDto);
 
}
