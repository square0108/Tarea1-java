package org.example;
// nota: Teams del ayudante: Emilio Ramos Montesino.

public class DetalleOrden {
    private Articulo articulo;
    private int cantidad;
    /**
     * Metodo constructor de detalle de orden
     * @param art Articulo a comprar
     * @param unidades cantidad de articulos a comprar.
     */
    public DetalleOrden(Articulo art, int unidades) {
        this.articulo = art;
        this.cantidad = unidades;
    }

    /*Metodos getter y setter*/

    public Articulo getArticulo() {return this.articulo;}
    public void setArticulo(Articulo nuevoArticulo) {this.articulo = nuevoArticulo;}
    public int getCantidad() {return this.cantidad;}
    public void setCantidad(int nuevaCantidad) {this.cantidad = nuevaCantidad;}

    /****************************/

    public float calcPrecio() {return cantidad*articulo.getPrecio();}
    public float calcPrecioSinIVA() {
        /*El IVA en Chile es 19%, el cual viene incluido en el precio de cada articulo*/
        return (float) ((cantidad*articulo.getPrecio())/1.19);
    }
    public float calcIVA() {return calcPrecio()-calcPrecioSinIVA();}
    public float calcPeso() {return (cantidad*articulo.getPeso());}
    @Override
    public String toString() {
        return (articulo.toString() + ", Unidades: " + this.cantidad);
    }
}

