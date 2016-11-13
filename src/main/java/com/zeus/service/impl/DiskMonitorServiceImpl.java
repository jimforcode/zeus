package com.zeus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zeus.common.requestEnum.CpuRequestTypeEnum;
import com.zeus.common.requestEnum.DiskRequestTypeEnum;
import com.zeus.common.utils.DateUtil;
import com.zeus.dto.CpuInfoDto;
import com.zeus.dto.DiskInfoDto;
import com.zeus.dto.DtoBeanFactory;
import com.zeus.model.HistoryUint;
import com.zeus.service.DiskMonitorService;
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
 * 磁盘监控信息
 */
@Service
public class DiskMonitorServiceImpl extends BaseServiceImpl implements DiskMonitorService {

    @Autowired
    private ZabbixApi zabbixApi;

    @Override
    public List<DiskInfoDto> getDiskMonitorInfo(Map<String, String> itemIdMap, String auth, DiskRequestTypeEnum requestTypeEnum) {

        List<DiskInfoDto> diskInfoDtoList = new ArrayList<>();
        switch (requestTypeEnum) {
            case DISK_ALL:
                diskInfoDtoList = getAll(itemIdMap, auth);
                break;
            case DISK_AVAILABLE_SPACE:
                diskInfoDtoList = getDiskAvailableSpace(itemIdMap, auth);
                break;
            case DISK_USED_SPACE:
                diskInfoDtoList = getDiskUsedSpace(itemIdMap, auth);
                break;
            case DISK_TOTAL_SPACE:
                diskInfoDtoList = getDiskTotalSpace(itemIdMap, auth);
                break;
            case DISK_USE_PERCENT:
                diskInfoDtoList = getDiskUsePercent(itemIdMap, auth);
                break;
            default:
                break;
        }
        return diskInfoDtoList;
    }

    private List<DiskInfoDto> getAll(Map<String, String> itemIdMap, String auth) {
        List<DiskInfoDto> diskInfoList = new ArrayList<>();

        Map<Long, DiskInfoDto> diskUsePercentMap = convertToMapFromList(getDiskUsePercent(itemIdMap, auth));
        Map<Long, DiskInfoDto> diskTotalSoaceMap = convertToMapFromList(getDiskTotalSpace(itemIdMap, auth));
        Map<Long, DiskInfoDto> diskAvailableSpace = convertToMapFromList(getDiskAvailableSpace(itemIdMap, auth));
        Map<Long, DiskInfoDto> diskUsedSpaceMap = convertToMapFromList(getDiskUsedSpace(itemIdMap, auth));

        for (Map.Entry<Long, DiskInfoDto> entry : diskUsedSpaceMap.entrySet()) {
            DiskInfoDto diskInfoDto = new DiskInfoDto();
            diskInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(entry.getValue().getClock(), DateUtil.DATE_WITH_SECOND));
            diskInfoDto.setUsePercent(diskUsePercentMap.get(entry.getKey()).getUsePercent());
            diskInfoDto.setTotalSpace(diskTotalSoaceMap.get(entry.getKey()).getTotalSpace());
            diskInfoDto.setAvailableSpace(diskAvailableSpace.get(entry.getKey()).getAvailableSpace());
            diskInfoDto.setUseSpace(entry.getValue().getUseSpace());
            diskInfoList.add(diskInfoDto);
        }
        return diskInfoList;
    }

    private List<DiskInfoDto> getDiskUsePercent(Map<String, String> itemIdMap, String auth) {
        List<DiskInfoDto> diskInfoList = new ArrayList<>();
        String itemId = itemIdMap.get(DiskRequestTypeEnum.DISK_USE_PERCENT.getCode());
        String jsonResult = doRequestCommon(itemId, auth);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            diskInfoList.add(DtoBeanFactory.convertByDiskUsedPercent(historyUint));
        }

        return diskInfoList;
    }

    private List<DiskInfoDto> getDiskTotalSpace(Map<String, String> itemIdMap, String auth) {
        List<DiskInfoDto> diskInfoList = new ArrayList<>();
        String itemId = itemIdMap.get(DiskRequestTypeEnum.DISK_TOTAL_SPACE.getCode());
        String jsonResult = doRequestCommon(itemId, auth);

        List<HistoryUint> historyUints;
        historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            diskInfoList.add(DtoBeanFactory.convertByDiskTotalSpace(historyUint));
        }

        return diskInfoList;
    }

    private List<DiskInfoDto> getDiskAvailableSpace(Map<String, String> itemIdMap, String auth) {
        List<DiskInfoDto> diskInfoList = new ArrayList<>();
        String itemId = itemIdMap.get(DiskRequestTypeEnum.DISK_AVAILABLE_SPACE.getCode());
        String jsonResult = doRequestCommon(itemId, auth);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            diskInfoList.add(DtoBeanFactory.convertByDiskAvailableSpace(historyUint));
        }

        return diskInfoList;
    }

    private List<DiskInfoDto> getDiskUsedSpace(Map<String, String> itemIdMap, String auth) {
        List<DiskInfoDto> diskInfoList = new ArrayList<>();
        String itemId = itemIdMap.get(DiskRequestTypeEnum.DISK_USED_SPACE.getCode());
        String jsonResult = doRequestCommon(itemId, auth);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            diskInfoList.add(DtoBeanFactory.convertByDiskAvailableSpace(historyUint));
        }

        return diskInfoList;
    }

    private String doRequestCommon(String itemId, String auth) {
        Request getRequest = RequestBuilder.newBuilder()
                .paramEntry("itemids", itemId).paramEntry("sortfield", "clock").paramEntry("sortorder", "DESC")
                .paramEntry("limit", "10").paramEntry("history", "3").paramEntry("output", "extend").method("history.get")
                .auth(auth).build();

        JSONObject response = zabbixApi.call(getRequest);
        return response.getJSONArray("result").toString();
    }

    private Map<Long, DiskInfoDto> convertToMapFromList(List<DiskInfoDto> diskInfoList) {
        Map<Long, DiskInfoDto> result = new HashMap<>();
        for (DiskInfoDto diskInfoDto : diskInfoList) {
            result.put(diskInfoDto.getClock(), diskInfoDto);
        }
        return result;
    }

}
