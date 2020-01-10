package com.mmm.weixin.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mmm.weixin.common.GenerateAccessToken;

/**
    * 定时任务
 * @author EDZ
 *
 */
@Component
public class MySchedule {
	
	@Autowired
	private GenerateAccessToken generator;
	
	private static final Logger logger = LoggerFactory.getLogger(MySchedule.class);
	
	//每小时更新一次微信access_token
	@Scheduled(cron="0 0 */1 * * *")
	public void updateAccessToken() {
		logger.info("******update weixin access token*****");
		generator.generateAccessToken();
	}
}