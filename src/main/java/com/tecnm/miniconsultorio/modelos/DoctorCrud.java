package com.tecnm.miniconsultorio.modelos;
import java.util.ArrayList;
import java.util.List;

public class DoctorCrud {

    private List<Doctor> doctores = new ArrayList<>();

    //metodo para agregar un doctor (Create)
    //
    public void agregarDoctor(Doctor doctor) {
        doctores.add(doctor);           //agrego el objeto doctor para la lista de doctores
        System.out.println("El doctor se agregado correctamente : " + doctor.getNombre());
    }

    //metodo para buscar un doctor por id

    public Doctor buscarDoctorPorId(String id) { // comienza el recorrido para ver si hay un doctor
        for (Doctor doctor : doctores) {
            if (doctor.getId().equals(id)) { // comparamos el id
                return doctor; // si se encuntra se devuelve ese objeto y si no se encuentra el retur null
            }
        }
        return null;
    }

    // este nos ayuda a leer todos los dactores que se encyentra agregados

    public List<Doctor> listarDoctores() {
        return doctores;     //se rrecorre toda la lista de doctores
    }

    //metodo para actualizar los doctores por su id

    public void actualizarDoctor(String id, String nuevoNombre, String nuevaCedula,
                                 String nuevaUniversidad, int nuevosAnios) {
        for (Doctor doctor : doctores) {
            if (doctor.getId().equals(id)) {
                doctor.setNombre(nuevoNombre);
                doctor.setCedula(nuevaCedula);
                doctor.setUniversidadDeEgreso(nuevaUniversidad);
                doctor.setAnosdeExperiencia(nuevosAnios);
                System.out.println("Doctor actualizado correctamente : " + nuevoNombre);
            }
        }
    }

    // DELETE - Eliminar doctor por ID
    public void eliminarDoctor(String id) {
        for (Doctor doctor : doctores) {
            if (doctor.getId().equals(id)) {
                doctores.remove(doctor);
                System.out.println("🗑️ Doctor eliminado: " + doctor.getNombre());
            }
        }
    }

}
