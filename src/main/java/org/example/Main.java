package org.example;
// nota: Teams del ayudante: Emilio Ramos Montesino.

public class Main {
    public static void main(String[] args) {

        System.out.println("Yo soy el verdadero Guillermo Oliva");

    }
}

class Cliente {
    private final String nombre;
    private final String rut;
    private Direccion dirCliente;

    public Cliente(String n, String r, Direccion dirObj) {
        this.nombre = n;
        this.rut = r;
        dirCliente = dirObj;
    }


}

class Direccion { /*Según el diagrama UML, es posible que varios clientes tengan la misma dirección.
                  /*¿Será necesario que apunten a la misma referencia?  */
    private String direccion = null;

    public Direccion() {}
    public String dirGet() {return direccion;}
    public void dirSet(String inputString) {this.direccion = inputString;}

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
