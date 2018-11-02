package com.xjsoftware.com.info.manager;

public class CacheKeys {
	
	private final static String CACHEKEYPREFIX="info_sys";
	
	public static  String getPhoneNumberValidCodeCacheKey(String phoneNumber)
	{
		return String.format ("%s:telcode:%s",CACHEKEYPREFIX, phoneNumber);
	}
	
	public static String getNewRegisterInfoSetKey()
	{
		return String.format ("%s:new_invited_set",CACHEKEYPREFIX);
	}
	
	public static String getDeviceIdSetKey()
	{
		return String.format ("%s:deviceid_set",CACHEKEYPREFIX);
	}
	
	public static String getUserGotCoinSetKey()
	{
		return String.format ("%s:user_got_coin_set",CACHEKEYPREFIX);
	}
	
	public static String getInvitedSuccessRecordSetKey()
	{
		return String.format ("%s:invited_success_set",CACHEKEYPREFIX);
	}
	
	public static String getAddressSetKey(String ip) {
		return String.format ("%s:ip_filter_set:%s",CACHEKEYPREFIX,ip);
	}
}
