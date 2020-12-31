package com.example.services;

import com.example.info.TimerInfo;
import com.example.util.TimerUtil;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.stream.Collectors;

@Service
@Configuration
@EnableAutoConfiguration
public class SchedulerService {

    private final Scheduler scheduler;
    private static final Logger Log = LoggerFactory.getLogger(SchedulerService.class);

    @Autowired
    public SchedulerService(Scheduler scheduler){
        this.scheduler= scheduler;
    }

    public void schedule(final Class jobClass,TimerInfo info){
        System.out.println("3. Calling from schedule SchedulerService");
        final JobDetail jobDetail= TimerUtil.buildJobDetails(jobClass);
        final Trigger trigger= TimerUtil.buildTrigger(jobDetail,info);

        try{
            scheduler.scheduleJob(jobDetail,trigger);
        }
        catch(SchedulerException e){
            Log.error(e.getMessage());
        }
    }

    //Get all running jobs
    public List<TimerInfo> getAllRunningTimers(){
        try{
            return scheduler.getJobKeys(GroupMatcher.anyGroup())
                    .stream()
                    .map(jobKey -> {
                        try{
                            final JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                            return (TimerInfo) jobDetail.getJobDataMap().get(jobKey.getName());
                        }
                        catch (SchedulerException e){
                            Log.error(e.getMessage(),e);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        catch (SchedulerException e){
            Log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @PostConstruct
    public void init(){
        try{
            scheduler.start();
        }
        catch (SchedulerException e){
            Log.error(e.getMessage());
        }
    }

    @PreDestroy
    public void preDestroy(){
        try{
            scheduler.shutdown();
        }
        catch (SchedulerException e){
            Log.error(e.getMessage());
        }
    }

    public TimerInfo getRunningTimer(String timerId) {
        try {
            final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(timerId));
            if (jobDetail == null){
                return null;
            }

            return (TimerInfo) jobDetail.getJobDataMap().get(timerId);
        }
        catch (SchedulerException e) {
            Log.error(e.getMessage(),e);
            return null;
        }
    }
}
