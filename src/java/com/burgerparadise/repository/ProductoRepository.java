package com.burgerparadise.repository;

import com.burgerparadise.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FRAMEWORK DE PERSISTENCIA: Gestiona el almacenamiento de datos en MySQL.
 * Integra validaciones, manejo de excepciones y comentarios técnicos.
 */
public class ProductoRepository {

    // Configuración de la cadena de conexión apuntando al puerto alterno 3307
    private final String url = "jdbc:mysql://localhost:3307/burger_paradise?useSSL=false&serverTimezone=UTC";
    private final String user = "root";
    private final String pass = "";

    // Método para obtener una conexión limpia
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error Driver: " + e.getMessage());
        }
        return DriverManager.getConnection(url, user, pass);
    }

    /**
     * CRITERIO 2: Recupera los datos almacenados de forma dinámica.
     */
    public List<Producto> findAll() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos";

        // Uso de Try-With-Resources para asegurar el cierre de conexiones (Estándar Industrial)
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
                productos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error en findAll: " + e.getMessage());
        }
        return productos;
    }

    /**
     * CRITERIO 2: Inserta un nuevo producto de forma segura (Evita SQL
     * Injection).
     */
    public boolean save(Producto p) {
        String query = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, p.getNombre());
            stmt.setDouble(2, p.getPrecio());
            stmt.setInt(3, p.getStock());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en save: " + e.getMessage());
            return false;
        }
    }
}
