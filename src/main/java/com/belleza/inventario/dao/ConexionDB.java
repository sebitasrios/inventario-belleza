package com.belleza.inventario.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=inventario_belleza;" +
            "encrypt=false;" +
            "integratedSecurity=true;";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}