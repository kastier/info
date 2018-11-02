package com.xjsoftware.com.info.volobj.enums;

public enum ValidCodeSentStatusEnum {
	OldUser("老用户",-1),
	
	NotSend("未发送",1),
	SentFail("发送失败",2),
	SendSuccess ("发送成功",3),
	AlreadySentInLimitedTime("已发送",4),
	OffLimit("超过限制",5)
	;
	
	ValidCodeSentStatusEnum(String desc, int value) {
		this.desc = desc;
		this.value = value;
	}
	
	private String desc;
	private Integer value;
	
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