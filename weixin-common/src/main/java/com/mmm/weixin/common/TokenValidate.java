package com.mmm.weixin.common;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.mmm.weixin.config.redis.RedisClient;
import com.mmm.weixin.exception.InvalidTokenException;
import com.mmm.weixin.exception.ServiceException;
import com.mmm.weixin.utils.base.StringUtils;
import com.mmm.weixin.utils.base.ValidateHelper;
import com.mmm.weixin.utils.jwt.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class TokenValidate {

	@Resource
	private JwtUtil jwtUtil;
	
	@Autowired
	private RedisClient redisClient;
	
	public Integer validateToken(String token) {
		if(ValidateHelper.isEmptyString(token)){
			throw new ServiceException("token不能为空");
		}
		Claims parseJWT = null;
		try {
			parseJWT = jwtUtil.parseJWT(token);
		}catch(MalformedJwtException e) {
			throw new InvalidTokenException();
		}
		Integer uid = JSON.parseObject(parseJWT.getSubject()).getIntValue("userId");
		//获取redis中对应用户的上次token签发时间
		String result = redisClient.get(String.valueOf(uid));
		if(ValidateHelper.isEmpty(result)) {
			throw new ExpiredJwtException(null, parseJWT, "token过期");
		}
		Long redisIssueTime = Long.parseLong(result);
		Long issueTime = JSON.parseObject(parseJWT.getSubject()).getLongValue("issueTime");
		if(redisIssueTime.longValue()!=issueTime.longValue()) {
			throw new ExpiredJwtException(null, parseJWT, "token过期");
		}
		return uid;
	}
}
