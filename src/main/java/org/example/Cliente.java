package org.example;
// nota: Teams del ayudante: Emilio Ramos Montesino.

public class Cliente {
    private String nombre;
    private String rut;
    private Direccion dirCliente;

    /**
     * Constructor de la clase Cliente.
     * @param n Nombre del cliente.
     * @param r Rut asociado al cliente.
     * @param d Dirección asociada al cliente.
     */
    public Cliente(String n, String r, Direccion d) {
        this.nombre = n;
        this.rut = r;
        this.dirCliente = d;
        dirCliente.addCliente(this);
        /* Si varios clientes tienen la misma direccion,
         * entonces sus propiedades dirCliente tienen la misma referencia
         * (no son instancias nuevas de Direccion)*/
    }
    /*Metodos getter y setter para las propiedades de Cliente */

    public void setNombre(String str) {nombre = str;}
    public String getNombre() {return nombre;}
    public void setRut(String str) {rut = str;}
    public String getRut() {return rut;}
    public void setDirCliente(Direccion dir) {dirCliente = dir;}
    public Direccion getDirCliente() {return dirCliente;}
    /* Metodo toString que a su vez utiliza el toString de Direccion */
    public String toString() {
        /*Aqui falta el toString de dircliente?? -> this.dirCliente.toString() ¿?

        // si. Me agile*/
        return ("Nombre: " + this.getNombre() + ", RUT: " + this.getRut() + ", " + this.dirCliente.toString());
    }
}

