package diegoCenteno.controller;

import diegoCenteno.model.Producto;
import diegoCenteno.service.ProductoService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class ProductoController {
    private final ProductoService service;

    public ProductoController() {
        this.service = new ProductoService();
    }

    public boolean crear(Producto producto, JFrame parent) {
        try {
            if (service.crear(producto)) {
                JOptionPane.showMessageDialog(parent, "Producto creado exitosamente");
                return true;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parent, "Error: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Producto producto, JFrame parent) {
        try {
            if (service.actualizar(producto)) {
                JOptionPane.showMessageDialog(parent, "Producto actualizado");
                return true;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parent, "Error: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id, JFrame parent) {
        try {
            if (JOptionPane.showConfirmDialog(parent, "¿Eliminar producto?") == JOptionPane.YES_OPTION) {
                if (service.eliminar(id)) {
                    JOptionPane.showMessageDialog(parent, "Producto eliminado");
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parent, "Error: " + e.getMessage());
            return false;
        }
    }

    public List<Producto> listarTodos(JFrame parent) {
        try {
            return service.listarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parent, "Error: " + e.getMessage());
            return null;
        }
    }

    public Producto buscarPorId(int id, JFrame parent) {
        try {
            return service.buscarPorId(id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parent, "Error: " + e.getMessage());
            return null;
        }
    }

    public List<Producto> buscarPorCodigo(String codigo, JFrame parent) {
        try {
            return service.buscarPorCodigo(codigo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parent, "Error: " + e.getMessage());
            return null;
        }
    }

    public List<Producto> buscarPorTipo(String tipo, JFrame parent) {
        try {
            return service.buscarPorTipo(tipo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parent, "Error: " + e.getMessage());
            return null;
        }
    }

    public List<Producto> obtenerEquiposProximoMantenimiento(int diasAnticipacion, JFrame parent) {
        try {
            return service.obtenerEquiposProximoMantenimiento(diasAnticipacion);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parent, "Error: " + e.getMessage());
            return null;
        }
    }
}
