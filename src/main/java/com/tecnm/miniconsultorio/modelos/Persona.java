package com.tecnm.miniconsultorio.modelos;

public class Persona {
    private String id;
    private String nombre;
    private int edad;
    
    public Persona(){}
    
    public Persona(String id, String nombre, int edad){
        this.edad = edad;
        this.nombre = nombre;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
}
