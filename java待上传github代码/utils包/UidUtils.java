package com.utils;

import java.util.UUID;

public class UidUtils {
	public static String getuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
