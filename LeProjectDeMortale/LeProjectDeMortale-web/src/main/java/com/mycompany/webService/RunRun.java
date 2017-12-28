/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webService;

import generated.GetDataConfig;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import ru.iflex.commons.configuration.ConfigReader;

/**
 *
 * @author Nikolay
 */
public class RunRun implements Runnable{

    public static GetDataConfig cfg = ConfigReader.getInstance(GetDataConfig.class).getConfig();
    
    @Override
    public void run() {
        GetDataConfig cfg2 = ConfigReader.getInstance(GetDataConfig.class).getConfig();
        System.out.println(new Date() + ":Post method was invoked with period = " + cfg.getPeriod());
        new RestHandler().storeData(null, "1");
        if (!cfg.equals(cfg2)){
            cfg = cfg2;
            new Scheduler().initNewTimer();
        }
    }
    
}
