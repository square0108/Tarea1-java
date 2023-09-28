package org.example;
// nota: Teams del ayudante: Emilio Ramos Montesino.

public class Main {
    public static void main(String[] args) {

        System.out.println("Yo soy el verdadero Mike Tyson");

    }
}

class Cliente {
    private String nombre;
    private String rut;
    private Direccion dirCliente;

    public Cliente(String n, String r, Direccion d) {
        this.nombre = n;
        this.rut = r;
        this.dirCliente = d;
        /* Si varios clientes tienen la misma direccion,
         * entonces sus propiedades dirCliente tienen la misma referencia
         * (no son instancias nuevas de Direccion)*/
    }
    /* Metodos getter y setter para las propiedades de Cliente */
    public void setNombre(String str) {nombre = str;}
    public String getNombre() {return nombre;}
    public void setRut(String str) {rut = str;}
    public String getRut() {return rut;}
    public void setDirCliente(Direccion dir) {dirCliente = dir;}
    public Direccion getDirCliente() {return dirCliente;}
    /* Metodo toString que a su vez utiliza el toString de Direccion */
    public String toString() {
        return (this.getNombre() + ", RUT: " + this.getRut() + ", Direccion: " + this.dirCliente);
    }
}

class Direccion {
    /* Según el diagrama UML, es posible que varios clientes tengan la misma dirección.
    /* ¿Será necesario que apunten a la misma referencia?
    /* La respuesta es Si, lo he confirmado con el profesor Geoffrey.*/
    private String direccion = null;

    public Direccion() {}
    public String getDir() {return direccion;}
    public void setDir(String inputString) {this.direccion = inputString;}

}

class DocTributario {
    /*Dos tipos de Documentos Tributarios: Boleta, Factura*/
}

class Boleta extends DocTributario {}

class Factura extends DocTributario {}

class Pago {
    /*Pago se divide en tres tipos de Pago: Efectivo, Transferencia, y Tarjeta*/
}

class Efectivo extends Pago {}

class Transferencia extends Pago {}

class Tarjeta extends Pago {}

class OrdenCompra {}

class DetalleOrden {}

class Articulo {}
