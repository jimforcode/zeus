package com.zeus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zeus.common.requestEnum.IORequestTypeEnum;
import com.zeus.common.requestEnum.MemoryRequestTypeEnum;
import com.zeus.common.utils.DateUtil;
import com.zeus.dto.DtoBeanFactory;
import com.zeus.dto.IOInfoDto;
import com.zeus.model.HistoryUint;
import com.zeus.service.IOMonitorService;
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
public class IOMonitorServiceImpl extends BaseServiceImpl implements IOMonitorService {

    @Autowired
    private ZabbixApi zabbixApi;

    @Override

    public List<IOInfoDto> getIOMonitorInfo(Map<String, String> itemIdMap, String auth, IORequestTypeEnum requestTypeEnum, Integer limit) {

        List<IOInfoDto> ioInfoDtoList = new ArrayList<>();
        switch (requestTypeEnum) {
            case IO_ALL:
                ioInfoDtoList = getAll(itemIdMap, auth, limit);
                break;
            case IO_RECEIVE_RATE:
                ioInfoDtoList = getIOReceiveRate(itemIdMap, auth, limit);
                break;
            case IO_SENT_RATE:
                ioInfoDtoList = getIOSentRate(itemIdMap, auth, limit);
                break;
            default:
                break;
        }
        return ioInfoDtoList;
    }

    private List<IOInfoDto> getAll(Map<String, String> itemIdMap, String auth, Integer limit) {
        List<IOInfoDto> ioInfoDtoList = new ArrayList<>();

        Map<Long, IOInfoDto> ioReceiveRateMap = convertToMapFromList(getIOReceiveRate(itemIdMap, auth, limit));
        Map<Long, IOInfoDto> ioSentRateMap = convertToMapFromList(getIOSentRate(itemIdMap, auth, limit));

        for (Map.Entry<Long, IOInfoDto> entry : ioReceiveRateMap.entrySet()) {
            IOInfoDto ioInfoDto = new IOInfoDto();
            ioInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(entry.getValue().getClock(), DateUtil.DATE_WITH_SECOND));
            ioInfoDto.setReceiveRate(ioReceiveRateMap.get(entry.getKey()).getReceiveRate());
            ioInfoDto.setSendRate(ioSentRateMap.get(entry.getKey()).getSendRate());
            ioInfoDtoList.add(ioInfoDto);
        }
        return ioInfoDtoList;
    }

    private List<IOInfoDto> getIOReceiveRate(Map<String, String> itemIdMap, String auth, Integer limit) {
        List<IOInfoDto> ioInfoDtoList = new ArrayList<>();
        String itemId = itemIdMap.get(IORequestTypeEnum.IO_RECEIVE_RATE.getCode());
        String jsonResult = doRequestCommon(itemId, auth, limit);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            ioInfoDtoList.add(DtoBeanFactory.convertByIOReceiveRate(historyUint));
        }

        return ioInfoDtoList;
    }

    private List<IOInfoDto> getIOSentRate(Map<String, String> itemIdMap, String auth, Integer limit) {
        List<IOInfoDto> ioInfoDtoList = new ArrayList<>();
        String itemId = itemIdMap.get(IORequestTypeEnum.IO_SENT_RATE.getCode());
        String jsonResult = doRequestCommon(itemId, auth, limit);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            ioInfoDtoList.add(DtoBeanFactory.convertByIOSentRate(historyUint));
        }

        return ioInfoDtoList;
    }

    private String doRequestCommon(String itemId, String auth, Integer limit) {
        Request getRequest = RequestBuilder.newBuilder()
                .paramEntry("itemids", itemId).paramEntry("sortfield", "clock").paramEntry("sortorder", "DESC")
                .paramEntry("limit", limit).paramEntry("history", "3").paramEntry("output", "extend").method("history.get")
                .auth(auth).build();

        JSONObject response = zabbixApi.call(getRequest);
        return response.getJSONArray("result").toString();
    }

    private Map<Long, IOInfoDto> convertToMapFromList(List<IOInfoDto> ioInfoList) {
        Map<Long, IOInfoDto> result = new HashMap<>();
        for (IOInfoDto ioInfoDto : ioInfoList) {
            result.put(ioInfoDto.getClock(), ioInfoDto);
        }
        return result;
    }
}
