/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webService;

import com.mycompany.gen.GetDataConfig;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import ru.iflex.commons.configuration.ConfigReader;

/**
 *
 * @author Nikolay
 */
@Singleton
@Startup
public class Scheduler {

    @EJB
    private RestHandler handler;
    
    
    private static ScheduledExecutorService service;

    public static ScheduledFuture<?> currentTimer;

    @PostConstruct
    private void init() {
        initTimer();
    }

//    public GetDataConfig getDC() {
//        return ConfigReader.getInstance(GetDataConfig.class).getConfig();
//    }

    @Schedule(second = "*/10", minute = "*", hour = "*")
    private void initGet() {
        System.out.println("Get method invocation from Scheduler");
        try {
            handler.getData("1", null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    public void refresher(){
//        service.scheduleAtFixedRate(new Refreshment(), 40, 20, TimeUnit.SECONDS);
//        System.out.println("Refresher was initiated!");
//      
//    }
    public void syncPeriod() {
        GetDataConfig cfg = ConfigReader.getInstance(GetDataConfig.class).getConfig();
        currentTimer.cancel(true);
        currentTimer = service.scheduleAtFixedRate(new RunRun(), cfg.getInitialDelay(), cfg.getPeriod(), TimeUnit.SECONDS);
    }

    public void initTimer() {
        GetDataConfig cfg = ConfigReader.getInstance(GetDataConfig.class).getConfig();
        System.out.println("Main timer was initiated!");
        service = Executors.newSingleThreadScheduledExecutor();
        currentTimer = service.scheduleAtFixedRate(new RunRun(), cfg.getInitialDelay(), cfg.getPeriod(), TimeUnit.SECONDS);
    }
//    public void shutdown(){
//        service.shutdown();
//    }
//     
//   public void initNewTimer(){
////        service.shutdown();
////        service = null;
////        service = Executors.newSingleThreadScheduledExecutor();
//        service.scheduleAtFixedRate(new RunRun(), RunRun.cfg.getInitialDelay(), RunRun.cfg.getPeriod(), TimeUnit.SECONDS);
//        System.out.println("The new timer was initiated");
//    }

}
