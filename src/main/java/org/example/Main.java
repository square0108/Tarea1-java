package org.example;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
// nota: Teams del ayudante: Emilio Ramos Montesino.

public class Main {
    public static void main(String[] args) throws ParseException {
        // Ojo con el throws ParseException, esta por el commando parse
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        // Generamos fechas
        Date date1 = formatter.parse("10-10-2033");
        Date date2 = formatter.parse("05-03-1997");
        Date date3 = formatter.parse("28-08-1983");
        Date date4 = formatter.parse("11-09-1614");
        System.out.println(date1);
        System.out.println(date2);
        // Primero Generamos distintos articulos
        Articulo queso = new Articulo((float)40,"Queso","Es amarillo, sabroso y apestoso.",(float)100);
        Articulo jamon = new Articulo((float)35,"Jamon","Hecho de una cantidad indeterminada de cerdos.",(float)150);
        Articulo pan = new Articulo((float)60,"Pan","una marraqueta son solo dos dientes.",(float)50);
        Articulo cocacola = new Articulo((float)1000,"Coca-cola","Dulce, muy dulce... es mejor el agua.",(float)1200);
        Articulo item = new Articulo((float)42,"Item sospechoso","Sientes el impulso de comprarlo, pero no sabes si es lo correcto.",(float)9999);
        System.out.println(jamon);
        System.out.println(item);
        //Generamos Direcciones
        Direccion direccion1 = new Direccion("Avenida Siempreviva, 742");
        Direccion direccion2 = new Direccion("Callejon Diagon");
        System.out.println(direccion1);
        // Ahora Generamos clientes
        Cliente cliente1 = new Cliente("Homero Simpson", "10456354-2", direccion1);
        Cliente cliente2 = new Cliente("Harry Potter", "209774345-5", direccion2);
        System.out.println(cliente1);

        //---------Primera Prueba---------
        System.out.println("\n---------Primera Prueba---------");
        OrdenCompra ordenCompra1 = new OrdenCompra(cliente1,pan,10,0);
        System.out.println(ordenCompra1);
        ordenCompra1.nuevoArticulo(jamon,15);
        System.out.println(ordenCompra1);
        Transferencia transferencia1 = new Transferencia((float)1000, date1, ordenCompra1,"Banco Estado", "12345");
        System.out.println(transferencia1);
        System.out.println(ordenCompra1);
        Efectivo efectivo1 = new Efectivo((float)1755, date2, ordenCompra1);
        System.out.println(efectivo1);
        System.out.println(ordenCompra1);
        System.out.println("Cantidad de vuelto: " + efectivo1.calcDevolucion());
        Efectivo efectivo2 = new Efectivo((float)340, date2, ordenCompra1);
        System.out.println("Cantidad de vuelto: " + efectivo2.calcDevolucion() + " (Ya que no se ha usado el dinero)");

        //---------Segunda Prueba---------
        System.out.println("\n---------Segunda Prueba---------");
        OrdenCompra ordenCompra2 = new OrdenCompra(cliente2,item,0,1);
        System.out.println(ordenCompra2);
        ordenCompra2.nuevoArticulo(cocacola,3);
        System.out.println(ordenCompra2);
        ordenCompra2.nuevoArticulo(queso,3);
        ordenCompra2.nuevoArticulo(item,2);
        System.out.println(ordenCompra2);
        System.out.println(ordenCompra2.getDocTributario());
        Tarjeta tarjeta1 = new Tarjeta((float)1000, date3, ordenCompra2, "Debito", "126643");
        System.out.println(tarjeta1);
        System.out.println(ordenCompra2);
        Transferencia transferencia2 = new Transferencia((float)1000, date1, ordenCompra2,"Banco Estado", "12345");
        System.out.println(ordenCompra2);
        Efectivo efectivo3 = new Efectivo((float)21880, date4, ordenCompra2);
        System.out.println(efectivo3);
        System.out.println(ordenCompra2);
        ordenCompra2.realizarPago(efectivo3);
        System.out.println("Cantidad de vuelto: " + efectivo3.calcDevolucion());
        Efectivo efectivo4 = new Efectivo((float)100, date4, ordenCompra2);
        System.out.println(ordenCompra2);
        System.out.println("Cantidad de vuelto: " + efectivo3.calcDevolucion());
        System.out.println("Cantidad de vuelto: " + efectivo4.calcDevolucion());

        //---------Tercera Prueba---------
        System.out.println("\n---------Tercera Prueba---------");
        OrdenCompra ordenCompra3 = new OrdenCompra(cliente2,cocacola,3,1);
        System.out.println(ordenCompra3);
        ordenCompra3.nuevoArticulo(item,1);
        System.out.println(ordenCompra3);
        // OJO: en la siguiente linea estamos pagando en ordenCompra2, no ordenCompra3
        Transferencia transferencia3 = new Transferencia((float)1000, date4, ordenCompra2,"Banco Estado", "12345");
        ordenCompra3.realizarPago(transferencia3);
        System.out.println(ordenCompra3);
        Efectivo efectivo5 = new Efectivo((float)13000, date1, ordenCompra3);
        System.out.println(ordenCompra3);
        System.out.println(efectivo5);



    }
}

