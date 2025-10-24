package com.tecnm.miniconsultorio.vistas;
import com.tecnm.miniconsultorio.modelos.DoctorCrud;
import com.tecnm.miniconsultorio.modelos.Doctor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class DoctorVista extends JFrame {

    private JTextField txtId, txtNombre, txtEdad, txtCedula, txtUniversidad, txtAnios;
    private JButton btnAgregar, btnBuscar, btnActualizar, btnEliminar, btnListar;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private DoctorCrud doctorCrud = new DoctorCrud();

    public DoctorVista() {
        setTitle("Gestión de Doctores - MiniConsultorio");
        setSize(850, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🧱 Panel superior (Formulario)
        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registrar Doctor"));

        panelFormulario.add(new JLabel("ID:"));
        txtId = new JTextField();
        panelFormulario.add(txtId);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panelFormulario.add(txtEdad);

        panelFormulario.add(new JLabel("Cédula:"));
        txtCedula = new JTextField();
        panelFormulario.add(txtCedula);

        panelFormulario.add(new JLabel("Universidad de Egreso:"));
        txtUniversidad = new JTextField();
        panelFormulario.add(txtUniversidad);

        panelFormulario.add(new JLabel("Años de Experiencia:"));
        txtAnios = new JTextField();
        panelFormulario.add(txtAnios);

        btnAgregar = new JButton("Agregar Doctor");
        btnBuscar = new JButton("Buscar Doctor");
        panelFormulario.add(btnAgregar);
        panelFormulario.add(btnBuscar);

        add(panelFormulario, BorderLayout.NORTH);

        // Tabla inferior
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre", "Edad", "Cédula", "Universidad", "Años"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // ️ Panel inferior con botones
        JPanel panelBotones = new JPanel();
        btnActualizar = new JButton("Actualizar Doctor");
        btnEliminar = new JButton("Eliminar Doctor");
        btnListar = new JButton("Listar Doctores");

        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);
        add(panelBotones, BorderLayout.SOUTH);

        //  Eventos
        btnAgregar.addActionListener(e -> agregarDoctor());
        btnBuscar.addActionListener(e -> buscarDoctor());
        btnListar.addActionListener(e -> listarDoctores());
        btnEliminar.addActionListener(e -> eliminarDoctor());
        btnActualizar.addActionListener(e -> actualizarDoctor());
    }

    //  Agregar Doctor
    private void agregarDoctor() {
        try {
            String id = txtId.getText();
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            String cedula = txtCedula.getText();
            String universidad = txtUniversidad.getText();
            int anios = Integer.parseInt(txtAnios.getText());

            Doctor doctor = new Doctor(edad, nombre, id, cedula, universidad, anios);
            doctorCrud.agregarDoctor(doctor);
            JOptionPane.showMessageDialog(this, " Doctor agregado correctamente.");
            limpiarCampos();
            listarDoctores();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "️ Error al agregar: " + ex.getMessage());
        }
    }

    //  Buscar Doctor por ID
    private void buscarDoctor() {
        String id = txtId.getText();
        Doctor doctor = doctorCrud.buscarDoctorPorId(id);
        if (doctor != null) {
            JOptionPane.showMessageDialog(this,
                    "Doctor encontrado:\n" +
                            "Nombre: " + doctor.getNombre() +
                            "\nEdad: " + doctor.getEdad() +
                            "\nCédula: " + doctor.getCedula() +
                            "\nUniversidad: " + doctor.getUniversidadDeEgreso() +
                            "\nAños: " + doctor.getAnosdeExperiencia());
        } else {
            JOptionPane.showMessageDialog(this, " No se encontró ningún doctor con ese ID.");
        }
    }

    //  Listar todos los doctores
    private void listarDoctores() {
        modeloTabla.setRowCount(0); // limpiar tabla
        for (Doctor doctor : doctorCrud.listarDoctores()) {
            modeloTabla.addRow(new Object[]{
                    doctor.getId(),
                    doctor.getNombre(),
                    doctor.getEdad(),
                    doctor.getCedula(),
                    doctor.getUniversidadDeEgreso(),
                    doctor.getAnosdeExperiencia()
            });
        }
    }

    // ️ Eliminar doctor
    private void eliminarDoctor() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            String id = modeloTabla.getValueAt(fila, 0).toString();
            doctorCrud.eliminarDoctor(id);
            JOptionPane.showMessageDialog(this, "️ Doctor eliminado correctamente.");
            listarDoctores();
        } else {
            JOptionPane.showMessageDialog(this, "️ Selecciona un doctor para eliminar.");
        }
    }

    //  Actualizar doctor
    private void actualizarDoctor() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            String id = modeloTabla.getValueAt(fila, 0).toString();
            String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:");
            String nuevaCedula = JOptionPane.showInputDialog("Nueva cédula:");
            String nuevaUniversidad = JOptionPane.showInputDialog("Nueva universidad:");
            int nuevosAnios = Integer.parseInt(JOptionPane.showInputDialog("Nuevos años de experiencia:"));

            doctorCrud.actualizarDoctor(id, nuevoNombre, nuevaCedula, nuevaUniversidad, nuevosAnios);
            JOptionPane.showMessageDialog(this, " Doctor actualizado correctamente.");
            listarDoctores();
        } else {
            JOptionPane.showMessageDialog(this, "️ Selecciona un doctor para actualizar.");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        txtCedula.setText("");
        txtUniversidad.setText("");
        txtAnios.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DoctorVista().setVisible(true));
    }
}