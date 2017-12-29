package ru.iflex.service;


import javax.jws.WebMethod;
import javax.jws.WebService;
import ru.iflex.entity.Data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolay
 */

@WebService
public interface DataService {
    
    
    @WebMethod(operationName = "getData")
    public Data getData();
    
    
    @WebMethod(operationName = "storeData")
    public void storeData(Data data);
}
