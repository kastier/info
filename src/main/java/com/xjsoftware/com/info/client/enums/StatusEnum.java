package com.xjsoftware.com.info.client.enums;

import com.xjsoftware.com.info.manager.BaseCodeEnum;

public enum  StatusEnum implements BaseCodeEnum {
	
	INVALID("无效",0),NEW("新用户",1),VALID("有效",2);
	
	private String desc;
	private Integer value;
	
	StatusEnum (String desc, int value) {
		this.desc = desc;
		this.value = value;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
	

}
