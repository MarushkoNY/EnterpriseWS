/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webService;

import com.mycompany.util.InitParams;
import com.mycompany.util.ServiceDef;
import com.mycompany.util.Validator;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import ru.iflex.commons.logging.Log4jLogger;
import service.Data;

/**
 *
 * @author Nikolay
 */

@Stateless
@Path("/")
public class RestHandler {
    
    private Logger logger = Log4jLogger.getLogger(RestHandler.class);
    
    private volatile static boolean isInitialized = false; 
    
    @Inject @Any 
    private Instance<Service> services;
    
    @EJB
    private Scheduler sch;
    
//    @Resource
//    TimerService timer;
    
    
    @POST
    @Path("/Store")
    @Produces("text/html")
    public Response storeData(@Context HttpServletRequest req, @FormParam("mode")String mode){
        int imode;
        if(mode == null){
            imode = 1;
        }
        String resp = "";
        //validation of input parameters
        if((imode = Validator.validate(mode))==0){
            return Response.ok().entity("Enter the proper values!").build();
        }
        if (req == null){
            System.out.println();
            logger.info("Request from timer");
        }else{
        Data data = new Data();
        data.setIp(req.getRemoteAddr());
        data.setTime(new Timestamp(System.currentTimeMillis()));
        resolveService(imode).storeData(data);
        resp = "Your IP and request time was added to server!";
       
        //Logging below
        Log4jLogger.pushThread();
        logger.info("Incoming STORE request from {}", req.getRemoteHost());
        }
        return Response.ok().entity(resp).build();
    }
    
    @GET
    @Path("/GetData")
    @Produces("text/html")
    public  Response getData(@QueryParam("mode") String mode, @Context HttpServletRequest req) throws IOException{
        int imode;
        Log4jLogger.pushThread();
        if ((imode = Validator.validate(mode)) == 0){
            return Response.ok().entity("Enter the proper values!").build();
        }
            
        Data data = resolveService(imode).getData();
        String ip = data.getIp();
        Timestamp time = data.getTime();
        if (!isInitialized){
            isInitialized = true;
        }
        //Logging below
        if (req == null){
            logger.info("Incoming GET request with null REQUEST!");
        } else{
        logger.info("Incoming GET request from {}", req.getRemoteHost());
        }
        
        
        return Response.ok().entity(ip + " " + time).build();
    }
    
    private Service resolveService(int mode) {
        Service ret = null;
        for (Service service : services) {
            ServiceDef def = getDefAnnotation(service.getClass());
            if (def != null) {
                if (def.mode() == mode) {
                    ret = service;
                    break;
                }
            }
        }
        return ret;
    }
    
    private ServiceDef getDefAnnotation(Class clazz) {
        return (ServiceDef) clazz.getAnnotation(ServiceDef.class);
    }
    
//    @POST
//    @Path("/Mode")
//    @Produces("text/html")
//    public Response chooseMode(@FormParam("mode")String mode) throws Exception{
//        if ()
//         URI uri = UriBuilder.fromUri("http://localhost:7001/").build();
//        return Response.temporaryRedirect(uri).build();
//    }
    
    
//    public void timerInitializator(){
//        ScheduleExpression sc = new ScheduleExpression().second("*/10");
//        timer.createIntervalTimer(0, 10, null);
//        System.out.println("From GETinit method");
//        
//    }
//    @Timeout
//    public void postInitializator(Timer tim){
//        new RestHandler().storeData(null, "1");
//    }
// 
    
   
}
