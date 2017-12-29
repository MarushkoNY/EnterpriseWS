/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.iflex.dao;

import ru.iflex.entity.Data;


/**
 *
 * @author Nikolay
 */
public interface DataDAO {
    
    
    public Data getData();
    
    public void storeData(Data data);
}
