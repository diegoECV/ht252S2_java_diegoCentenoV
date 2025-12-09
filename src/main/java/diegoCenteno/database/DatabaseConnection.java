package diegoCenteno.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://h251s2.c506266wsgbx.us-east-1.rds.amazonaws.com/hackaton";
    private static final String USER = "root";
    private static final String PASSWORD = "diego1416";

    public static Connection getConnection() throws SQLException {
        try {
            System.out.println("Intentando conectar a la base de datos...");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
            return conn;
        } catch (SQLException e) {
            System.err.println("Error crítico: No se pudo conectar a la base de datos.");
            System.err.println("Verifique URL, Usuario y Contraseña.");
            System.err.println("Detalle del error: " + e.getMessage());
            throw e;
        }
    }
}