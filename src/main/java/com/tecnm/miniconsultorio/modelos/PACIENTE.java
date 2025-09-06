package com.tecnm.miniconsultorio.modelos;

import java.util.List;

public class Paciente extends Persona{
    
       double altura;
       int peso;
       float temperatura;
       List<Enfermedad> enfermedades;


      
        public Paciente (String nombre,String id, int edad, double altura,int peso,float temperatura,String enfermedades){
            super(id,nombre,edad);
            this.altura = altura;
            this.peso = peso;
            this.temperatura = temperatura;
            this.enfermedades = enfermedades;
             
        }  
        
       public double getaltura(){
           return altura;
       }
       public int getpeso(){
           return peso;
       }
       public float gettemperatura(){
           return temperatura;
       }
       public List<String> getenfermedades(){
           return enfermedades;
       }
       
       public void setaltura(double altura){
           this.altura = altura;
       }
       public void setpeso(int peso){
           this.peso = peso;
       }
       public void settemperatura(float temperatura){
           this.temperatura = temperatura;
       }
        public void settenfermedades(List<String> enfermedades){
           this.enfermedades = enfermedades;
       }
       
        
        public void mostarinformacion(){
            
            System.out.println("Informacion del paciente");
            System.out.println("Su altura es de: "+altura);
            System.out.println("Su peso es de: "+peso);
            System.out.println("Su temperatura es de: "+temperatura);
            System.out.println("Sus emfermedades son las siguintes: "+enfermedades);
          
        }
  }
  
