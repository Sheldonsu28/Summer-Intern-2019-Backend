package com.mmm.weixin.handler;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mmm.weixin.dto.BaseController;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.exception.InvalidTokenException;
import com.mmm.weixin.exception.ServiceException;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
@ResponseBody
public class ExceptionsHandler extends BaseController{

	@Value("${inner_error}")
	private String innerError;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException){
		BindingResult result = methodArgumentNotValidException.getBindingResult();
		String defaultMessage = "";
		if(result.hasErrors()){
            Iterator<ObjectError> iterator = result.getAllErrors().iterator();
            while(iterator.hasNext()){
                  ObjectError next = iterator.next();
                  defaultMessage = next.getDefaultMessage();
                  break;
            }
        }
		return fail(defaultMessage);
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	public Result expiredJwtException(ExpiredJwtException exception){
		return fail(-100,"token过期");
	}
	
	@ExceptionHandler(InvalidTokenException.class)
	public Result invalidTokenException(InvalidTokenException invalidTokenException) {
		return fail(403,"无效token");
	}
	
	@ExceptionHandler(ServiceException.class)
	public Result serviceExceptionHandler(ServiceException serviceException){
		return fail(serviceException.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public Result exceptionHandler(HttpServletRequest request,Exception exception){
		logger.error("",exception);
		return fail(innerError);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public Result runtimeExceptionHandler(RuntimeException e){
		logger.error("",e);
		return fail(innerError);
	}

}