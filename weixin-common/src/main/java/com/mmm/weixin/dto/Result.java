package com.mmm.weixin.dto;

public class Result<T> {

	private String msg;
	private Integer code;
	private T data;
	
	public Result() {
		super();
	}

	public Result(String msg,Integer code,T t){
		this.msg = msg;
		this.code = code;
		this.data = t;
	}
	
	public Result(String msg,Integer code){
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}

