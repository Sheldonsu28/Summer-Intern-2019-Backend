package com.mmm.weixin.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mmm.weixin.service.IMatchService;

/**
    * 定时任务
 * @author EDZ
 *
 */
@Component
public class ToolSchedule {
	
	private Logger logger = LoggerFactory.getLogger(ToolSchedule.class);
	
	@Autowired
	private IMatchService matchService;
	
//    @Scheduled(fixedRate=1000)
//    public void runTask2(){
//        System.out.println("task two:"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date()));
//    }

    @Scheduled(cron="0 */1 * * * ?")
    public void runTask(){
    	logger.info("****task begin");
    	matchService.updateStatus2Playing();
    }    
}