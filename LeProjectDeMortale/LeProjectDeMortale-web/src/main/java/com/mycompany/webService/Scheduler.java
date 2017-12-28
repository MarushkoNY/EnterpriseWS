/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webService;

import generated.GetDataConfig;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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
    
    static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    
//    @Resource
//    private SessionContext ctx;
    
    
    @PostConstruct
    private void init(){
        initTimer();
    }
    
    @Schedule(second = "*/10", minute = "*", hour = "*" )
    private void initGet(){
        System.out.println("Get method invocation from Scheduler");
        try{
            handler.getData("1", null);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
//    public void comparator(){
//        ScheduledExecutorService service1 = Executors.newSingleThreadScheduledExecutor();
//                
//        
//    }
    
    public void initTimer(){
        GetDataConfig cfg = RunRun.cfg;
        
        final RestHandler handler = new RestHandler();
//        service = null;
//        service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new RunRun(), cfg.getInitialDelay() , cfg.getPeriod(), TimeUnit.SECONDS);
    }
    
    public void initNewTimer(){
        service.shutdownNow();
        service.scheduleAtFixedRate(new RunRun(), RunRun.cfg.getInitialDelay(), RunRun.cfg.getPeriod(), TimeUnit.SECONDS);
    }
    
    
//    public void initPost(){
//        
//        ScheduleExpression exp = new ScheduleExpression().second("*/10").minute("*").hour("*");
//        ctx.getTimerService().createCalendarTimer(exp);
//    }
//    @Timeout
//    private void initPost(Timer timer){
//        System.out.println("initPOST method invocation from Scheduler");
//        handler.storeData(null, "1");
//    }
//    
    
    
}
