package org.example;
import java.util.Date;
import java.util.ArrayList;
// nota: Teams del ayudante: Emilio Ramos Montesino.

public class Main {
    public static void main(String[] args) {

        System.out.println("Yo soy el verdadero Martin Llanos");
        Direccion d1 = new Direccion("Arboledas Verdes 420");
        Cliente cordero = new Cliente("Pepe","21.165.368-K",d1);
        Cliente llama = new Cliente("Seth","22.22.3.4.5",d1);
        System.out.println(d1.toString());
        System.out.println(cordero.toString());
        System.out.println(llama.toString());

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
        dirCliente.addCliente(this);
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

    /* *Metodos getter y setter**/

    public Direccion(String d) {
        this.direccion = d;
        arrayClientes = new ArrayList<>();
    }
    public String getDir() {return direccion;}
    public void setDir(String inputString) {this.direccion = inputString;}

    /* **************************/

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

class DocTributario {
    /*Dos tipos de Documentos Tributarios: Boleta, Factura*/
    private String numero;
    private String rut;
    private Date fecha;
    private Direccion dirDoc;
    /*En el enunciado del programa dice que el documento tributario debe tener una dirección, esta no aparece
    en el diagrama UML. ¿Se habra estara malo el UML o tengo mala comprensión lectora?*/

    // Es un "error" del UML, pasa lo mismo con la agregación Cliente-Dirección.
    // El profesor me aceptó que incluyese una variable privada tipo Dirección dentro de las propiedades de Cliente,
    // asi que supongo que ocurre lo mismo en este caso.
    public DocTributario(String n, String r, Date f, Direccion d){
        this.numero = n;
        this.rut = r;
        this.fecha = f;
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
    public Boleta(String n, String r, Date f, Direccion d){
        super(n,r,f,d);
    }
    public String toString(){
        return ("Boleta. " + super.toString());
    }
}

class Factura extends DocTributario {
    public Factura(String n, String r, Date f, Direccion d){
        super(n,r,f,d);
    }
    public String toString(){
        return ("Factura. " + super.toString());
    }
}

class Pago {
    /*Pago se divide en tres tipos de Pago: Efectivo, Transferencia, y Tarjeta*/
    private float monto;
    private Date fecha;
    public Pago(float m, Date f){
        this.monto = m;
        this.fecha = f;
    }
    /* *Metodos getter y setter**/

    public void setFecha(Date fech){this.fecha = fech;}
    public Date getFecha(){return this.fecha;}
    public void setMonto(float m){this.monto = m;}
    public float getMonto(){return this.monto;}

    /* **************************/

    public String toString(){
        return ("Pago. Monto: " + this.getMonto() + ", Fecha: " + this.getFecha().toString());
    }
}

class Efectivo extends Pago {
    /*¿El pago en efectivo es exacto, o tenemos monedas de 10,50,100,500?
    ¿es exacto el vuelto de 782,3?. Claro que si es en efectivo deberia estar con las cantidades
    de dinero correctas...*/

    // Le enviaré mensaje al profe sobre esto
    public Efectivo(float m, Date f){
        super(m,f);
    }
    /*Claramente hay que modificar este metodo, es solo para probar*/
    public float calcDevolucion(float pago){return pago - super.getMonto(); }
    public String toString(){
        /*quizas podria causar problemas porque monto no es string¿?, VERIFICAR,*/

        // Al concatenarlo en una string no debería haber problema
        return ("Pago en Efectivo. Monto: " + this.getMonto() + ", Fecha: " + this.getFecha().toString());
    }
}

class Transferencia extends Pago {
    private String banco;
    private String numCuenta;
    public Transferencia(float m, Date f,String b, String numC){
        super(m,f);
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
    public Tarjeta(float m, Date f, String t, String numT){
        super(m,f);
        this.tipo = t;
        this.numTransaccion = numT;
    }
    public String toString(){
        return ("Pago con Tarjeta de" + this.tipo +". Monto: " + this.getMonto() + ", Fecha: "
                + this.getFecha().toString() + ", Numero de transaccion: " + this.numTransaccion);
    }
}

class OrdenCompra {
    // Tengo varias confusiones aqui. Se supone que el documento tributario asociado a una orden de compra se emite al mismo tiempo (misma fecha entre ambos), y es solo uno por cada orden?
    // Una agregacion tiene permitido crear objetos con new?
    private Date fecha;
    private String estado;
    private Cliente comprador;
    private DocTributario documento;
    private ArrayList<Pago> arrayPagos;
    public OrdenCompra(Date f, Cliente c){
        this.fecha = f;
        this.comprador = c;
        this.documento = new DocTributario(comprador.getNombre(),comprador.getRut(),this.fecha,comprador.getDirCliente());
        // No tengo claro que funcion cumple Estado en el problema.
        this.estado = null;
        this.arrayPagos = new ArrayList<>();
    }

    /* **Metodos getter y setter**/

    /* ***************************/

    public void addPago(Pago nuevoPago) {
        arrayPagos.add(nuevoPago);
    }

}

class DetalleOrden {}

class Articulo {
    private float peso;
    private String nombre;
    private String descripcion;
    private float precio;
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
        return ("Nombre: " + this.nombre + "\nDescripcion: " + this.descripcion
        + "\nPrecio: " + this.precio + "\nPeso: " + this.peso);
    }
}
