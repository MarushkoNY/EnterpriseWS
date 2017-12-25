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

/**
 *
 * @author Nikolay
 */

public class Marsh {
    
    static private JAXBContext ctx;
    
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
        }
            return result;
        }
}

