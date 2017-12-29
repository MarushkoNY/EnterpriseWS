/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.iflex.service;

import javax.jws.WebService;
import ru.iflex.dao.DataDAOImpl;
import ru.iflex.entity.Data;

/**
 *
 * @author Nikolay
 */
@WebService(serviceName = "DataService", portName = "DataServicePort")
public class DataServiceImpl implements DataService{

    @Override
    public Data getData() {
        return new DataDAOImpl().getData();
    }

    @Override
    public void storeData(Data data) {
        new DataDAOImpl().storeData(data);
    }
    
}
