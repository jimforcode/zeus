package com.zeus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zeus.common.requestEnum.CpuRequestTypeEnum;
import com.zeus.common.utils.DateUtil;
import com.zeus.dto.CpuInfoDto;
import com.zeus.dto.DtoBeanFactory;
import com.zeus.model.HistoryUint;
import com.zeus.service.CpuMonitorService;
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
 * CPU监控信息
 */
@Service
public class CpuMonitorServiceImpl extends BaseServiceImpl implements CpuMonitorService {

    @Autowired
    private ZabbixApi zabbixApi;

    @Override
    public List<CpuInfoDto> getCpuMonitorInfo(Map<String, String> itemIdMap, String timeFrom, String timeTill, String auth, CpuRequestTypeEnum requestTypeEnum) {

        List<CpuInfoDto> cpuInfoDtoList = new ArrayList<>();
        switch (requestTypeEnum) {
            case CPU_ALL:
                cpuInfoDtoList = getAll(itemIdMap, auth);
                break;
            case CPU_IDLE_PERCENT:
                cpuInfoDtoList = getCpuIdlePercent(itemIdMap, auth);
                break;
            case CPU_SYSTEM_PERCENT:
                cpuInfoDtoList = getCpuSystemPercent(itemIdMap, auth);
                break;
            case CPU_USER_PERCENT:
                cpuInfoDtoList = getCpuUserPercent(itemIdMap, auth);
                break;
            case CPU_PROCESSOR_LOAD_PERCENT:
                cpuInfoDtoList = getCpuProcessorLoadPercent(itemIdMap, auth);
                break;
            default:
                break;
        }
        return cpuInfoDtoList;
    }

    List<CpuInfoDto> getAll(Map<String, String> itemIdMap, String auth) {
        List<CpuInfoDto> cpuInfoDtoList = new ArrayList<>();

        Map<Long, CpuInfoDto> processorLoadPercentMap = convertToMapFromList(getCpuProcessorLoadPercent(itemIdMap, auth));
        Map<Long, CpuInfoDto> userPercentMap = convertToMapFromList(getCpuUserPercent(itemIdMap, auth));
        Map<Long, CpuInfoDto> systemPercentMap = convertToMapFromList(getCpuSystemPercent(itemIdMap, auth));
        Map<Long, CpuInfoDto> idlePercentMap = convertToMapFromList(getCpuIdlePercent(itemIdMap, auth));

        for (Map.Entry<Long, CpuInfoDto> entry : processorLoadPercentMap.entrySet()) {
            CpuInfoDto cpuInfoDto = new CpuInfoDto();
            cpuInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(entry.getValue().getClock(), DateUtil.DATE_WITH_SECOND));
            cpuInfoDto.setSystemCpuPercent(systemPercentMap.get(entry.getKey()).getSystemCpuPercent());
            cpuInfoDto.setIdleCpuPercent(idlePercentMap.get(entry.getKey()).getIdleCpuPercent());
            cpuInfoDto.setUserCpuPercent(userPercentMap.get(entry.getKey()).getUserCpuPercent());
            cpuInfoDto.setProcessorLoadPercent(entry.getValue().getProcessorLoadPercent());
            cpuInfoDtoList.add(cpuInfoDto);
        }
        return cpuInfoDtoList;
    }

    private List<CpuInfoDto> getCpuProcessorLoadPercent(Map<String, String> itemIdMap, String auth) {
        List<CpuInfoDto> cpuInfoDtoList = new ArrayList<>();
        String itemId = itemIdMap.get(CpuRequestTypeEnum.CPU_PROCESSOR_LOAD_PERCENT.getCode());
        String jsonResult = doRequestCommon(itemId, auth);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            cpuInfoDtoList.add(DtoBeanFactory.convertByCpuProcessorLoadPercent(historyUint));
        }
        return cpuInfoDtoList;
    }

    private List<CpuInfoDto> getCpuUserPercent(Map<String, String> itemIdMap, String auth) {
        List<CpuInfoDto> cpuInfoDtoList = new ArrayList<>();
        String itemId = itemIdMap.get(CpuRequestTypeEnum.CPU_USER_PERCENT.getCode());
        String jsonResult = doRequestCommon(itemId, auth);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            cpuInfoDtoList.add(DtoBeanFactory.convertByCpuUserPercent(historyUint));
        }
        return cpuInfoDtoList;
    }

    private List<CpuInfoDto> getCpuSystemPercent(Map<String, String> itemIdMap, String auth) {
        List<CpuInfoDto> cpuInfoDtoList = new ArrayList<>();
        String itemId = itemIdMap.get(CpuRequestTypeEnum.CPU_SYSTEM_PERCENT.getCode());
        String jsonResult = doRequestCommon(itemId, auth);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            cpuInfoDtoList.add(DtoBeanFactory.convertByCpuSystemPercent(historyUint));
        }
        return cpuInfoDtoList;
    }

    private List<CpuInfoDto> getCpuIdlePercent(Map<String, String> itemIdMap, String auth) {
        List<CpuInfoDto> cpuInfoDtoList = new ArrayList<>();
        String itemId = itemIdMap.get(CpuRequestTypeEnum.CPU_IDLE_PERCENT.getCode());
        String jsonResult = doRequestCommon(itemId, auth);

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        for (HistoryUint historyUint : historyUints) {
            cpuInfoDtoList.add(DtoBeanFactory.convertByCpuIdlePercent(historyUint));
        }
        return cpuInfoDtoList;
    }

    private String doRequestCommon(String itemId, String auth) {
        Request getRequest = RequestBuilder.newBuilder()
                .paramEntry("itemids", itemId).paramEntry("sortfield", "clock").paramEntry("sortorder", "DESC")
                .paramEntry("limit", "10").paramEntry("history", "3").paramEntry("output", "extend").method("history.get")
                .auth(auth).build();

        JSONObject response = zabbixApi.call(getRequest);
        return response.getJSONArray("result").toString();
    }

    private Map<Long, CpuInfoDto> convertToMapFromList(List<CpuInfoDto> cpuInfoDtoist) {
        Map<Long, CpuInfoDto> result = new HashMap<>();
        for (CpuInfoDto cpuInfoDto : cpuInfoDtoist) {
            result.put(cpuInfoDto.getClock(), cpuInfoDto);
        }
        return result;
    }
}
