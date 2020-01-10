package com.mmm.weixin.common;

import com.mmm.weixin.dto.Result;
import com.mmm.weixin.exception.ServiceException;
import com.mmm.weixin.exception.WxLoginException;

public class ResultValidate {

	public static void validateResult(Result result) {
		Integer code = result.getCode();
		if(code==-200){
			throw new ServiceException(result.getMsg());
		}
		else if(code==-101) {
			throw new WxLoginException();
		}
	}
}
