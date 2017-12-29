/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webService;

import com.mycompany.gen.GetDataConfig;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import ru.iflex.commons.configuration.ConfigReader;

/**
 *
 * @author Nikolay
 */

public class RunRun implements Runnable{
    
    
    private static long beginTime = System.currentTimeMillis();
    private static boolean isActive = false;
    private static GetDataConfig cfg = ConfigReader.getInstance(GetDataConfig.class).getConfig(); 
    
    @Override
    public void run() {
//        System.out.println("*******************");
//        System.out.println( new Date() + " Entering the run method..."  );
//        if (isActive){
//        long period = System.currentTimeMillis() - beginTime;
//        beginTime = System.currentTimeMillis();
//        new RestHandler().storeData(null, "1");
//                GetDataConfig cfg = ConfigReader.getInstance(GetDataConfig.class).getConfig();
//                System.out.println((period / 1000)-2);
//                System.out.println(cfg.getPeriod());
//                if (cfg.getPeriod() != (period/1000-2)) {
//                    System.out.println("The config has changed!");
//                    
//                    new Scheduler().syncPeriod();
//                }
//        }
//                System.out.println("end of run method!");
//                System.out.println("*******************");
//                isActive = true;
        
        System.out.println("************************");
        if (isActive){
        long period = System.currentTimeMillis() - beginTime;
        System.out.println("Entering the \"run\" method with " + period/1000 + " seconds delay!");
        beginTime = System.currentTimeMillis();
        GetDataConfig newCfg = ConfigReader.getInstance(GetDataConfig.class).getConfig();
        new RestHandler().storeData(null, "1");
        if (cfg.getInitialDelay() != newCfg.getInitialDelay() || cfg.getPeriod() != newCfg.getPeriod()){
            System.out.println("The config has changed!");
            new Scheduler().syncPeriod();
            cfg = newCfg;
        }
        System.out.println("Exiting the \"run\" method!");
        } else {System.out.println("Initial startup!");}
        System.out.println("************************");
        isActive = true;
    }
    
}
