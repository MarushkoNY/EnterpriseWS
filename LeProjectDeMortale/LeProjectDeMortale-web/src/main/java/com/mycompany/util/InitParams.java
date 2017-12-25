/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.util;

import com.mycompany.generated.GetDataConfig;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import javax.xml.ws.BindingProvider;
import ru.iflex.commons.configuration.ConfigReader;
import service.GetDataService;


/**
 *
 * @author Nikolay
 */
public class InitParams {
    
    public void init() throws FileNotFoundException, IOException{
            
        GetDataConfig cfg = new ConfigReader().getInstance(GetDataConfig.class).getConfig();
        
        System.out.println(cfg.getConnectionTimeout());
        BindingProvider bp = (BindingProvider) (BindingProvider) new GetDataService().getGetDataPort();
        Map<String,Object> ctx = bp.getRequestContext();
        ctx.put(com.sun.xml.internal.ws.developer.JAXWSProperties.CONNECT_TIMEOUT,  cfg.getConnectionTimeout());
        ctx.put(com.sun.xml.internal.ws.developer.JAXWSProperties.REQUEST_TIMEOUT, cfg.getRecieveTimeout());
        ctx.put(com.sun.xml.internal.ws.developer.JAXWSProperties.WSENDPOINT, cfg.getEndpointLocation());
        
        
        
    }

    
    
    
    
    
}
