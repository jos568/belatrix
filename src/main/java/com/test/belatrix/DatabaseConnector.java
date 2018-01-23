/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.belatrix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author joseph
 */
public class DatabaseConnector {

    Map dbParams = new HashMap<String, String>();

    public DatabaseConnector() {
        this.dbParams.put("userName", null);
        this.dbParams.put("password", null);
        this.dbParams.put("dbms", null);
        this.dbParams.put("serverName", null);
        this.dbParams.put("portNumber", null);
    }

    public DatabaseConnector(Map dbParams) {
        if (dbParams.get("userName") != null || dbParams.get("password") != null
                || dbParams.get("dbms") != null || dbParams.get("serverName") != null
                || dbParams.get("portNumber") != null) {
            this.dbParams.put("userName", dbParams.get("userName"));
            this.dbParams.put("password", dbParams.get("password"));
            this.dbParams.put("dbms", dbParams.get("dbms"));
            this.dbParams.put("serverName", dbParams.get("serverName"));
            this.dbParams.put("portNumber", dbParams.get("portNumber"));
        }
    }

    public Connection getConnection() throws Exception {
        Connection connection = null;
        connection = setConnection(connection);
        return connection;
    }

    private Connection setConnection(Connection connection) throws Exception {

        Properties connectionProps = new Properties();
        if (dbParams.get("userName") != null) {
            connectionProps.put("user", dbParams.get("userName"));
            connectionProps.put("password", dbParams.get("password"));

            connection = DriverManager.getConnection("jdbc:" + dbParams.get("dbms") + "://" + dbParams.get("serverName")
                    + ":" + dbParams.get("portNumber") + "/", connectionProps);
        }
        return connection;
    }

}
