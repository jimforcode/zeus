package com.zeus.common.utils;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class StorageUnitUtil {

    /**
     * KB -> GB
     */
    public static BigDecimal K2G(Long storageValue, int scale) {
        return new BigDecimal(storageValue).divide(new BigDecimal(1024 * 1024 * 1024)).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * KB -> MB
     */
    public static BigDecimal K2M(Long storageValue, int scale) {
        return new BigDecimal(storageValue).divide(new BigDecimal(1024 * 1024)).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

}
