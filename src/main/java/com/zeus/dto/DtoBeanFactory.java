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
        diskInfoDto.setAvailableSpace(StorageUnitUtil.K2G(historyUintDB.getValue(), 2) + Constants.UNIT_GB);
        return diskInfoDto;
    }

    public static DiskInfoDto convertByDiskUsedSpace(HistoryUint historyUintDB) {
        DiskInfoDto diskInfoDto = new DiskInfoDto();
        diskInfoDto.setClock(historyUintDB.getClock());
        diskInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        diskInfoDto.setUseSpace(StorageUnitUtil.K2G(historyUintDB.getValue(), 2) + Constants.UNIT_GB);
        return diskInfoDto;
    }

    public static DiskInfoDto convertByDiskTotalSpace(HistoryUint historyUintDB) {
        DiskInfoDto diskInfoDto = new DiskInfoDto();
        diskInfoDto.setClock(historyUintDB.getClock());
        diskInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        diskInfoDto.setTotalSpace(StorageUnitUtil.K2G(historyUintDB.getValue(), 2) + Constants.UNIT_GB);
        return diskInfoDto;
    }

    public static DiskInfoDto convertByDiskUsedPercent(HistoryUint historyUintDB) {
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

    public static MemoryInfoDto convertByMemoryUsedSpace(HistoryUint historyUintDB) {
        MemoryInfoDto memoryInfoDto = new MemoryInfoDto();
        memoryInfoDto.setClock(historyUintDB.getClock());
        memoryInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        memoryInfoDto.setMemoryUsedSpace(StorageUnitUtil.K2M(historyUintDB.getValue(), 2) + Constants.UNIT_MB);
        return memoryInfoDto;
    }

    public static MemoryInfoDto convertByMemoryCachedSpace(HistoryUint historyUintDB) {
        MemoryInfoDto memoryInfoDto = new MemoryInfoDto();
        memoryInfoDto.setClock(historyUintDB.getClock());
        memoryInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        memoryInfoDto.setMemoryCachedSpace(StorageUnitUtil.K2M(historyUintDB.getValue(), 2) + Constants.UNIT_MB);
        return memoryInfoDto;
    }

    public static MemoryInfoDto convertByMemoryFreeSpace(HistoryUint historyUintDB) {
        MemoryInfoDto memoryInfoDto = new MemoryInfoDto();
        memoryInfoDto.setClock(historyUintDB.getClock());
        memoryInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        memoryInfoDto.setMemoryFreeSpace(StorageUnitUtil.K2M(historyUintDB.getValue(), 2) + Constants.UNIT_MB);
        return memoryInfoDto;
    }

    public static MemoryInfoDto convertByMemoryTotalSpace(HistoryUint historyUintDB) {
        MemoryInfoDto memoryInfoDto = new MemoryInfoDto();
        memoryInfoDto.setClock(historyUintDB.getClock());
        memoryInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        memoryInfoDto.setMemoryTotalSpace(StorageUnitUtil.K2G(historyUintDB.getValue(), 2) + Constants.UNIT_GB);
        return memoryInfoDto;
    }

    public static MemoryInfoDto convertByMemoryBufferSpace(HistoryUint historyUintDB) {
        MemoryInfoDto memoryInfoDto = new MemoryInfoDto();
        memoryInfoDto.setClock(historyUintDB.getClock());
        memoryInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        memoryInfoDto.setMemoryBufferSpace(StorageUnitUtil.K2M(historyUintDB.getValue(), 2) + Constants.UNIT_MB);
        return memoryInfoDto;
    }

    public static IOInfoDto convertByIOReceiveRate(HistoryUint historyUintDB) {
        IOInfoDto ioInfoDto = new IOInfoDto();
        ioInfoDto.setClock(historyUintDB.getClock());
        ioInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        ioInfoDto.setReceiveRate(historyUintDB.getValue() + Constants.RATE_BLOCK_S);
        return ioInfoDto;
    }

    public static IOInfoDto convertByIOSentRate(HistoryUint historyUintDB) {
        IOInfoDto ioInfoDto = new IOInfoDto();
        ioInfoDto.setClock(historyUintDB.getClock());
        ioInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        ioInfoDto.setSendRate(historyUintDB.getValue() + Constants.RATE_BLOCK_S);
        return ioInfoDto;
    }

    public static NetInfoDto convertByNetInTotalFlow(HistoryUint historyUintDB) {
        NetInfoDto netInfoDto = new NetInfoDto();
        netInfoDto.setClock(historyUintDB.getClock());
        netInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        netInfoDto.setInTotalFlow(String.valueOf(historyUintDB.getValue()));
        return netInfoDto;
    }

    public static NetInfoDto convertByNetOutTotalFlow(HistoryUint historyUintDB) {
        NetInfoDto netInfoDto = new NetInfoDto();
        netInfoDto.setClock(historyUintDB.getClock());
        netInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        netInfoDto.setOutTotalFlow(String.valueOf(historyUintDB.getValue()));
        return netInfoDto;
    }
}
