package diegoCenteno.service;

import diegoCenteno.database.DatabaseConnection;
import diegoCenteno.model.Producto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    public boolean crear(Producto producto) throws SQLException {
        String sql = "INSERT INTO equipos (codigo, tipo, marcas, modelo, so, almacenamiento, ram, estado, mantenimiento) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getCodigo());
            stmt.setString(2, producto.getTipoEquipo());
            stmt.setString(3, producto.getMarca());
            stmt.setString(4, producto.getModelo());
            stmt.setString(5, producto.getSistemaOperativo());
            stmt.setInt(6, producto.getAlmacenamiento());
            stmt.setInt(7, producto.getRam());
            stmt.setString(8, producto.getEstado());
            stmt.setDate(9, Date.valueOf(producto.getFechaMantenimiento()));
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Producto> listarTodos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM equipos ORDER BY fecha_registro DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        }
        return productos;
    }

    public Producto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM equipos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapearProducto(rs);
            }
        }
        return null;
    }

    public List<Producto> buscarPorTipo(String tipo) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM equipos WHERE tipo = ? ORDER BY codigo";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        }
        return productos;
    }

    public List<Producto> buscarPorCodigo(String codigo) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM equipos WHERE codigo LIKE ? ORDER BY codigo";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + codigo + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        }
        return productos;
    }

    public boolean actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE equipos SET codigo = ?, tipo = ?, marcas = ?, modelo = ?, so = ?, " +
                     "almacenamiento = ?, ram = ?, estado = ?, mantenimiento = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getCodigo());
            stmt.setString(2, producto.getTipoEquipo());
            stmt.setString(3, producto.getMarca());
            stmt.setString(4, producto.getModelo());
            stmt.setString(5, producto.getSistemaOperativo());
            stmt.setInt(6, producto.getAlmacenamiento());
            stmt.setInt(7, producto.getRam());
            stmt.setString(8, producto.getEstado());
            stmt.setDate(9, Date.valueOf(producto.getFechaMantenimiento()));
            stmt.setInt(10, producto.getIdProducto());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM equipos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Producto> obtenerEquiposProximoMantenimiento(int diasAnticipacion) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM equipos WHERE mantenimiento BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL ? DAY) " +
                     "ORDER BY mantenimiento ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, diasAnticipacion);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        }
        return productos;
    }

    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setIdProducto(rs.getInt("id"));
        producto.setCodigo(rs.getString("codigo"));
        producto.setTipoEquipo(rs.getString("tipo"));
        producto.setMarca(rs.getString("marcas"));
        producto.setModelo(rs.getString("modelo"));
        producto.setSistemaOperativo(rs.getString("so"));
        producto.setAlmacenamiento(rs.getInt("almacenamiento"));
        producto.setRam(rs.getInt("ram"));
        producto.setEstado(rs.getString("estado"));

        Date fechaMantenimiento = rs.getDate("mantenimiento");
        if (fechaMantenimiento != null) {
            producto.setFechaMantenimiento(fechaMantenimiento.toLocalDate());
        }

        Timestamp fechaRegistro = rs.getTimestamp("fecha_registro");
        if (fechaRegistro != null) {
            producto.setFechaRegistro(fechaRegistro.toLocalDateTime());
        }

        Timestamp fechaActualizacion = rs.getTimestamp("fecha_actualizacion");
        if (fechaActualizacion != null) {
            producto.setFechaActualizacion(fechaActualizacion.toLocalDateTime());
        }

        return producto;
    }
}


