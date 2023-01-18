import com.ejemplo.OrderdetailsEntity;
import com.ejemplo.OrdersEntity;
import com.ejemplo.ProductsEntity;
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
        //actividad03(session);
        //actividad04(session);
        //actividad05(session);
        //actividad06(session);
        //actividad07(session);
        actividad08(session);
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
    public static void actividad03(Session session) {

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

    public static void actividad04(Session session) {
        // 4 Repetimos el anterior pero de una forma distinta
        System.out.println("********************************");
        System.out.println("EJEMPLO MULTI COLUMNA CON LISTAS");
        String hql = "select new List(shipCountry, shipCity, count(*)) from OrdersEntity group by shipCity, shipCountry";
        Query q = session.createQuery(hql);
        List<List<Object>> listaBis = q.list();
        System.out.println("Numero de filas: " + listaBis.size());
        for (int i = 0; i < listaBis.size(); i++) {
            List<Object> fila = listaBis.get(i);
            System.out.println(fila.get(0) + "," + fila.get(1) + "," + fila.get(2));
        }
    }


    //por cada producto obtener el nombre de la cotegoria a la que pertenece por sentencia

    public static void actividad05(Session session) {
        String hql = "select p.productName, c.categoryName from ProductsEntity p INNER JOIN CategoriesEntity c on p.categoryId = c.categoryId";
        Query q = session.createQuery(hql);
        List<Object[]> list = q.list();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(Arrays.toString(list.get(i)));
        }

    }

    // por cada producto obtener el nombre de la cotegoria a la que pertenece por objetos
    private static void actividad06(Session session) {
        String sql = "FROM ProductsEntity ";
        Query q = session.createQuery(sql); // HQL, usamos el nombre de la entidad no de la tabla
        List<ProductsEntity> listaProductos = q.list();

        Iterator<ProductsEntity> it = listaProductos.iterator();

        while (it.hasNext()) {
            ProductsEntity pedido = (ProductsEntity) it.next();
            System.out.println(pedido.getProductName() + ", " + pedido.getCategoriesByCategoryId().getCategoryName());
        }
    }

    //Por cada pedido mostrar los productos que contiene, así como los nombres de categoría
    //de cada producto y el nombre y apellidos del empleado que atendió el pedido. Por consola
    //debe mostrarse:
    //
    //ID de pedido, Nombre del empleado, Apellido del empleado, Nombre de producto, Nombre de categoría

    private static void actividad07(Session session){
        String sql = "select od.orderId, p.productName, c.categoryName, e.firstName, e.lastName from OrderdetailsEntity od inner join ProductsEntity p on od.productId = p.productId inner join CategoriesEntity c on p.categoryId = c.categoryId inner join OrdersEntity o on od.orderId=o.orderId inner join EmployeesEntity e on o.employeeId=e.employeeId ";
        Query q = session.createQuery(sql); // HQL, usamos el nombre de la entidad no de la tabla
        List<Object[]> list = q.list();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(Arrays.toString(list.get(i)));
        }
    }

    private static void actividad08(Session session) {
        String sql = "FROM OrderdetailsEntity ";
        Query q = session.createQuery(sql); // HQL, usamos el nombre de la entidad no de la tabla
        List<OrderdetailsEntity> listaProductos = q.list();

        Iterator<OrderdetailsEntity> it = listaProductos.iterator();

        while (it.hasNext()) {
            OrderdetailsEntity pedido = (OrderdetailsEntity) it.next();
            System.out.println("Id de pedido: " + pedido.getOrderId() + ", nombre de empleado: " + pedido.getOrdersByOrderId().getEmployeesByEmployeeId().getFirstName() + ", apellido empleado: " +pedido.getOrdersByOrderId().getEmployeesByEmployeeId().getLastName() + ", nombre del producto: " + pedido.getProductsByProductId().getProductName() + ", nombre de categoría: " + pedido.getProductsByProductId().getCategoriesByCategoryId().getCategoryName());
        }
    }


}
