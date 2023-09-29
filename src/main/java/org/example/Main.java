package org.example;
import java.util.Date;
// nota: Teams del ayudante: Emilio Ramos Montesino.

public class Main {
    public static void main(String[] args) {

        System.out.println("Yo soy el verdadero Martin Llanos");

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
    /* La respuesta es Si, lo he confirmado con el profesor Geoffrey. ¡Muy bien hecho!*/
    private String direccion = null;

    public Direccion() {}
    public String getDir() {return direccion;}
    public void setDir(String inputString) {this.direccion = inputString;}

}

class DocTributario {
    /*Dos tipos de Documentos Tributarios: Boleta, Factura*/
    private String numero;
    private String rut;
    private Date fecha;
    /*En el enunciado del programa dice que el documento tributario debe tener una dirección, esta no aparece
    en el diagrama UML. ¿Se habra estara malo el UML o tengo mala comprensión lectora?*/
    public DocTributario(String n, String r, Date f){
        this.numero = n;
        this.rut = r;
        this.fecha = f;
    }
    /*Metodos getter y setter de DocTributario*/
    public void setNumero(String num){this.numero = num;}
    public String getNumero(){return this.numero;}
    public void  setRut(String rt){this.rut = rt;}
    public String getRut(){return this.rut;}
    public void setFecha(Date fech){this.fecha = fech;}
    public Date getFecha(){return this.fecha;}

}

class Boleta extends DocTributario {
    public Boleta(String n, String r, Date f){
        super(n,r,f);
    }
}

class Factura extends DocTributario {
    public Factura(String n, String r, Date f){
        super(n,r,f);
    }
}

class Pago {
    /*Pago se divide en tres tipos de Pago: Efectivo, Transferencia, y Tarjeta*/
}

class Efectivo extends Pago {}

class Transferencia extends Pago {}

class Tarjeta extends Pago {}

class OrdenCompra {}

class DetalleOrden {}

class Articulo {}
