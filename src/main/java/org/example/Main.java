package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
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

