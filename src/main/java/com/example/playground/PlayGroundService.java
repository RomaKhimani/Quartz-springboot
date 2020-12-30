package com.example.playground;

import com.example.jobs.HelloWorldJob;
import com.example.services.SchedulerService;
import com.example.info.TimerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class PlayGroundService {

    private final SchedulerService scheduler;

    @Autowired
    public PlayGroundService(SchedulerService scheduler) {
        this.scheduler = scheduler;
    }



    public void runHelloWorldJob(){
        final TimerInfo info=new TimerInfo();
        info.setTotalFireCount(5);
        info.setRepeatIntervalMs(2000);
        info.setInitialOffsetMs(5000);
        info.setCallBackData("My callback data");

        scheduler.schedule(HelloWorldJob.class,info );
    }

    public List<TimerInfo> getAllRunningTimers(){
        return scheduler.getAllRunningTimers();
    }

    public TimerInfo getRunningTimer(String timerId) {
        return scheduler.getRunningTimer(timerId);
    }
}
