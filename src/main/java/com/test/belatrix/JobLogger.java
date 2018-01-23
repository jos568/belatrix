/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.belatrix;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobLogger {

    private static boolean logToFile;
    private static boolean logToConsole;
    private static boolean logMessage;
    private static boolean logWarning;
    private static boolean logError;
    private static boolean logToDatabase;
    private static boolean isOnlyError;
    private boolean initialized;
    private static Map dbParams;
    private static Logger logger;
    private static DatabaseConnector databaseConnector = new DatabaseConnector();

    public JobLogger(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
            boolean logMessageParam, boolean logWarningParam, boolean logErrorParam, Map dbParamsMap, boolean isOnlyError) {
        logger = Logger.getLogger("MyLog");
        logError = logErrorParam;
        logMessage = logMessageParam;
        logWarning = logWarningParam;
        logToDatabase = logToDatabaseParam;
        logToFile = logToFileParam;
        logToConsole = logToConsoleParam;
        dbParams = dbParamsMap;
        this.isOnlyError = isOnlyError;
    }

    public static void LogMessage(String messageText, boolean message, boolean warning, boolean error) throws Exception {
        messageText.trim();
        if (messageText == null || messageText.length() == 0) {
            return;
        }

        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new Exception("Invalid configuration");
        }

        if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {
            throw new Exception("Error or Warning or Message must be specified");
        }

        int tierLevel = getTierLevel(0, message, error, warning);

        String logErrorMessage = setLogErrorMessage("", message, error, warning, messageText);
        
        if(isOnlyError && tierLevel == 2){
            writeLogs(logErrorMessage, tierLevel);
        }else if(!isOnlyError && tierLevel == 3 || tierLevel == 2) {
            writeLogs(logErrorMessage, tierLevel);
        }
        

    }

    private static int getTierLevel(int tierLevel, boolean message, boolean error, boolean warning) {
        if (message && logMessage) {
            tierLevel = 1;
        }

        if (error && logError) {
            tierLevel = 2;
        }

        if (warning && logWarning) {
            tierLevel = 3;
        }      
        
        return tierLevel;
    }

    private static String setLogErrorMessage(String logErrorMessage, boolean message, boolean error, boolean warning, String messageText) {
        if (error && logError) {
            logErrorMessage = logErrorMessage + "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
        }

        if (warning && logWarning) {
            logErrorMessage = logErrorMessage + "warning " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
        }

        if (message && logMessage) {
            logErrorMessage = logErrorMessage + "message " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
        }
        return logErrorMessage;
    }

    private static FileHandler fileHandler() throws Exception {

        File logFile = new File(dbParams.get("logFileFolder") + "/logFile.txt");
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        FileHandler fileHandler = new FileHandler(dbParams.get("logFileFolder") + "/logFile.txt");
        return fileHandler;
    }

    private static void writeLogs(String logErrorMessage, int tierLevel) throws Exception {
        if (logToFile) {
            logger.addHandler(fileHandler());
            logger.log(Level.INFO, logErrorMessage+" saved on file");
        }

        if (logToConsole) {
            ConsoleHandler ch = new ConsoleHandler();
            logger.addHandler(ch);
            logger.log(Level.INFO, logErrorMessage + " saved on console");
        }

        if (logToDatabase) {
            if (!dbParams.isEmpty()) {
                databaseConnector = new DatabaseConnector(dbParams);
            }
            Connection connection = databaseConnector.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into Log_Values('" + logErrorMessage + "', " + String.valueOf(tierLevel) + ")");
        }
    }
}
