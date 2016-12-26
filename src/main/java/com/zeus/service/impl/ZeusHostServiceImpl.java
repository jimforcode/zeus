package com.zeus.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.dto.LocalHostDto;
import com.zeus.dto.LocalHostResDto;
import com.zeus.dto.ZeusHostDto;
import com.zeus.dto.ZeusHostGetReqDto;
import com.zeus.dto.ZeusHostGetResDto;
import com.zeus.dto.ZeusHostSaveReqDto;
import com.zeus.dto.ZeusHostSaveResDto;
import com.zeus.service.ZeusHostService;


@Service
public class ZeusHostServiceImpl implements ZeusHostService {

	@Autowired
	ZeusHostMapper zeusHostMapper;

	/**
	 * 获取局域网主机列表
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LocalHostResDto getLocalHost() {
		
		LocalHostResDto localHostResDto = new LocalHostResDto();
		List<LocalHostDto> localHostList = new ArrayList<LocalHostDto>();
	        try {
            Process process1 = Runtime.getRuntime().exec("net view");
            InputStreamReader inputStr1 = new InputStreamReader(
                    process1.getInputStream(), "GBK");
            BufferedReader br1 = new BufferedReader(inputStr1);
            String temp = "";
            br1.readLine();
            List<String> nameList = new ArrayList<String>();
            while ((temp = br1.readLine()) != null) {
            	if(temp.startsWith("\\")){
            		String name = temp.substring(2);
            		nameList.add(name.split(" ")[0]);
            	}
            }
            for (String s : nameList) {
            	Process process2 = Runtime.getRuntime().exec("ping " + s + " -n 1");
            	InputStreamReader inputStr2 = new InputStreamReader(
            			process2.getInputStream(), "GBK");
            	BufferedReader br2 = new BufferedReader(inputStr2);
            	br2.readLine();
            	while ((temp = br2.readLine()) != null) {
            		if (!(temp == null || temp.length() == 0)) {
            			String x;
            			InetAddress add=null;
            			String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";  
            		    Pattern pattern = Pattern.compile(regex);  
            		    Matcher matcher = pattern.matcher(temp.split(" ")[0]);  
            			if(matcher.matches()) {
            				LocalHostDto localHostDto = new LocalHostDto();
            	            localHostDto.setIp(temp.split(" ")[0]);
            	            localHostList.add(localHostDto);
                			break;
            			}
            		}
            	}
            	process2.destroy();
                br2.close();
                inputStr2.close();
            }
            process1.destroy();
            br1.close();
            inputStr1.close();
        } catch (Exception e) {
            System.out.println("可能是网络不可用。");
        }
        localHostResDto.setLocalHostList(localHostList);
		return localHostResDto;
	}
	
	/**
	 * 保存主机信息
	 * @param zeusHostSaveReqDto
	 * @return
	 */
	public ZeusHostSaveResDto saveZeusHost(ZeusHostSaveReqDto zeusHostSaveReqDto) {
		ZeusHostSaveResDto zeusHostSaveResDto = new ZeusHostSaveResDto();
		ZeusHost zeusHost = null;
		for(ZeusHostDto zeusHostDto : zeusHostSaveReqDto.getZeusHostList()) {
			zeusHost = this.getZeusHost(zeusHostDto.getHostId());
			zeusHost.setAgencyNo(zeusHostDto.getAgencyNo());
			zeusHost.setAgencyName(zeusHostDto.getAgencyName());
			zeusHost.setHostType(zeusHostDto.getHostType());
			zeusHost.setHsotIp(zeusHostDto.getHsotIp());
			zeusHost.setHostName(zeusHostDto.getHostName());
			zeusHost.setCreatedUser(zeusHostDto.getCreatedUser());
			zeusHost.setModifiedUser(zeusHostDto.getModifiedUser());
			this.saveOrUpdateZeusHost(zeusHost);
		}
		return zeusHostSaveResDto;
	}
	
	/**
	 * 根据主键查询主机信息
	 * @param hostId
	 * @return
	 */
	private ZeusHost getZeusHost(Integer hostId){
		return zeusHostMapper.selectByPrimaryKey();
	}
	
	/**
	 * 保存或更新主机信息
	 * @param zeusHost
	 * @return
	 */
	private int saveOrUpdateZeusHost(ZeusHost zeusHost) {
		zeusHost.setModifiedDate(new Date());
		if(null == zeusHost.getHostId()){
			zeusHost.setIsDelete((short) 0);
			zeusHost.setCreatedDate(new Date());
			int hostId = zeusHostMapper.insertSelective(zeusHost);
			return hostId;
		} else{
			zeusHostMapper.updateByPrimaryKeySelective(zeusHost);
			return zeusHost.getHostId();
		}
	}
	
	/**
	 * 获取主机信息
	 * @param zeusHostGetReqDto
	 * @return
	 */
	public ZeusHostGetResDto getZeusHost(ZeusHostGetReqDto zeusHostGetReqDto) {
		ZeusHostGetResDto zeusHostGetResDto = new ZeusHostGetResDto();
		Map<String, Object> map = new HashMap<String, Object>();
		if (zeusHostGetReqDto != null) {
			map.put("agencyNo", zeusHostGetReqDto.getAgencyNo());
			map.put("hostType", zeusHostGetReqDto.getHostType());
			map.put("hsotIp", zeusHostGetReqDto.getHsotIp());
		}
		List<ZeusHost> zeusHostList = zeusHostMapper.getZeusHostList(map);
		List<ZeusHostDto> zeusHostDtoList = new ArrayList<ZeusHostDto>();
		for (ZeusHost zeusHost : zeusHostList) {
			ZeusHostDto zeusHostDto = new ZeusHostDto();
			BeanUtils.copyProperties(zeusHost, zeusHostDto);
			zeusHostDtoList.add(zeusHostDto);
		}
		zeusHostGetResDto.setZeusHostList(zeusHostDtoList);
		return zeusHostGetResDto;
	}

}
