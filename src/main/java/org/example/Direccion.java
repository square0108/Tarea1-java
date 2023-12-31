package org.example;
import java.util.ArrayList;
// nota: Teams del ayudante: Emilio Ramos Montesino.

public class Direccion {
    private String direccion;
    private ArrayList<Cliente> arrayClientes;
    /**
     * Constructor de la clase Dirección.
     * @param d Dirección (String).
     */
    public Direccion(String d) {
        this.direccion = d;
        arrayClientes = new ArrayList<>();
    }
    /*Metodos getter y setter*/
    public String getDir() {return direccion;}
    public void setDir(String inputString) {this.direccion = inputString;}

    /*Metodo toString*/
    public String toString() {
        return ("Direccion: " + this.direccion);
    }

    /**
     * Agrega un nuevo Cliente a la lista interna de clientes que poseen esta misma direccion.
     * @param nuevoCliente Referencia del cliente nuevo a agregar.
     */
    public void addCliente(Cliente nuevoCliente) {
        arrayClientes.add(nuevoCliente);
    }

}

