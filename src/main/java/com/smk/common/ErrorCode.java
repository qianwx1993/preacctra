package com.smk.common;

public enum ErrorCode {
	SUCCESS("0000", "success"),
	PARAM_WRONG("0001","param wrong"),
	PHOTO_IS_NOT_IN_FAST_DFS("0101", "photo is not in fastDfs"),
	FAST_DFS_ERROR("0102", "fastDfs server error"),
	PHOTO_IS_ERROR("0103", "photo error"),

	SYSTEM_ERROR("9999", "system error");

	public String code;

	public String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private ErrorCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
