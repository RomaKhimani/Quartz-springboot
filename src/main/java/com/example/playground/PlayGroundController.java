package com.example.playground;


import com.example.info.TimerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timer")
public class PlayGroundController {

    private PlayGroundService service;

    @Autowired
    public PlayGroundController(PlayGroundService service){
        this.service=service;
    }

    @PostMapping("/runhelloworld")
    public void runHelloWorldJob(){
        service.runHelloWorldJob();
    }

    @GetMapping
    public List<TimerInfo> getAllRunningTimers(){
        return service.getAllRunningTimers();
    }

    @GetMapping("/{timerId}")
    public TimerInfo getRunningTimer(@PathVariable String timerId){
        return service.getRunningTimer(timerId);
    }
}
