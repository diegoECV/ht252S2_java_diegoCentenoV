package diegoCenteno;

import diegoCenteno.view.ProductView;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        // Establecer Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("No se pudo establecer el Look and Feel: " + e.getMessage());
        }
        
        // Ejecutar la aplicación en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            ProductView productView = new ProductView();
            productView.setVisible(true);
        });
    }
}

