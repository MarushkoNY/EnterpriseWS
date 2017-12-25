/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webService;

import com.mycompany.util.ServiceDef;
import service.Data;
import java.util.ArrayList;
import javax.inject.Qualifier;

/**
 *
 * @author Nikolay
 */
@ServiceDef(mode = 2)
public class DataServiceMock implements Service{
    
    private static ArrayList<Data> dataList = new ArrayList<Data>();
    
    public void storeData(Data data){
        dataList.add(data);
    }
    
    public Data getData(){
        if (dataList.size() == 0){
            return new Data();
        }
        return dataList.get(dataList.size()-1);
    }
    
}
