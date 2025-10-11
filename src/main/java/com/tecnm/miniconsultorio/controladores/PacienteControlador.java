package com.tecnm.miniconsultorio.controladores;

import com.tecnm.miniconsultorio.modelos.Paciente;
import java.util.ArrayList;

public class PacienteControlador {
    private final ArrayList<Paciente> pacientes = new ArrayList<>();

    // Método para crear paciente con validación
    public String crearPaciente(Paciente datosPaciente) {
        if (datosPaciente == null) {
            return "El paciente no puede ser nulo.";
        }
        if (datosPaciente.getNombre() == null || datosPaciente.getNombre().isBlank()) {
            return "El nombre del paciente es obligatorio.";
        }
        if (datosPaciente.getEdad() <= 0) {
            return "La edad debe ser mayor a 0.";
        }
        if (datosPaciente.getaltura() <= 0) {
            return "La altura debe ser un valor positivo.";
        }
        if (datosPaciente.getpeso() <= 0) {
            return "El peso debe ser un valor positivo.";
        }
        if (datosPaciente.gettemperatura() < 30 || datosPaciente.gettemperatura() > 45) {
            return "La temperatura ingresada no es válida.";
        }

        // Validar que no se repita el ID
        for (Paciente p : pacientes) {
            if (p.getId().equals(datosPaciente.getId())) {
                return "Ya existe un paciente con ese ID.";
            }
        }

        // Validación de enfermedades
        if (datosPaciente.getenfermedades() != null) {
            for (var enf : datosPaciente.getenfermedades()) {
                if (enf.gettipo() == null || enf.gettipo().isBlank()) {
                    return "Cada enfermedad debe tener un tipo.";
                }
                if (enf.getmedicamento() == null || enf.getmedicamento().isBlank()) {
                    return "Cada enfermedad debe tener medicamento.";
                }
            }
        }

        pacientes.add(datosPaciente);
        return "Paciente agregado correctamente: " + datosPaciente.getNombre();
    }

    
    public Paciente traerPacientePorId(String id) {
        for (Paciente p : pacientes) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null; // si no se encuentra
    }

    public ArrayList<Paciente> traerTodosPacientes() {
        return pacientes;
    }
    
    public String actualizarPaciente(String id, Paciente datosActualizados) {
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente p = pacientes.get(i);
            if (p.getId().equals(id)) {
                if (datosActualizados.getNombre() == null || datosActualizados.getNombre().isBlank()) {
                    return "El nombre del paciente es obligatorio.";
                }
                if (datosActualizados.getEdad() <= 0) {
                    return "La edad debe ser mayor a 0.";
                }
                if (datosActualizados.getaltura() <= 0) {
                    return "La altura debe ser un valor positivo.";
                }
                if (datosActualizados.getpeso() <= 0) {
                    return "El peso debe ser un valor positivo.";
                }
                if (datosActualizados.gettemperatura() < 30 || datosActualizados.gettemperatura() > 45) {
                    return "La temperatura ingresada no es válida.";
                }

                // Reemplazar
                pacientes.set(i, datosActualizados);
                return "Paciente actualizado correctamente.";
            }
        }
        return "Paciente con ID " + id + " no encontrado.";
    }

    public String eliminarPaciente(String id) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getId().equals(id)) {
                pacientes.remove(i);
                return "Paciente eliminado correctamente.";
            }
        }
        return " Paciente con ID " + id + " no encontrado.";
    }
}
