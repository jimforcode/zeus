package com.zeus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zeus.common.constant.Constants;
import com.zeus.common.requestEnum.MemoryRequestTypeEnum;
import com.zeus.common.requestEnum.NetRequestTypeEnum;
import com.zeus.common.utils.DateUtil;
import com.zeus.common.utils.StorageUnitUtil;
import com.zeus.dto.DtoBeanFactory;
import com.zeus.dto.MemoryInfoDto;
import com.zeus.dto.NetInfoDto;
import com.zeus.model.HistoryUint;
import com.zeus.service.MemoryMonitorService;
import com.zeus.service.NetMonitorService;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 内存监控信息
 */
@Service
public class NetMonitorServiceImpl extends BaseServiceImpl implements NetMonitorService {

    @Autowired
    private ZabbixApi zabbixApi;

    @Override
    public List<NetInfoDto> getNetMonitorInfo(Map<String, String> itemIdMap, String auth, NetRequestTypeEnum requestTypeEnum, Integer limit) {

        List<NetInfoDto> netInfoDtoList = new ArrayList<>();
        switch (requestTypeEnum) {
            case NET_ALL:
                netInfoDtoList = getAll(itemIdMap, auth, limit);
                break;
            case NET_IN_TOTAL_FLOW:
                netInfoDtoList = getNetInTotalFlow(itemIdMap, auth, limit);
                break;
            case NET_OUT_TOTAL_FLOW:
                netInfoDtoList = getNetOutTotalFlow(itemIdMap, auth, limit);
                break;
            default:
                break;
        }
        return netInfoDtoList;
    }

    private List<NetInfoDto> getAll(Map<String, String> itemIdMap, String auth, Integer limit) {
        List<NetInfoDto> netInfoDtoList = new ArrayList<>();

        Map<Long, NetInfoDto> netInTotalFlowMap = convertToMapFromList(getNetInTotalFlow(itemIdMap, auth, limit));
        Map<Long, NetInfoDto> netOutTotalFlowMap = convertToMapFromList(getNetOutTotalFlow(itemIdMap, auth, limit));

        for (Map.Entry<Long, NetInfoDto> entry : netInTotalFlowMap.entrySet()) {
            NetInfoDto netInfoDto = new NetInfoDto();
            netInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(entry.getValue().getClock(), DateUtil.DATE_WITH_SECOND));
            netInfoDto.setInTotalFlow(netInTotalFlowMap.get(entry.getKey()).getInTotalFlow());
            netInfoDto.setOutTotalFlow(netOutTotalFlowMap.get(entry.getKey()).getOutTotalFlow());
            netInfoDtoList.add(netInfoDto);
        }

        return netInfoDtoList;
    }

    private List<NetInfoDto> getNetInTotalFlow(Map<String, String> itemIdMap, String auth, Integer limit) {
        List<NetInfoDto> netInfoList = new ArrayList<>();
        String[] itemIds = itemIdMap.get(NetRequestTypeEnum.NET_IN_TOTAL_FLOW.getCode()).split(",");
        for (String id : itemIds) {
            String jsonResult = doRequestCommon(id, auth, limit);
            List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
            if (CollectionUtils.isEmpty(historyUints)) {
                continue;
            }
            for (HistoryUint historyUint : historyUints) {
                netInfoList.add(DtoBeanFactory.convertByNetInTotalFlow(historyUint));
            }
        }
        return countByClockGroup(netInfoList, NetRequestTypeEnum.NET_IN_TOTAL_FLOW);
    }

    private List<NetInfoDto> getNetOutTotalFlow(Map<String, String> itemIdMap, String auth, Integer limit) {
        List<NetInfoDto> netInfoList = new ArrayList<>();
        String[] itemIds = itemIdMap.get(NetRequestTypeEnum.NET_OUT_TOTAL_FLOW.getCode()).split(",");
        for (String id : itemIds) {
            String jsonResult = doRequestCommon(id, auth, limit);
            List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
            if (CollectionUtils.isEmpty(historyUints)) {
                continue;
            }
            for (HistoryUint historyUint : historyUints) {
                netInfoList.add(DtoBeanFactory.convertByNetOutTotalFlow(historyUint));
            }
        }
        return countByClockGroup(netInfoList, NetRequestTypeEnum.NET_OUT_TOTAL_FLOW);
    }

    private String doRequestCommon(String itemId, String auth, Integer limit) {
        Request getRequest = RequestBuilder.newBuilder()
                .paramEntry("itemids", Arrays.asList(itemId.split(","))).paramEntry("sortfield", "clock").paramEntry("sortorder", "DESC")
                .paramEntry("limit", limit).paramEntry("history", "3").paramEntry("output", "extend").method("history.get")
                .auth(auth).build();

        JSONObject response = zabbixApi.call(getRequest);
        return response.getJSONArray("result").toString();
    }

    private Map<Long, NetInfoDto> convertToMapFromList(List<NetInfoDto> netInfoList) {
        Map<Long, NetInfoDto> result = new HashMap<>();
        for (NetInfoDto netInfoDto : netInfoList) {
            result.put(netInfoDto.getClock(), netInfoDto);
        }
        return result;
    }

    private List<NetInfoDto> countByClockGroup(List<NetInfoDto> netInfoList, NetRequestTypeEnum requestTypeEnum) {
        // 得到所有的clock
        Set<Long> clockSet = new HashSet<>();
        for (NetInfoDto netInfoDto : netInfoList) {
            clockSet.add(netInfoDto.getClock());
        }

        HashMap<Long, List<NetInfoDto>> map = new HashMap<>();
        for (Long clock : clockSet) {
            List<NetInfoDto> groupList = new ArrayList<>();
            for (NetInfoDto netInfoDto : netInfoList) {
                if (clock.longValue() == netInfoDto.getClock().longValue()) {
                    groupList.add(netInfoDto);
                }
            }
            map.put(clock, groupList);
        }

        List<NetInfoDto> result = new ArrayList<>();
        for (Map.Entry<Long, List<NetInfoDto>> entry : map.entrySet()) {
            NetInfoDto netInfoDto = new NetInfoDto();
            BigDecimal sumAmount = new BigDecimal(0);
            List<NetInfoDto> dtoList = entry.getValue();
            if (requestTypeEnum.equals(NetRequestTypeEnum.NET_IN_TOTAL_FLOW)) {
                for (NetInfoDto dto : dtoList) {
                    sumAmount = sumAmount.add(new BigDecimal(dto.getInTotalFlow()));
                }

                netInfoDto.setInTotalFlow(StorageUnitUtil.K2M(sumAmount.longValue(), 2) + Constants.UNIT_MB);
                netInfoDto.setClock(entry.getKey());
                netInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(dtoList.get(0).getClock(), DateUtil.DATE_WITH_SECOND));
                result.add(netInfoDto);
            } else if (requestTypeEnum.equals(NetRequestTypeEnum.NET_OUT_TOTAL_FLOW)) {
                for (NetInfoDto dto : dtoList) {
                    sumAmount.add(new BigDecimal(dto.getOutTotalFlow()));
                }

                netInfoDto.setOutTotalFlow(StorageUnitUtil.K2M(sumAmount.longValue(), 2) + Constants.UNIT_MB);
                netInfoDto.setClock(entry.getKey());
                netInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(dtoList.get(0).getClock(), DateUtil.DATE_WITH_SECOND));
                result.add(netInfoDto);
            }

        }
        return result;

    }
}
