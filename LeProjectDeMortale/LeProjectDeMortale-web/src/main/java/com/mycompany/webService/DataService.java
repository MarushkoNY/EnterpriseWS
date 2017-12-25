/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webService;

import service.Data;
import service.DataWS;
import service.GetDataService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Nikolay
 */
@Stateless
public class DataService {

    
    
    public Data getData(){
        DataWS port = new GetDataService().getGetDataPort();
        Data data = port.getData();
        return data;
    }
    
    
}
