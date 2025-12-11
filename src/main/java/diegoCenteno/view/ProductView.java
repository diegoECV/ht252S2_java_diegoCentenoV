package diegoCenteno.view;

import diegoCenteno.controller.ProductoController;
import diegoCenteno.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ProductView extends JFrame {
    private final ProductoController controller;
    private JTextField txtCodigo, txtAlmacenamiento, txtRam, txtFechaMantenimiento, txtEstado;
    private JComboBox<String> cboTipo, cboMarca, cboModelo, cboSO;
    private JTable table;
    private DefaultTableModel tableModel;
    private Integer selectedId;

    public ProductView() {
        this.controller = new ProductoController();
        initComponents();
        cargarListas();
        cargarProductos();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Inventario de Computadoras - Hackathon 251-S2");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos de Equipos Informarticos"));

        formPanel.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        formPanel.add(txtCodigo);

        formPanel.add(new JLabel("Tipo de equipo:"));
        cboTipo = new JComboBox<>();
        formPanel.add(cboTipo);

        formPanel.add(new JLabel("Marca:"));
        cboMarca = new JComboBox<>();
        formPanel.add(cboMarca);

        formPanel.add(new JLabel("Modelo:"));
        cboModelo = new JComboBox<>();
        formPanel.add(cboModelo);

        formPanel.add(new JLabel("Sistema Operativo:"));
        cboSO = new JComboBox<>();
        formPanel.add(cboSO);

        formPanel.add(new JLabel("Almacenamiento (GB):"));
        txtAlmacenamiento = new JTextField();
        formPanel.add(txtAlmacenamiento);

        formPanel.add(new JLabel("RAM (GB):"));
        txtRam = new JTextField();
        formPanel.add(txtRam);

        formPanel.add(new JLabel("Estado:"));
        txtEstado = new JTextField();
        formPanel.add(txtEstado);

        formPanel.add(new JLabel("Fecha de Mantenimiento (Automático):"));
        txtFechaMantenimiento = new JTextField();
        txtFechaMantenimiento.setEditable(false);
        txtFechaMantenimiento.setBackground(new Color(240, 240, 240));
        // Establecer fecha de mantenimiento automática (fecha actual)
        LocalDate fechaMantenimiento = LocalDate.now();
        txtFechaMantenimiento.setText(fechaMantenimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        formPanel.add(txtFechaMantenimiento);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnGuardar = new JButton("Registrar");
        btnGuardar.setBackground(new Color(76, 175, 80));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(e -> guardar());
        buttonPanel.add(btnGuardar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBackground(new Color(33, 150, 243));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        btnActualizar.addActionListener(e -> actualizar());
        buttonPanel.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(244, 67, 54));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(e -> eliminar());
        buttonPanel.add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBackground(new Color(158, 158, 158));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(e -> limpiar());
        buttonPanel.add(btnLimpiar);

        JButton btnVolver = new JButton("Cerrar");
        btnVolver.setBackground(new Color(96, 125, 139));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.addActionListener(e -> dispose());
        buttonPanel.add(btnVolver);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Tabla
        String[] columns = {"ID", "Código", "Tipo", "Marca", "Modelo", "SO", "Almacenamiento (GB)", "RAM (GB)", "Estado", "Mantenimiento", "Registro"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                cargarDatosTabla();
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Equipos"));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void cargarListas() {
        // Tipos de equipo
        String[] tipos = {"Laptop", "Desktop", "All-in-One", "Servidor", "Tablet", "Workstation"};
        for (String t : tipos) cboTipo.addItem(t);

        // Marcas
        String[] marcas = {"HP", "ASUS", "Dell", "Lenovo", "Acer", "Apple", "MSI", "Samsung", "Toshiba"};
        for (String m : marcas) cboMarca.addItem(m);

        // Modelos (genéricos, idealmente cargar según marca)
        String[] modelos = {"ProBook 640 G5", "VivoBook 15", "Inspiron 15", "ThinkPad T14", "Aspire 5",
                           "MacBook Pro", "Predator Helios", "Galaxy Book", "Satellite Pro"};
        for (String mo : modelos) cboModelo.addItem(mo);

        // Sistemas Operativos
        String[] sistemas = {"Windows 10", "Windows 11", "Linux Ubuntu", "MacOS", "Chrome OS"};
        for (String s : sistemas) cboSO.addItem(s);
    }

    private void cargarProductos() {
        tableModel.setRowCount(0);
        controller.listarTodos(this).forEach(p -> {
            String fechaMant = p.getFechaMantenimiento() != null
                ? p.getFechaMantenimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : "";
            String fechaReg = p.getFechaRegistro() != null
                ? p.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                : "";

            tableModel.addRow(new Object[]{
                p.getIdProducto(),
                p.getCodigo(),
                p.getTipoEquipo(),
                p.getMarca(),
                p.getModelo(),
                p.getSistemaOperativo(),
                p.getAlmacenamiento() + " GB",
                p.getRam() + " GB",
                p.getEstado(),
                fechaMant,
                fechaReg
            });
        });
    }

    private void cargarDatosTabla() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        selectedId = (Integer) tableModel.getValueAt(row, 0);
        Producto p = controller.buscarPorId(selectedId, this);

        if (p != null) {
            txtCodigo.setText(p.getCodigo());
            cboTipo.setSelectedItem(p.getTipoEquipo());
            cboMarca.setSelectedItem(p.getMarca());
            cboModelo.setSelectedItem(p.getModelo());
            cboSO.setSelectedItem(p.getSistemaOperativo());
            txtAlmacenamiento.setText(String.valueOf(p.getAlmacenamiento()));
            txtRam.setText(String.valueOf(p.getRam()));
            txtEstado.setText(p.getEstado());

            if (p.getFechaMantenimiento() != null) {
                txtFechaMantenimiento.setText(p.getFechaMantenimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }
    }

    private void guardar() {
        if (!validarCampos()) return;

        Producto producto = new Producto();
        producto.setCodigo(txtCodigo.getText().trim());
        producto.setTipoEquipo((String) cboTipo.getSelectedItem());
        producto.setMarca((String) cboMarca.getSelectedItem());
        producto.setModelo((String) cboModelo.getSelectedItem());
        producto.setSistemaOperativo((String) cboSO.getSelectedItem());
        producto.setAlmacenamiento(parseInteger(txtAlmacenamiento.getText().trim()));
        producto.setRam(parseInteger(txtRam.getText().trim()));
        producto.setEstado(txtEstado.getText().trim());
        producto.setFechaMantenimiento(parseFecha(txtFechaMantenimiento.getText().trim()));

        if (controller.crear(producto, this)) {
            cargarProductos();
            limpiar();
            JOptionPane.showMessageDialog(this, "Equipo registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizar() {
        if (selectedId == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un equipo de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarCampos()) return;

        Producto producto = new Producto();
        producto.setIdProducto(selectedId);
        producto.setCodigo(txtCodigo.getText().trim());
        producto.setTipoEquipo((String) cboTipo.getSelectedItem());
        producto.setMarca((String) cboMarca.getSelectedItem());
        producto.setModelo((String) cboModelo.getSelectedItem());
        producto.setSistemaOperativo((String) cboSO.getSelectedItem());
        producto.setAlmacenamiento(parseInteger(txtAlmacenamiento.getText().trim()));
        producto.setRam(parseInteger(txtRam.getText().trim()));
        producto.setEstado(txtEstado.getText().trim());
        producto.setFechaMantenimiento(parseFecha(txtFechaMantenimiento.getText().trim()));

        if (controller.actualizar(producto, this)) {
            cargarProductos();
            limpiar();
            JOptionPane.showMessageDialog(this, "Equipo actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminar() {
        if (selectedId == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un equipo de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de eliminar este equipo?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (controller.eliminar(selectedId, this)) {
                cargarProductos();
                limpiar();
                JOptionPane.showMessageDialog(this, "Equipo eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void limpiar() {
        selectedId = null;
        txtCodigo.setText("");
        if (cboTipo.getItemCount() > 0) cboTipo.setSelectedIndex(0);
        if (cboMarca.getItemCount() > 0) cboMarca.setSelectedIndex(0);
        if (cboModelo.getItemCount() > 0) cboModelo.setSelectedIndex(0);
        if (cboSO.getItemCount() > 0) cboSO.setSelectedIndex(0);
        txtAlmacenamiento.setText("");
        txtRam.setText("");
        txtEstado.setText("");
        // Restablecer fecha de mantenimiento automática (fecha actual)
        LocalDate fechaMantenimiento = LocalDate.now();
        txtFechaMantenimiento.setText(fechaMantenimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        table.clearSelection();
    }

    private boolean validarCampos() {
        if (txtCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el código del equipo", "Error", JOptionPane.ERROR_MESSAGE);
            txtCodigo.requestFocus();
            return false;
        }

        if (cboTipo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione el tipo de equipo", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (txtAlmacenamiento.getText().trim().isEmpty() || parseInteger(txtAlmacenamiento.getText().trim()) <= 0) {
            JOptionPane.showMessageDialog(this, "Ingrese un almacenamiento válido", "Error", JOptionPane.ERROR_MESSAGE);
            txtAlmacenamiento.requestFocus();
            return false;
        }

        if (txtRam.getText().trim().isEmpty() || parseInteger(txtRam.getText().trim()) <= 0) {
            JOptionPane.showMessageDialog(this, "Ingrese una RAM válida", "Error", JOptionPane.ERROR_MESSAGE);
            txtRam.requestFocus();
            return false;
        }

        // La fecha de mantenimiento es automática, no requiere validación

        return true;
    }

    private Integer parseInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private LocalDate parseFecha(String s) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(s, formatter);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductView view = new ProductView();
            view.setVisible(true);
        });
    }
}

