package com.zeus.dao;

import java.util.List;
import java.util.Map;

import com.zeus.model.ZeusHost;

public interface ZeusHostMapper {
    int deleteByPrimaryKey(Integer hostId);

    int insert(ZeusHost record);

    int insertSelective(ZeusHost record);

    ZeusHost selectByPrimaryKey(Integer hostId);

    int updateByPrimaryKeySelective(ZeusHost record);

    int updateByPrimaryKey(ZeusHost record);

	List<ZeusHost> getZeusHostList(Map<String, Object> map);
}