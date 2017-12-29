/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.iflex.entity;

import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Nikolay
 */
@XmlRootElement
public class Data {
    
    
    private int id;
    
    private XMLGregorianCalendar requestTime;
    
    private String ip;
    
    
    public Data(){}
    
    public Data(int id,XMLGregorianCalendar  requestTime, String ip){
    this.id = id;
    this.requestTime = requestTime;
    this.ip = ip;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute(required = true)
    public void setId(int id) {
        this.id = id;
    }

    public XMLGregorianCalendar getRequestTime() {
        return requestTime;
    }

    @XmlElement(required = true)
    public void setRequestTime(XMLGregorianCalendar requestTime) {
        this.requestTime = requestTime;
    }

    public String getIp() {
        return ip;
    }

    @XmlElement(required = true)
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    
    
}
