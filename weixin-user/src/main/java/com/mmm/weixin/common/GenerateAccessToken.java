package com.mmm.weixin.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmm.weixin.config.redis.RedisClient;
import com.mmm.weixin.dto.AccessTokenDto;

@Component
public class GenerateAccessToken {

	@Value("${weixin_access_token_url}")
	private String accessTokenUrl;
	
	@Autowired
	private RedisClient redisClient;
	
	private static final Logger logger = LoggerFactory.getLogger(GenerateAccessToken.class);
	
	public void generateAccessToken() {
		HttpGet httpGet = new HttpGet(accessTokenUrl);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try{
        	HttpResponse response = httpClient.execute(httpGet);
	        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	            String str = EntityUtils.toString(response.getEntity(), "utf-8");
	            JSONObject jsonObject = JSONObject.parseObject(str);
	            String accessToken = jsonObject.getString("access_token");
	            long time = new Date().getTime();
	            AccessTokenDto token = new AccessTokenDto();
	            token.setAccessToken(accessToken);
	            token.setTime(String.valueOf(time));
	            redisClient.set("access_token_map",JSON.toJSONString(token));
	        }
        }catch(Exception e) {
        	logger.error("weixin-user GenerateAccessToken:"+e);
        }
	}
}
