package com.example.jobs;

import com.example.info.TimerInfo;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldJob implements Job {

    private static final Logger LOG= LoggerFactory.getLogger(HelloWorldJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        JobDataMap jobDataMap= jobExecutionContext.getJobDetail().getJobDataMap();
        TimerInfo info= (TimerInfo) jobDataMap.get(HelloWorldJob.class.getSimpleName());
        LOG.info(info.getCallBackData());
    }
}
