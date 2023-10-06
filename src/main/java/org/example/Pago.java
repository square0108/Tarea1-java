package org.example;
import java.util.Date;
// nota: Teams del ayudante: Emilio Ramos Montesino.

public abstract class Pago {
    /*Pago se divide en tres tipos de Pago: Efectivo, Transferencia, y Tarjeta*/
    private float monto;
    private Date fecha;
    private OrdenCompra orden;
    /**
     * Constructor de la clase Pago.
     * @param m Monto del pago.
     * @param f Fecha del pago.
     * @param o Orden de compra en el que se realiza el pago.
     */
    public Pago(float m, Date f, OrdenCompra o){

        this.monto = m;
        this.fecha = f;
        this.orden = o;
        this.orden.realizarPago(this);
    }
    /*Metodos getter y setter de Pago*/
    public void setFecha(Date fech){this.fecha = fech;}
    public Date getFecha(){return this.fecha;}
    public void setMonto(float m){this.monto = m;}
    public float getMonto(){return this.monto;}
    public void setOrdenCompra(OrdenCompra ordencompra){
        this.orden = ordencompra;
    }
    public OrdenCompra getOrdenCompra(){
        return this.orden;
    }

    /***************************/
    public String toString(){
        return ("Pago. Monto: " + this.getMonto() + ", Fecha: " + this.getFecha().toString());
    }
}

class Efectivo extends Pago {
    /*¿El pago en efectivo es exacto, o tenemos monedas de 10,50,100,500?
    ¿es exacto el vuelto de 782,3?. Claro que si es en efectivo deberia estar con las cantidades
    de dinero correctas...*/

    /**
     * Constructor de la clase Efectivo.
     * @param m Monto del pago en efectivo.
     * @param f Fecha del pago en efectivo.
     * @param o Orden de compra en el que se realiza el pago en efectivo.
     */
    public Efectivo(float m, Date f, OrdenCompra o){
        super(m,f,o);
    }
    public int calcDevolucion(){
        int ultimoIndex = super.getOrdenCompra().getArrayPagos().size()-1;

        // Si es que el ultimo pago fue en efectivo y la orden esta pagada
        if (ultimoIndex < 0) return 0; // en caso de array vacio
        else if (this == super.getOrdenCompra().getArrayPagos().get(ultimoIndex) && super.getOrdenCompra().getEstado().equals("FINALIZADO")){
            return  Math.round(getOrdenCompra().SumaPagos() - this.getOrdenCompra().calcPrecio());
        }
        else{
            return 0;
        }
    }
    public String toString(){
        return ("Pago en Efectivo. Monto: " + this.getMonto() + ", Fecha: " + this.getFecha().toString());
    }
}

class Transferencia extends Pago {
    private String banco;
    private String numCuenta;
    /**
     * Constructor de la clase Transferencia.
     * @param m Monto de la tranferencia.
     * @param f Fecha de la transferencia.
     * @param o Orden de compra en la que se realizara la transferencia.
     * @param b Nombre del banco desde donde se hace la transferencia.
     * @param numC Numero de cuenta.
     */
    public Transferencia(float m, Date f,OrdenCompra o,String b, String numC){
        super(m,f,o);
        this.banco = b;
        this.numCuenta= numC;
    }
    public String toString(){
        return ("Pago con Transferencia. Monto: " + this.getMonto() + ", Fecha: " + this.getFecha().toString()
                + ", Banco: " + this.banco + ", Numero de Cuenta: " + numCuenta);
    }
}

class Tarjeta extends Pago {
    private String tipo;
    private String numTransaccion;
    /**
     * Constructor de la clase Tarjeta.
     * @param m Monto del pago hecho con la tarjeta.
     * @param f Fecha del pago hecho con la tarjeta.
     * @param o Orden de compra en donde se hace el pago.
     * @param t Tipo de tarjeta (Debito o Credito).
     * @param numT Numero de transaccion.
     */
    public Tarjeta(float m, Date f,OrdenCompra o, String t, String numT){
        super(m,f,o);
        this.tipo = t;
        this.numTransaccion = numT;
    }
    public String toString(){
        return ("Pago con Tarjeta de " + this.tipo +". Monto: " + this.getMonto() + ", Fecha: "
                + this.getFecha().toString() + ", Numero de transaccion: " + this.numTransaccion);
    }
}
