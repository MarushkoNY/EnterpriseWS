/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.iflex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import ru.iflex.entity.Data;
import ru.iflex.util.Converter;

/**
 *
 * @author Nikolay
 */
@Stateless
public class DataDAOImpl implements DataDAO {

    @Resource(name = "mysql")
    DataSource ds;
    
    @Override
    public Data getData() {
        
        Data data = new Data();
        String query  = "SELECT * FROM data ORDER BY id DESC LIMIT 1;";
        try{
           try(Connection connection = ds.getConnection();
                   Statement statement = connection.createStatement();
                   ResultSet rs = statement.executeQuery(query)){
               while (rs.next()){
                   data.setId(rs.getInt("id"));
                   data.setRequestTime(Converter.timestampToDate(rs.getTimestamp("date")));
               }
           } catch (Exception ex) {
                ex.printStackTrace();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public void storeData(Data data) {
        String query = "INSERT INTO data(date, ip) VALUES (?,?);";
        Timestamp ts = Converter.dateToTimestamp(data.getRequestTime());
        try{
            try(Connection connection = ds.getConnection();
                    PreparedStatement ps = connection.prepareStatement(query);){
                ps.setTimestamp(1, null);
                ps.setString(2, data.getIp());
                ps.executeUpdate();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
