package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    // Cambiá "hotel" por el nombre de tu base creada en Workbench
    private static final String URL = "jdbc:mysql://localhost:3306/hotel?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // tu usuario
    private static final String PASSWORD = "tu_password"; // la contraseña que pusiste al instalar

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Test rápido de conexión
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Conexión exitosa a MySQL!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

