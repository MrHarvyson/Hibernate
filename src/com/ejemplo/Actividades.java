package com.ejemplo;

import org.hibernate.cfg.Configuration;
import org.hibernate.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Actividades {

    public static void main(String[] args) {
        //esta parte podría esta en un metodo aparte
        SessionFactory instancia = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = instancia.openSession();
        //actividad01(session);
        //actividad02(session);
        actividad03(session);
    }


    /*
Mediante hibernate, crear una lista con todos los pedidos atendidos por un empleado con ID par.
Imprimir la lista (debe reimplementar el método toString)
*/
    public static void actividad01(Session session) {

        Query q = session.createQuery("from OrdersEntity where mod(employeeId,2)=0");
        List<OrdersEntity> lista = q.list();

        System.out.println(lista.size());

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }

    }

    /*
    Query q = session.createQuery("from OrdersEntity");
        List<OrdersEntity> lista = q.list();

        System.out.println(lista.size());

        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getEmployeeId()%2==0){

            }
            System.out.println(lista.get(i));
        }
     */

    /*
    Mediante hibernate, contar todos los productos de un pedido en particular (elija el que desee). La consulta
    debe devolver un único valor (el número) que es lo que se imprimirá por pantalla.
     */
    public static void actividad02(Session session) {

        Query q = session.createQuery("select count(*) from OrderdetailsEntity as or where orderId = 10248 ");

        System.out.println(q.getSingleResult());

    }

    /*
    Por cada país y ciudad de envío, mostrar el total de pedidos.
     */
    public static void actividad03(Session session){

        Query q = session.createQuery("select shipCountry, shipCity, count(*)  from OrdersEntity group by shipCity,shipCountry");

        List<Object[]> list = q.list();

/*
for (Object[] arr: list) {
            System.out.println(arr[0] + ", " + arr[1] + ", " + arr[2]);
        }
 */
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Arrays.toString(list.get(i)));
        }

    }

    public void actividad04(Session session){
        // 4 Repetimos el anterior pero de una forma distinta
        System.out.println("********************************");
        System.out.println("EJEMPLO MULTI COLUMNA CON LISTAS");
        String hql = "select new List(shipCountry, shipCity, count(*)) from OrdersEntity group by shipCity, shipCountry";
        Query q = session.createQuery(hql);
        List<List<Object>> listaBis = q.list();
        System.out.println("Numero de filas: " + listaBis.size());
        for(int i=0; i< listaBis.size(); i++) {
            List<Object> fila = listaBis.get(i);
            System.out.println(fila.get(0) + "," + fila.get(1) + "," + fila.get(2));
        }
    }



}
