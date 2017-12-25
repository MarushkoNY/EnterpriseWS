/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.util;

/**
 *
 * @author Nikolay
 */
public class Validator {
    
    
    
    public static int validate(String mode){
        int imode;
        try{
            imode = Integer.parseInt(mode);
        } catch (NumberFormatException ex) {
            return 0;
        }
        if (imode < 1 || imode > 2){
            return 0;
        }
        return imode;
    }
}
