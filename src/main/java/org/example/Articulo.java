package org.example;
// nota: Teams del ayudante: Emilio Ramos Montesino.

import java.lang.annotation.Inherited;

public class Articulo {
    private float peso;
    private String nombre;
    private String descripcion;
    private float precio;

    /**
     * Metodo constructor de la clase Articulo.
     * @param pes Peso del articulo.
     * @param nom Nombre del articulo.
     * @param des Descripcion del articulo.
     * @param pre Precio del articulo.
     */
    public Articulo(float pes, String nom, String des, float pre){
            this.peso = pes;
            this.nombre = nom;
            this.descripcion = des;
            this.precio = pre;
    }

    /*Metodos getter y setter de Articulo*/

    public void setPeso(float p){this.peso = p;}
    public float getPeso(){return this.peso;}
    public void setNombre(String n){this.nombre = n;}
    public String getNombre(){return this.nombre;}
    public void setDescripcion(String d){this.descripcion = d;}
    public String getDescripcion(){return this.descripcion;}
    public void setPrecio(float p){this.precio = p;}
    public float getPrecio(){return this.precio;}

    /*Metodo to String*/

    public String toString(){
        return ("Nombre: " + this.nombre + ", Descripcion: " + this.descripcion
        + ", Precio: " + this.precio + ", Peso: " + this.peso);
    }
}

