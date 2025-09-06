/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecnm.miniconsultorio.modelos;

import java.util.List;

/**
 *
 * @author rodas
 */
public class Enfermera extends Persona{
    
  private int numeroEnfermera;
   private String areaAtencion;
   private List<Paciente> pacientesAsignados;

    public int getNumeroEnfermera() {
        return numeroEnfermera;
    }

    public void setNumeroEnfermera(int numeroEnfermera) {
        this.numeroEnfermera = numeroEnfermera;
    }

    public String getAreaAtencion() {
        return areaAtencion;
    }

    public void setAreaAtencion(String areaAtencion) {
        this.areaAtencion = areaAtencion;
    }

    public List<Paciente> getPacientesAsignados() {
        return pacientesAsignados;
    }

    public void setPacientesAsignados(List<Paciente> pacientesAsignados) {
        this.pacientesAsignados = pacientesAsignados;
    }

    public Enfermera() {
    }

    public Enfermera(String id, String nombre, int edad) {
        super(id, nombre, edad);
    }
   
   
}
