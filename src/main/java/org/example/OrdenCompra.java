package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
// nota: Teams del ayudante: Emilio Ramos Montesino.

public class OrdenCompra {
    private Date fecha;
    private String estado;
    private Cliente cliente;
    private DocTributario docTributario;
    private ArrayList<DetalleOrden> arrayDetalle;
    private ArrayList<Pago> arrayPagos;

    /**
     * Constructor de OrdenCompra. Por defecto la orden se emite en la fecha actual.
     * Algunos detalles de la implementacion:
     * <p>
     * - Se decidio incluir a Cliente como uno de los parametros, pues no parecia tener mucho sentido hacer una orden al aire,
     * a nuestro parecer.
     * <p>
     * - Mismo razonamiento para Articulo y su cantidad.
     * <p>
     * - No hay un metodo para realizar pagos en OrdenCompra, pues se requiere un objeto OrdenCompra como argumento de Pago para instanciar un
     * objeto de la clase Efectivo/Transferencia/Tarjeta. Esto significa que los pagos se realizan automaticamente al crear un
     * nuevo objeto del tipo Efectivo/Transferencia/Tarjeta. ¿Tiene sentido hacer un pago sin destinatario?
     *
     * @param c Cliente que crea la orden.
     * @param a Artículo que se desea comprar. Para crear la orden se puede elegir solo uno, para agregar más
     *            articulos se deben usar los metodos de OrdenCompra.
     * @param u Número de articulos del mismo tipo que se desea comprar.
     * @param docType Tipo de documento tributario que se desea. Se definen dos constantes públicas en la clase
     *               DocTributario: BOLETA = 0, FACTURA = 1.
     */

    public OrdenCompra(Cliente c, Articulo a, int u, int docType){
        this.fecha = new Date();
        this.cliente = c;
        // Para generar numeros aleatorios
        Random rand = new Random();
        int randnumber = rand.nextInt(1000);
        switch (docType) {
            case DocTributario.BOLETA -> this.docTributario = new Boleta(String.valueOf(randnumber), c.getRut(), c.getDirCliente());
            case DocTributario.FACTURA -> this.docTributario = new Factura(String.valueOf(randnumber), c.getRut(), c.getDirCliente());
            default -> {
            }
        }

        this.estado = "PENDIENTE";
        this.arrayPagos = new ArrayList<>();
        this.arrayDetalle = new ArrayList<>();
        this.arrayDetalle.add(new DetalleOrden(a, u));
    }

    /*Metodos getter y setter*/

    public Date getFecha() {return this.fecha;}
    public void setFecha(Date nuevaFecha) {this.fecha = nuevaFecha;}
    public String getEstado() {return this.estado;}
    public void setEstado(String s) {this.estado = s;}
    public Cliente getCliente() {return this.cliente;}
    public void setCliente(Cliente nuevoCliente) {this.cliente = nuevoCliente;}
    public DocTributario getDocTributario() {return docTributario;}
    public void setDocTributario(DocTributario docTributario) {this.docTributario = docTributario;}
    public ArrayList<DetalleOrden> getArrayDetalle() {return arrayDetalle;}
    public void setArrayDetalle(ArrayList<DetalleOrden> arrayDetalle) {this.arrayDetalle = arrayDetalle;}
    public ArrayList<Pago> getArrayPagos() {return arrayPagos;}
    public void setArrayPagos(ArrayList<Pago> arrayPagos) {this.arrayPagos = arrayPagos;}

    /**
     * nuevoArticulo se utiliza cuando se desean agregar articulos adicionales a la misma orden.
     * @param art Articulo nuevo a agregar a la orden
     * @param num Unidades del articulo nuevo
     */
    public void nuevoArticulo(Articulo art, int num) {
        arrayDetalle.add(new DetalleOrden(art, num));
    }

    /**
     * realizarPago agrega pagos que pueden realizarse progresivamente hasta pagar la orden.
     * @param nuevoPago Pago sobre la orden
     */
    public void realizarPago(Pago nuevoPago) {
        // Verificamos si la orden esta pagada y si el pago es distinto de 0
        if (this.estado.equals("FINALIZADO")) {
            System.out.println("Orden finalizada. No se aceptan mas pagos.");
        }
        else if (nuevoPago.getMonto() == 0 || arrayPagos.contains(nuevoPago)) {
            System.out.println("El pago: " + nuevoPago + "\n no es valido. Verifique que el monto sea positivo o que no se haya reutilizando un pago ya hecho.");
        }
        else {
            // Solo una vez que el pago ingresado cumple los dos checks anteriores (la orden no ha finalizado, Y, el pago no es ni cero ni repetido) entonces es posible que el estado de la orden cambie.
            arrayPagos.add(nuevoPago);
            nuevoPago.setOrdenCompra(this);

            if (this.SumaPagos() >= this.calcPrecio()){
                this.estado = "FINALIZADO";
            }
            else{
                this.estado = "PAGO PARCIAL";
            }
        }
    }

    /**
     *  Metodos de cálculo de precio: Estos calculan el precio de LA ORDEN COMPLETA, se debe acceder a los metodos
     *  calcPrecio de los detalles individuales si se desea saber el precio incluido en estos.
     *  */

    public float calcPrecio(){
        float num = (float)0;
        for (DetalleOrden detalleOrden : arrayDetalle) {
            num = num + detalleOrden.calcPrecio();
        }
        return num;
    }
    public float calcPrecioSinIVA() {
        float num = (float)0;
        for (DetalleOrden detalleOrden : arrayDetalle) {
            num = num + detalleOrden.calcPrecioSinIVA();
        }
        return num;
    }
    public float calcIVA() {
        float num = (float)0;
        for (DetalleOrden detalleOrden : arrayDetalle) {
            num = num + detalleOrden.calcIVA();
        }
        return num;
    }
    public float calcPeso() {
        float num = (float)0;
        for (DetalleOrden detalleOrden : arrayDetalle) {
            num = num + detalleOrden.calcPeso();
        }
        return num;
    }

    /**
     * Calcula cual es el monto total pagado hasta el momento
     */
    public float SumaPagos(){
        float num = 0;
        for (Pago pago : arrayPagos) {
            num = num + pago.getMonto();
        }
        return num;
    }

    /* toString de OrdenCompra*/

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.getArrayDetalle().size(); i++) {
            s.append(this.getArrayDetalle().get(i).getArticulo().getNombre());
            s.append(" (").append(this.getArrayDetalle().get(i).getCantidad()).append(" unidades), ");
        }
        if (this.getArrayDetalle().size() == 0) {
            s.append("(Sin productos)");
        }
        return ("Orden: " + s +
                "Estado: " + this.estado +
                ", Fecha: " + this.fecha +
                ", Cliente: " + this.cliente.getNombre() +
                ", Monto a pagar: " + this.calcPrecio() +
                ", Monto pagado: " + this.SumaPagos());
    }

}

