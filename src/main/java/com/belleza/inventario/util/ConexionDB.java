package com.belleza.inventario.util;

import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConexionDB {

    private static final String URL = "jdbc:sqlserver://localhost\\SQLDEV;" +
            "databaseName=inventario_belleza;" +
            "encrypt=false;" +
            "trustServerCertificate=true;" +
            "user=sa;" +
            "password=Belleza2026;";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}