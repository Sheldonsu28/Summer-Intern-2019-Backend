package com.mmm.weixin.dto;

public class BaseController {

	public <T> Result<T> success(T t){
		return new Result<T>("success",200,t);
	}
	
	public Result<?> success(){
		return success(null);
	}
	
	public Result<?> fail(String msg){
		return new Result(msg,-200);
	}
	
	public Result<?> fail(Integer code,String msg){
		return new Result(msg,code);
	}
}
