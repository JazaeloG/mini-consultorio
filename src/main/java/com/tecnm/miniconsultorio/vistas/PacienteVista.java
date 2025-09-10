package com.tecnm.miniconsultorio.vistas;

import com.tecnm.miniconsultorio.controladores.PacienteControlador;
import com.tecnm.miniconsultorio.modelos.Enfermedad;
import com.tecnm.miniconsultorio.modelos.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PacienteVista extends JFrame {
    PacienteControlador controlador;
    private ArrayList<Enfermedad> enfermedadesTemp = new ArrayList<>();
    

    private JTextField txtId, txtNombre, txtEdad, txtAltura, txtPeso, txtTemperatura;
    private JButton btnCrear;
    private JButton btnAgregarEnfermedad;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public PacienteVista(PacienteControlador controlador) {
        this.controlador = controlador;
        setTitle("Gestión de Pacientes - MiniConsultorio");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Campos
        txtId = new JTextField(15);
        txtNombre = new JTextField(15);
        txtEdad = new JTextField(15);
        txtAltura = new JTextField(15);
        txtPeso = new JTextField(15);
        txtTemperatura = new JTextField(15);

        btnCrear = new JButton("Crear Paciente");
        btnAgregarEnfermedad = new JButton("Agregar Enfermedad");

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Registrar Paciente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelForm.add(txtId, gbc);

        // Nombre
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panelForm.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelForm.add(txtNombre, gbc);

        // Edad
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        panelForm.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelForm.add(txtEdad, gbc);

        // Altura
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        panelForm.add(new JLabel("Altura (m):"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelForm.add(txtAltura, gbc);

        // Peso
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        panelForm.add(new JLabel("Peso (kg):"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelForm.add(txtPeso, gbc);

        // Temperatura
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        panelForm.add(new JLabel("Temperatura (°C):"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelForm.add(txtTemperatura, gbc);

        // Botón Agregar Enfermedad
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelForm.add(btnAgregarEnfermedad, gbc);

        // Botón Crear Paciente
        gbc.gridy = 7;
        panelForm.add(btnCrear, gbc);

        add(panelForm, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Edad", "Altura", "Peso", "Temp", "Editar", "Eliminar"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6 || column == 7;
            }

            @Override
            public void setValueAt(Object value, int row, int column) {
                if (column < 6) {
                    super.setValueAt(value, row, column);
                }
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(30);

        tabla.getColumn("Editar").setCellRenderer(new BotonRenderer("✏️"));
        tabla.getColumn("Eliminar").setCellRenderer(new BotonRenderer("🗑️"));

        tabla.getColumn("Editar").setCellEditor(new BotonEditor(new JCheckBox(), this, true));
        tabla.getColumn("Eliminar").setCellEditor(new BotonEditor(new JCheckBox(), this, false));

        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // Eventos
        btnCrear.addActionListener(e -> crearPaciente());
        btnAgregarEnfermedad.addActionListener(e -> mostrarDialogoEnfermedad());
    }

    
    private void mostrarDialogoEnfermedad() {
        JDialog dialog = new JDialog(this, "Registrar Enfermedad", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(5, 2, 5, 5));

        JTextField txtTipo = new JTextField();
        JTextField txtMedicamento = new JTextField();
        JTextField txtSintomas = new JTextField();

        dialog.add(new JLabel("Tipo:"));
        dialog.add(txtTipo);
        dialog.add(new JLabel("Medicamento:"));
        dialog.add(txtMedicamento);
        dialog.add(new JLabel("Síntomas (coma separados):"));
        dialog.add(txtSintomas);

        JButton btnGuardar = new JButton("Guardar");
        dialog.add(new JLabel(""));
        dialog.add(btnGuardar);

        btnGuardar.addActionListener(ev -> {
            java.util.List<String> listaSintomas = new ArrayList<>();
            if (!txtSintomas.getText().isBlank()) {
                for (String s : txtSintomas.getText().split(",")) {
                    listaSintomas.add(s.trim());
                }
            }

            com.tecnm.miniconsultorio.modelos.Enfermedad e = 
                new com.tecnm.miniconsultorio.modelos.Enfermedad(
                    enfermedadesTemp.size() + 1, 
                    txtTipo.getText(),
                    listaSintomas,
                    txtMedicamento.getText()
                );
            enfermedadesTemp.add(e);

            JOptionPane.showMessageDialog(dialog, "Enfermedad registrada");
            dialog.dispose();
        });

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }


    private void crearPaciente() {
        try {
            Paciente p = new Paciente(
                    txtNombre.getText(),
                    txtId.getText(),
                    Integer.parseInt(txtEdad.getText()),
                    Double.parseDouble(txtAltura.getText()),
                    Integer.parseInt(txtPeso.getText()),
                    Float.parseFloat(txtTemperatura.getText()),
                    new ArrayList<>(enfermedadesTemp) 
            );
            String mensaje = controlador.crearPaciente(p);
            JOptionPane.showMessageDialog(this, mensaje);
            limpiarFormulario();
            refrescarTabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
        }
    }


    private void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        txtAltura.setText("");
        txtPeso.setText("");
        txtTemperatura.setText("");
        enfermedadesTemp.clear();
    }


    public void refrescarTabla() {
        modeloTabla.setRowCount(0);
        controlador.traerTodosPacientes().forEach(p -> {
            modeloTabla.addRow(new Object[]{
                    p.getId(),
                    p.getNombre(),
                    p.getEdad(),
                    p.getaltura(),
                    p.getpeso(),
                    p.gettemperatura(),
                    "✏️",
                    "🗑️"
            });
        });
    }

    public void mostrarDialogoActualizar(Paciente p) {
        JDialog dialog = new JDialog(this, "Actualizar Paciente", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(7, 2, 5, 5));

        JTextField txtNombreUpd = new JTextField(p.getNombre());
        JTextField txtEdadUpd = new JTextField(String.valueOf(p.getEdad()));
        JTextField txtAlturaUpd = new JTextField(String.valueOf(p.getaltura()));
        JTextField txtPesoUpd = new JTextField(String.valueOf(p.getpeso()));
        JTextField txtTempUpd = new JTextField(String.valueOf(p.gettemperatura()));

        dialog.add(new JLabel("Nombre:"));
        dialog.add(txtNombreUpd);
        dialog.add(new JLabel("Edad:"));
        dialog.add(txtEdadUpd);
        dialog.add(new JLabel("Altura:"));
        dialog.add(txtAlturaUpd);
        dialog.add(new JLabel("Peso:"));
        dialog.add(txtPesoUpd);
        dialog.add(new JLabel("Temperatura:"));
        dialog.add(txtTempUpd);

        JButton btnActualizar = new JButton("Actualizar");
        dialog.add(new JLabel(""));
        dialog.add(btnActualizar);

        btnActualizar.addActionListener(e -> {
            Paciente actualizado = new Paciente(
                    txtNombreUpd.getText(),
                    p.getId(),
                    Integer.parseInt(txtEdadUpd.getText()),
                    Double.parseDouble(txtAlturaUpd.getText()),
                    Integer.parseInt(txtPesoUpd.getText()),
                    Float.parseFloat(txtTempUpd.getText()),
                    null
            );
            String mensaje = controlador.actualizarPaciente(p.getId(), actualizado);
            JOptionPane.showMessageDialog(dialog, mensaje);
            refrescarTabla();
            dialog.dispose();
        });

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PacienteControlador controlador = new PacienteControlador();
            PacienteVista vista = new PacienteVista(controlador);
            vista.setVisible(true);
        });
    }
}

class BotonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
    public BotonRenderer(String texto) {
        setText(texto);
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int col) {
        return this;
    }
}

class BotonEditor extends DefaultCellEditor {
    private JButton button;
    private boolean editar;
    private PacienteVista vista;
    private String id;

    public BotonEditor(JCheckBox checkBox, PacienteVista vista, boolean editar) {
        super(checkBox);
        this.vista = vista;
        this.editar = editar;
        button = new JButton();
        button.addActionListener(e -> fireEditingStopped());
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        id = (String) table.getValueAt(row, 0);
        button.setText((String) value);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (editar) {
            Paciente p = vista.controlador.traerPacientePorId(id);
            if (p != null) vista.mostrarDialogoActualizar(p);
        } else {
            String mensaje = vista.controlador.eliminarPaciente(id);
            JOptionPane.showMessageDialog(vista, mensaje);
            vista.refrescarTabla();
        }
        return "";
    }

}
