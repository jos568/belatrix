/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.belatrix;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joseph
 */
public class DatabaseConnectorIT {
    
    public DatabaseConnectorIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getConnection method, of class DatabaseConnector.
     */
    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        DatabaseConnector instance = new DatabaseConnector();
        Connection expResult = null;
        Connection result = instance.getConnection();
        assertEquals(expResult, result);        
    }
    
}
