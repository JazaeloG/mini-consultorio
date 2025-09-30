/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecnm.miniconsultorio.modelos;

/**
 *
 * @author JULIAN
 */
public class LogicaDeEnfermera  {
    public class Enfermera {
    private String id;
    private String nombre;
    private int edad;
    private String especialidad;
    private String turno;
    
 
    public Enfermera(String id, String nombre, int edad, String especialidad, String turno) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.especialidad = especialidad;
        this.turno = turno;
        
    }
  public void registrarSignosVitales(Paciente paciente, String signos) {
        System.out.println(nombre + " registró signos vitales de " + paciente.getNombre() + ": " + signos);
    }

    public void administrarMedicamento(Paciente paciente, String medicamento) {
        System.out.println(nombre + " administró " + medicamento + " al paciente " + paciente.getNombre());
    }

    public void reportarNota(String nota) {
        System.out.println("Nota de " + nombre + ": " + nota);
    }

    public void cambiarTurno(String nuevoTurno) {
        this.turno = nuevoTurno;
        System.out.println(nombre + " ahora tiene turno: " + turno);
    }

    public void registrarEntrada() {
        System.out.println(nombre + " registró entrada.");
    }

    public void registrarSalida() {
        System.out.println(nombre + " registró salida.");
    }

    public void actualizarEspecialidad(String nuevaEspecialidad) {
        this.especialidad = nuevaEspecialidad;
        System.out.println(nombre + " ahora tiene especialidad en: " + especialidad);
    }

    

    public String getNombre() {
        return nombre;
    }
    }

}