/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.iflex.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Nikolay
 */
public class Converter {
    
    
    public static Date timestampToDate(Timestamp ts){
        Date date  = new Date(ts.getTime());
        return date;
    }
    
    public static Timestamp dateToTimestamp(Date date){
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }
    
}