class Cliente {
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

class Direccion {
    /* Según el diagrama UML, es posible que varios clientes tengan la misma dirección.
    /* ¿Será necesario que apunten a la misma referencia?
    /* La respuesta es Si, lo he confirmado con el profesor Geoffrey. ¡Muy bien hecho!*/
    private String direccion = null;
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
        return ("Direccion: " + this.direccion );
    }

    /*Puede que esto sea innecessario, pero me guie por el PPT que describe las Cardinalidades de UML, asi que Direccion
    * ahora tiene un ArrayList con todos los clientes que poseen esta direccion. */
    @Deprecated
    public String stringDeClientes(ArrayList<Cliente> ar) {
        /*Esta funcion es solo para concatenar los nombres de los clientes con la misma direccion, dentro de toString().
        * lo pense un poco mas, y quizas no tiene sentido poner esto en toString(). Dejare la funcion por mientras*/
        if (arrayClientes.size() == 0) return null;
        else {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < ar.size(); i++) {
                s.append(ar.get(i).getNombre());
                if (i < ar.size()-1) s.append(", ");
            }
            return s.toString();
        }
    }

    public void addCliente(Cliente nuevoCliente) {
        arrayClientes.add(nuevoCliente);
    }

}

abstract class DocTributario {
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

abstract class Pago {
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

class OrdenCompra {
    /* Estas constantes definen los distintos estados que puede tener una orden, solo se utilizan internamente para cambiar de estado. */

    private Date fecha;
    private String estado;
    private Cliente cliente;
    private DocTributario docTributario;
    private ArrayList<DetalleOrden> arrayDetalle;
    private ArrayList<Pago> arrayPagos;

    /**
     * Constructor de OrdenCompra. Por defecto la orden se emite en la fecha actual.
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


    /****************************/

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
     * TODO: Verificar si intentar realizar un pago nuevo sobre una orden completa (todos los detalles pagados) debe tirar una exception?
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
            // aqui esto asociando los dos
            // Solo una vez que el pago ingresado pasa los dos checks anteriores (la orden no ha finalizado, Y, el pago no es ni cero ni repetido) entonces es posible que el estado de la orden cambie.
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
        return ("Estado: " + this.estado + ", Fecha: " + this.fecha +", Cliente: " +this.cliente.getNombre() +
                ", Monto a pagar: " + this.calcPrecio() + ", Monto pagado: " + this.SumaPagos());
    }

}

class DetalleOrden {
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

    /*¿Dado que el precio puede ser un float (valor decimal), consideramos todos los return type como floats...?*/
    /* todo: ¿El metodo calcIVA() calcula el IVA de la compra total, o de una sola unidad?*/
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

class Articulo {
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
class InvalidInputException extends Exception{
    public InvalidInputException(String errorMessage){
        super(errorMessage);
    }
}

