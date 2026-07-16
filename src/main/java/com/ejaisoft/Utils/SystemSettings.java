package com.ejaisoft.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SystemSettings {
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties props = new Properties();
        try (InputStream in = SystemSettings.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (in == null) {
                throw new IllegalStateException(
                        "db.properties not found on the classpath. Copy src/main/resources/db.properties.example " +
                                "to src/main/resources/db.properties and fill in your database credentials.");
            }
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load db.properties", e);
        }

        URL = props.getProperty("db.url");
        USERNAME = props.getProperty("db.username");
        PASSWORD = props.getProperty("db.password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("MySQL JDBC driver not found on the classpath", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
