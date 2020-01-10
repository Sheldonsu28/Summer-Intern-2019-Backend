package com.mmm.weixin.config;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration 
public class RegistrySchedule implements SchedulingConfigurer{

      @Override
      public void configureTasks(ScheduledTaskRegistrar registrar) {    //开启一个线程调度池
            registrar.setScheduler(Executors.newScheduledThreadPool(10));
      }

      
}