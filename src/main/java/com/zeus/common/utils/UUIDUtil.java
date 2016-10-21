package com.zeus.common.utils;

import java.util.UUID;

public class UUIDUtil {
	public static String createUUId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
