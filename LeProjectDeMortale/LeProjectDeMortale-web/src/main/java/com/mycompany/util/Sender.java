/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.util;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import service.Data;
import com.mycompany.util.Marsh;
import com.mycompany.webService.RestHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import ru.iflex.commons.logging.Log4jLogger;

/**
 *
 * @author Nikolay
 */

public class Sender {
//    
    
      Logger logger = Log4jLogger.getLogger(Sender.class);
//    @Resource(name = "ConnectionFactory1")
//    private ConnectionFactory factory;
//    
//    @Resource(name = "Queue1")
//    private Queue queue;
    
       public void sendData(Data data){
           String sMessage = Marsh.marshal(data);
           
           Connection connection = null;
           Session session = null;
           try{
            ConnectionFactory factory  = (ConnectionFactory) new InitialContext().lookup("ConnectionFactory1");
            Queue queue = (Queue) new InitialContext().lookup("Queue1");
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            message.setText(sMessage);
            producer.send(message);
            
            
            logger.info("The object \"data\" was sent");
            
           }catch (Exception ex) {
               ex.printStackTrace();
               logger.error("Exception occuring while JMS queue sending {}", ex);
           }
       }
  
}
