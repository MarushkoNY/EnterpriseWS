/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.util;

import java.io.IOException;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Nikolay
 */
@Singleton
@Startup
public class WSParameters {
    
    
    private static boolean isInitialized = false;
    
    
    public void init() throws IOException{
        if (isInitialized == false){
            new InitParams().init();
        }
    }
    
    
}
