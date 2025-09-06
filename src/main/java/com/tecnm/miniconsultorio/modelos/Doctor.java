/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecnm.miniconsultorio.modelos;

/**
 *
 * @author JULIAN
 */
public class Doctor extends Persona{
    
    String cedula;
    String universidadDeEgreso;
    int anosdeExperiencia;
    
    public Doctor(String cedula, String universidadDeEgreso, int anosDeExperiencia) {
    super();
    this.cedula = cedula;
    this.universidadDeEgreso = universidadDeEgreso;
    this.anosdeExperiencia = anosDeExperiencia;
}

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getUniversidadDeEgreso() {
        return universidadDeEgreso;
    }

    public void setUniversidadDeEgreso(String universidadDeEgreso) {
        this.universidadDeEgreso = universidadDeEgreso;
    }

    public int getAnosdeExperiencia() {
        return anosdeExperiencia;
    }

    public void setAnosdeExperiencia(int anosdeExperiencia) {
        this.anosdeExperiencia = anosdeExperiencia;
    }
    
    
  
}
