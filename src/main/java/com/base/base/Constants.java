package com.base.base;

import java.util.Arrays;
import java.util.List;

public class Constants {
	public static final int ACTIVE = 1;
	public static final int PENDING_APPROVAL = 2;
	public static final int INACTIVE = 3;
	public static String HEADER_DEVICE_ID="device_id";
	public static String HEADER_DEVICE_UID="device_uid";
	public static String HEADER_DEVICE_OPERATING_SYSTEM="device_operating_system";
	public static String HEADER_DEVICE_OPERATING_NAME="device_operating_name";
	public static String HEADER_DEVICE_APP_VERSION="device_app_version";
	public static String HEADER_DEVICE_BRAND="device_brand";
	public static String HEADER_DEVICE_MODEL="device_model";
	public static String HEADER_DEVICE_OS_VERSION="device_os_version";

	public static String HEADER_AUTH_SESSION_ID="auth_session_id";
	public static String HEADER_AUTH_SESSION_TOKEN="auth_session_token";
	
	public static List<String> allowedHeader=Arrays.asList(HEADER_DEVICE_ID,HEADER_DEVICE_UID,HEADER_DEVICE_OPERATING_SYSTEM,
			HEADER_DEVICE_OPERATING_NAME,HEADER_DEVICE_APP_VERSION,HEADER_DEVICE_BRAND,HEADER_DEVICE_MODEL,HEADER_DEVICE_OS_VERSION,
			HEADER_AUTH_SESSION_ID,HEADER_AUTH_SESSION_TOKEN);

}
