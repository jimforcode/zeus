package com.zeus.dto;

import com.zeus.common.utils.DateUtil;
import com.zeus.common.utils.StorageUnitUtil;
import com.zeus.model.HistoryUint;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class DtoBeanFactory {

    public static DiskInfoDto convertByDiskAvailSpace(HistoryUint historyUintDB) {
        DiskInfoDto diskInfoDto = new DiskInfoDto();
        diskInfoDto.setDate(DateUtil.convertToDateFromTimeSeconds(historyUintDB.getClock(), DateUtil.DATE_WITH_SECOND));
        diskInfoDto.setAvailableSpace(StorageUnitUtil.K2G(historyUintDB.getValue(), 2) + "G");
        return diskInfoDto;
    }

    public static DiskInfoDto convertByDiskUsedSpace(HistoryUint historyUintDB) {
        return convertByDiskAvailSpace(historyUintDB);
    }

}
