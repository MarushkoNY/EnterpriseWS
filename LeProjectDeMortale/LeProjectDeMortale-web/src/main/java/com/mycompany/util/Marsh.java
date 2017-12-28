/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.util;

import service.Data;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import ru.iflex.commons.logging.Log4jLogger;

/**
 *
 * @author Nikolay
 */

public class Marsh {
    
    static private JAXBContext ctx;
    private static Logger logger = Log4jLogger.getLogger(Marsh.class);
    
    public static String marshal(Data data){
        String result = "";

        if (ctx == null){
            try{
                ctx = JAXBContext.newInstance(Data.class);
            } catch (Exception ex ){
                ex.printStackTrace();
            }
        }
        try{
            StringWriter sw = new StringWriter();

            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(data, sw);
            result = sw.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Exception occured while unmarshalling!");
        }
            return result;
        }
}

