package com.zeus.dto;

import com.zeus.common.constant.Constants;
import com.zeus.common.utils.DateUtil;
import com.zeus.common.utils.StorageUnitUtil;
import com.zeus.model.HistoryUint;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class DtoBeanFactory {

    public static DiskInfoDto convertByDiskAvailableSpace(HistoryUint historyUintDB) {
        DiskInfoDto diskInfoDto = new DiskInfoDto();
        diskInfoDto.setClock(historyUintDB.getClock());
        diskInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        diskInfoDto.setAvailableSpace(StorageUnitUtil.K2G(historyUintDB.getValue(), 2) + Constants.UNIT_G);
        return diskInfoDto;
    }

    public static DiskInfoDto convertByDiskUsedSpace(HistoryUint historyUintDB) {
        DiskInfoDto diskInfoDto = new DiskInfoDto();
        diskInfoDto.setClock(historyUintDB.getClock());
        diskInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        diskInfoDto.setUseSpace(StorageUnitUtil.K2G(historyUintDB.getValue(), 2) + Constants.UNIT_G);
        return diskInfoDto;
    }

    public static DiskInfoDto convertByDiskTotalSpace(HistoryUint historyUintDB) {
        DiskInfoDto diskInfoDto = new DiskInfoDto();
        diskInfoDto.setClock(historyUintDB.getClock());
        diskInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        diskInfoDto.setTotalSpace(StorageUnitUtil.K2G(historyUintDB.getValue(), 2) + Constants.UNIT_G);
        return diskInfoDto;
    }

    public static DiskInfoDto convertByDiskUsePercent(HistoryUint historyUintDB) {
        DiskInfoDto diskInfoDto = new DiskInfoDto();
        diskInfoDto.setClock(historyUintDB.getClock());
        diskInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        diskInfoDto.setUsePercent(historyUintDB.getValue() + Constants.HUNDRED_PERCENT);
        return diskInfoDto;
    }

    public static CpuInfoDto convertByCpuIdlePercent(HistoryUint historyUintDB) {
        CpuInfoDto cpuInfoDto = new CpuInfoDto();
        cpuInfoDto.setClock(historyUintDB.getClock());
        cpuInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        cpuInfoDto.setIdleCpuPercent(historyUintDB.getValue() + Constants.HUNDRED_PERCENT);
        return cpuInfoDto;
    }

    public static CpuInfoDto convertByCpuProcessorLoadPercent(HistoryUint historyUintDB) {
        CpuInfoDto cpuInfoDto = new CpuInfoDto();
        cpuInfoDto.setClock(historyUintDB.getClock());
        cpuInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        cpuInfoDto.setProcessorLoadPercent(historyUintDB.getValue() + Constants.HUNDRED_PERCENT);
        return cpuInfoDto;
    }

    public static CpuInfoDto convertByCpuUserPercent(HistoryUint historyUintDB) {
        CpuInfoDto cpuInfoDto = new CpuInfoDto();
        cpuInfoDto.setClock(historyUintDB.getClock());
        cpuInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        cpuInfoDto.setUserCpuPercent(historyUintDB.getValue() + Constants.HUNDRED_PERCENT);
        return cpuInfoDto;
    }

    public static CpuInfoDto convertByCpuSystemPercent(HistoryUint historyUintDB) {
        CpuInfoDto cpuInfoDto = new CpuInfoDto();
        cpuInfoDto.setClock(historyUintDB.getClock());
        cpuInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        cpuInfoDto.setSystemCpuPercent(historyUintDB.getValue() + Constants.HUNDRED_PERCENT);
        return cpuInfoDto;
    }
}
