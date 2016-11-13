package com.zeus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zeus.common.requestEnum.MemoryRequestTypeEnum;
import com.zeus.common.utils.DateUtil;
import com.zeus.dto.DiskInfoDto;
import com.zeus.dto.DtoBeanFactory;
import com.zeus.dto.MemoryInfoDto;
import com.zeus.model.HistoryUint;
import com.zeus.service.MemoryMonitorService;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内存监控信息
 */
@Service
public class MemoryMonitorServiceImpl extends BaseServiceImpl implements MemoryMonitorService {

    @Autowired
    private ZabbixApi zabbixApi;

    @Override

    public List<MemoryInfoDto> getDiskMonitorInfo(Map<String, String> itemIdMap, String auth, MemoryRequestTypeEnum requestTypeEnum) {

        List<MemoryInfoDto> memoryInfoDtoList = new ArrayList<>();
        switch (requestTypeEnum) {
            case MEMORY_ALL:
                memoryInfoDtoList = getAll(itemIdMap, auth);
                break;
            case MEMORY_USED_SPACE:
                memoryInfoDtoList = getMemoryUsedSpace(itemIdMap, auth);
                break;
            case MEMORY_BUFFER_SPACE:
                memoryInfoDtoList = getMemoryBufferpace(itemIdMap, auth);
                break;
            case MEMORY_CACHED_SPACE:
                memoryInfoDtoList = getMemoryCachedSpace(itemIdMap, auth);
                break;
            case MEMORY_FREE_SPACE:
                memoryInfoDtoList = getMemoryFreeSpace(itemIdMap, auth);
                break;
            case MEMORY_TOTAL_SPACE:
                memoryInfoDtoList = getMemoryTotalSpace(itemIdMap, auth);
                break;
            default:
                break;
        }
        return memoryInfoDtoList;
    }

    private List<MemoryInfoDto> getAll(Map<String, String> itemIdMap, String auth) {
        List<MemoryInfoDto> memoryInfoDtoList = new ArrayList<>();

        Map<Long, MemoryInfoDto> memoryUsedSpaceMap = convertToMapFromList(getMemoryUsedSpace(itemIdMap, auth));
        Map<Long, MemoryInfoDto> memoryBufferSpaceMap = convertToMapFromList(getMemoryBufferpace(itemIdMap, auth));
        Map<Long, MemoryInfoDto> memoryCachedSpaceMap = convertToMapFromList(getMemoryCachedSpace(itemIdMap, auth));
        Map<Long, MemoryInfoDto> memoryFreeSpaceMap = convertToMapFromList(getMemoryFreeSpace(itemIdMap, auth));
        Map<Long, MemoryInfoDto> memoryTotalSpaceMap = convertToMapFromList(getMemoryTotalSpace(itemIdMap, auth));

        for (Map.Entry<Long, MemoryInfoDto> entry : memoryUsedSpaceMap.entrySet()) {
            MemoryInfoDto memoryInfoDto = new MemoryInfoDto();
            memoryInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(entry.getValue().getClock(), DateUtil.DATE_WITH_SECOND));
            memoryInfoDto.setMemoryBufferSpace(memoryBufferSpaceMap.get(entry.getKey()).getMemoryBufferSpace());
            memoryInfoDto.setMemoryCachedSpace(memoryCachedSpaceMap.get(entry.getKey()).getMemoryCachedSpace());
            memoryInfoDto.setMemoryFreeSpace(memoryFreeSpaceMap.get(entry.getKey()).getMemoryFreeSpace());
            memoryInfoDto.setMemoryTotalSpace(memoryTotalSpaceMap.get(entry.getKey()).getMemoryTotalSpace());
            memoryInfoDto.setMemoryUsedSpace(entry.getValue().getMemoryUsedSpace());
            memoryInfoDtoList.add(memoryInfoDto);
        }
        return memoryInfoDtoList;
    }

    private List<MemoryInfoDto> getMemoryUsedSpace(Map<String, String> itemIdMap, String auth) {
        List<MemoryInfoDto> memoryInfoList = new ArrayList<>();
        String itemId = itemIdMap.get(MemoryRequestTypeEnum.MEMORY_USED_SPACE.getCode());
        String jsonResult = doRequestCommon(itemId, auth);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            memoryInfoList.add(DtoBeanFactory.convertByMemoryUsedSpace(historyUint));
        }

        return memoryInfoList;
    }

    private List<MemoryInfoDto> getMemoryBufferpace(Map<String, String> itemIdMap, String auth) {
        List<MemoryInfoDto> memoryInfoList = new ArrayList<>();
        String itemId = itemIdMap.get(MemoryRequestTypeEnum.MEMORY_BUFFER_SPACE.getCode());
        String jsonResult = doRequestCommon(itemId, auth);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            memoryInfoList.add(DtoBeanFactory.convertByMemoryBufferSpace(historyUint));
        }

        return memoryInfoList;
    }

    private List<MemoryInfoDto> getMemoryCachedSpace(Map<String, String> itemIdMap, String auth) {
        List<MemoryInfoDto> memoryInfoList = new ArrayList<>();
        String itemId = itemIdMap.get(MemoryRequestTypeEnum.MEMORY_CACHED_SPACE.getCode());
        String jsonResult = doRequestCommon(itemId, auth);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            memoryInfoList.add(DtoBeanFactory.convertByMemoryCachedSpace(historyUint));
        }

        return memoryInfoList;
    }

    private List<MemoryInfoDto> getMemoryFreeSpace(Map<String, String> itemIdMap, String auth) {
        List<MemoryInfoDto> memoryInfoList = new ArrayList<>();
        String itemId = itemIdMap.get(MemoryRequestTypeEnum.MEMORY_FREE_SPACE.getCode());
        String jsonResult = doRequestCommon(itemId, auth);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            memoryInfoList.add(DtoBeanFactory.convertByMemoryFreeSpace(historyUint));
        }

        return memoryInfoList;
    }

    private List<MemoryInfoDto> getMemoryTotalSpace(Map<String, String> itemIdMap, String auth) {
        List<MemoryInfoDto> memoryInfoList = new ArrayList<>();
        String itemId = itemIdMap.get(MemoryRequestTypeEnum.MEMORY_TOTAL_SPACE.getCode());
        String jsonResult = doRequestCommon(itemId, auth);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            memoryInfoList.add(DtoBeanFactory.convertByMemoryTotalSpace(historyUint));
        }

        return memoryInfoList;
    }

    private String doRequestCommon(String itemId, String auth) {
        Request getRequest = RequestBuilder.newBuilder()
                .paramEntry("itemids", itemId).paramEntry("sortfield", "clock").paramEntry("sortorder", "DESC")
                .paramEntry("limit", "10").paramEntry("history", "3").paramEntry("output", "extend").method("history.get")
                .auth(auth).build();

        JSONObject response = zabbixApi.call(getRequest);
        return response.getJSONArray("result").toString();
    }

    private Map<Long, MemoryInfoDto> convertToMapFromList(List<MemoryInfoDto> memoryInfoList) {
        Map<Long, MemoryInfoDto> result = new HashMap<>();
        for (MemoryInfoDto memoryInfoDto : memoryInfoList) {
            result.put(memoryInfoDto.getClock(), memoryInfoDto);
        }
        return result;
    }
}
