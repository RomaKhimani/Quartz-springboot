package com.example.util;

import com.example.info.TimerInfo;
import org.quartz.*;

import java.util.Date;

public final class TimerUtil {

    private TimerUtil(){}

    public static JobDetail buildJobDetails(final Class jobClass){
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("Job Name", jobClass.getSimpleName());
        jobDataMap.put("Job Type name",jobClass.getTypeName());

        return JobBuilder
                .newJob(jobClass)
                .withIdentity(jobClass.getSimpleName())
                .withDescription("Job schedule saved for" +jobClass.getSimpleName())
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();

//.setJobData(jobDataMap)
    }

    public static Trigger buildTrigger(final JobDetail jobDetail,TimerInfo info){
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(info.getRepeatIntervalMs());

        if(info.isRunForever()){
            builder = builder.repeatForever();
        }
        else {
            builder = builder.withRepeatCount(info.getTotalFireCount() -1);
        }

        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName())
                .withDescription("Send Trigger")
                .withSchedule(builder)
                .startAt(new Date())
                .build();

//        .newTrigger()
//                .withIdentity(jobClass.getSimpleName())
//                .withSchedule(builder)
//                .startAt(new Date(System.currentTimeMillis() + info.getInitialOffsetMs()))
//                .build();
    }
}
