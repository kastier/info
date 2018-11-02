package com.xjsoftware.com.info.volobj;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.xjsoftware.com.info.volobj.enums.ValidCodeSentStatusEnum;

import java.time.LocalDateTime;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;


public class ValidCodeInfo {
	private String PhoneNumber;
	private String ValidCode;
	private int SentTimes;
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime CacheExpireTimes;
	private int requestTimes;
	
	public ValidCodeSentStatusEnum getStatus() {
		return status;
	}
	
	public void setStatus(ValidCodeSentStatusEnum status) {
		this.status = status;
	}
	
	private ValidCodeSentStatusEnum status;
	
	public int getRequestTimes() {
		return requestTimes;
	}
	
	public void setRequestTimes(int requestTimes) {
		this.requestTimes = requestTimes;
	}
	
	
	
	public LocalDateTime getCacheExpireTimes() {
		return CacheExpireTimes;
	}
	
	public void setCacheExpireTimes(LocalDateTime cacheExpireTimes) {
		CacheExpireTimes = cacheExpireTimes;
	}
	
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	
	public String getValidCode() {
		return ValidCode;
	}
	
	public void setValidCode(String validCode) {
		ValidCode = validCode;
	}
	
	public int getSentTimes() {
		return SentTimes;
	}
	
	public void setSentTimes(int sentTimes) {
		SentTimes = sentTimes;
	}
}