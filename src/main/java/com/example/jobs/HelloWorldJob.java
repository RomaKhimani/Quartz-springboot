package com.example.jobs;

import com.example.info.TimerInfo;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class HelloWorldJob implements Job {

    private static final Logger LOG= LoggerFactory.getLogger(HelloWorldJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        System.out.println("4. Calling from execute"+jobExecutionContext);
//        JobDataMap jobDataMap= jobExecutionContext.getJobDetail().getJobDataMap();
//        TimerInfo info= (TimerInfo) jobDataMap.get(HelloWorldJob.class.getSimpleName());
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        LOG.info("SimpleJob executed: " + jobKey + " executing at " + new Date());
    }
}
