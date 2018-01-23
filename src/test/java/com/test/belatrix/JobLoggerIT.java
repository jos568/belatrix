/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.belatrix;

import static com.test.belatrix.Belatrix.dbTest;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author joseph
 */
public class JobLoggerIT {

    private static boolean logToFile;
    private static boolean logToConsole;
    private static boolean logMessage;
    private static boolean logWarning;
    private static boolean logError;
    private static boolean logToDatabase;
    private static boolean isOnlyError;
    private boolean initialized;
    private static Map dbParams = new HashMap<String, String>();
    private static Logger logger;
    private static DatabaseConnector databaseConnector = new DatabaseConnector();

    public JobLoggerIT() {
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Map dbTest = new HashMap<String, String>();
        String workingDirectory = System.getProperty("user.dir");
        dbTest.put("logFileFolder", workingDirectory);
        new JobLogger(true, true, false, true, false, true,dbTest, true);    
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of LogMessage method, of class JobLogger.
     */
    @Test
    public void testLogMessage() {
        System.out.println("LogMessage");
        String messageText = "";
        boolean message = false;
        boolean warning = false;
        boolean error = false;
        try {
            JobLogger.LogMessage(messageText, message, warning, error);            
        } catch (Exception expectedException) {
            fail("My method throw an exception");
        }
        
        

        // TODO review the generated test code and remove the default call to fail.
    }

}
