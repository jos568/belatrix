/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.belatrix;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author joseph
 */
public class Belatrix {
static JobLogger jobLogger = null;
static Map dbTest = new HashMap<String, String>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String workingDirectory = System.getProperty("user.dir");
        dbTest.put("logFileFolder", workingDirectory);
       test1();
       test2();
    }
    
    static void test1(){        
        jobLogger = new JobLogger(true, true, false, true, false, true,dbTest, true); 
        try{
            JobLogger.LogMessage("test1", true, true, true);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    static void test2(){        
        jobLogger = new JobLogger(true, false, false, true, true, false,dbTest, false);   
        try{
            JobLogger.LogMessage("test2", true, true, true);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
   
}
