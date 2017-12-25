/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webService;

import com.mycompany.util.Sender;
import com.mycompany.util.ServiceDef;
import service.Data;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Nikolay
 */

@ServiceDef(mode = 1)
public class DataServiceCDI implements Service {
    
   
    
        public Data getData(){
            DataService ds = new DataService();
            Data data = ds.getData();
            return data;
        }
        
        public void storeData(Data data){
            new Sender().sendData(data);
        }
}
