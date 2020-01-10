package com.mmm.weixin.utils.jwt;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.mmm.weixin.config.redis.RedisClient;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${jwt_expire_milli}")
	private Long expireMilli;
	@Value("${secret_key}")
	private String secretKey;
	@Autowired
	private RedisClient redisClient;
	
	public String createJWT(Integer userId){
		Map<String,Object> subject = new HashMap<String,Object>();
		subject.put("userId", userId);
		SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		subject.put("issueTime", nowMillis);
		redisClient.set(String.valueOf(userId), nowMillis);
		Date now = new Date(nowMillis);
		SecretKey secreKey = generateKey();
		JwtBuilder builder = Jwts.builder().setIssuedAt(now).setSubject(JSON.toJSONString(subject)).signWith(algorithm, secreKey);
		long expMillies = nowMillis+expireMilli;
		Date exp = new Date(expMillies);
		builder.setExpiration(exp);
		return builder.compact();
	}

	public Claims parseJWT(String jwt){
		SecretKey key = generateKey();
		Claims claims= Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
		return claims;
	}
	
	private SecretKey generateKey() {
		byte[] encodeKey = Base64.decodeBase64(secretKey);
		SecretKeySpec key = new SecretKeySpec(encodeKey, 0,encodeKey.length,"AES");
		return key;
	}
	
}
