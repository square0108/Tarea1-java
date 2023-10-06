package org.example;
import java.util.Date;
// nota: Teams del ayudante: Emilio Ramos Montesino.

public abstract class DocTributario {
    /*Dos tipos de Documentos Tributarios: Boleta, Factura*/
    public static final int BOLETA = 0, FACTURA = 1;

    private String numero;
    private String rut;
    private Date fecha;
    private Direccion dirDoc;
    /*En el enunciado del programa dice que el documento tributario debe tener una dirección, esta no aparece
    en el diagrama UML. ¿Se habra estara malo el UML o tengo mala comprensión lectora?*/

    // Es un "error" del UML, pasa lo mismo con la agregación Cliente-Dirección.
    // El profesor me aceptó que incluyese una variable privada tipo Dirección dentro de las propiedades de Cliente,
    // asi que supongo que ocurre lo mismo en este caso.

    /**
     * Constructor de la clase abstracta DocTributario.
     * @param n Numero del documento tributario.
     * @param r Rut asociado al documento tributario.
     * @param d Dirección asociada al documento tributario.
     */
    public DocTributario(String n, String r, Direccion d){
        this.numero = n;
        this.rut = r;
        this.fecha = new Date();
        this.dirDoc = d;
    }
    /*Metodos getter y setter de DocTributario*/
    public void setNumero(String num){this.numero = num;}
    public String getNumero(){return this.numero;}
    public void  setRut(String rt){this.rut = rt;}
    public String getRut(){return this.rut;}
    public void setFecha(Date fech){this.fecha = fech;}
    public Date getFecha(){return this.fecha;}
    public void setDir(Direccion drc){this.dirDoc = drc;}
    public Direccion getDir(){return this.dirDoc;}
    public String toString(){
        return ("Numero: " + this.numero + ", RUT: " + this.rut + ", Fecha: " + this.fecha.toString() + ", " + this.dirDoc.toString());
    }
}

class Boleta extends DocTributario {
    /**
     * Constructor de la clase Boleta.
     * @param n Numero de la boleta.
     * @param r Rut asociado a la boleta.
     * @param d Dirección asociada a la boleta.
     */
    public Boleta(String n, String r, Direccion d){
        super(n,r,d);
    }
    public String toString(){
        return ("Boleta. " + super.toString());
    }
}

class Factura extends DocTributario {
    /**
     * Constructor de la clase Boleta.
     * @param n Numero de la boleta.
     * @param r Rut asociado a la boleta.
     * @param d Dirección asociada a la boleta.
     */
    public Factura(String n, String r, Direccion d){
        super(n,r,d);
    }
    public String toString(){
        return ("Factura. " + super.toString());
    }
}
