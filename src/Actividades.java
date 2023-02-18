import com.ejemplo.*;
import com.sun.xml.bind.util.Which;
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

        String  sql = "FROM OrdersEntity";
        Query q = session.createQuery(sql);
        List<OrdersEntity> listaPedidos = q.list();
        ejercicio1(listaPedidos);
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

    /*
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
*/

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
            CategoriesEntity categoria = pedido.getCategoriesByCategoryId();
            System.out.println(pedido.getProductName() + ", " + categoria.getCategoryName());
        }
    }

    //Por cada pedido mostrar los productos que contiene, así como los nombres de categoría
    //de cada producto y el nombre y apellidos del empleado que atendió el pedido. Por consola
    //debe mostrarse:
    //
    //ID de pedido, Nombre del empleado, Apellido del empleado, Nombre de producto, Nombre de categoría

    private static void actividad07(Session session) {
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
            System.out.println("Id de pedido: " + pedido.getOrderId() + ", nombre de empleado: " + pedido.getOrdersByOrderId().getEmployeesByEmployeeId().getFirstName() + ", apellido empleado: " + pedido.getOrdersByOrderId().getEmployeesByEmployeeId().getLastName() + ", nombre del producto: " + pedido.getProductsByProductId().getProductName() + ", nombre de categoría: " + pedido.getProductsByProductId().getCategoriesByCategoryId().getCategoryName());
        }
    }

    private static void actividad09(Session session) {
        String sql = "FROM OrderdetailsEntity ";
        Query q = session.createQuery(sql);
        List<OrderdetailsEntity> listaDetalles = q.list();
        Iterator<OrderdetailsEntity> iteradorDetalles = listaDetalles.iterator(); // con listIterator podemos volver a un item anterior solo con listas

        while (iteradorDetalles.hasNext()) {
            OrderdetailsEntity detalle = iteradorDetalles.next();
            int idPedido = detalle.getOrderId();
            System.out.printf("ID de pedido: %d", idPedido);
            System.out.println();
            detalle.getProductsByProductId();
        }

    }

    /*
Por cada pedido mostrar los productos que contiene, así como los nombres de categoría
de cada producto y el nombre y apellidos del empleado que atendió el pedido. Por consola
debe mostrarse:

ID de pedido, Nombre del empleado, Apellido del empleado, Nombre de producto, Nombre de categoría
*/
    private static void ejemploAsociaciones(Session session) {
        int count = 0;
        String sql = "FROM OrdersEntity";
        Query q = session.createQuery(sql); // HQL, usamos el nombre de la entidad no de la tabla
        List<OrdersEntity> listaPedidos = q.list();
        Iterator<OrdersEntity> itPedidos = listaPedidos.iterator();
        while (itPedidos.hasNext()) {
// 1. Recuperamos el pedido
            OrdersEntity ped = itPedidos.next();
// 2. Recuperamos el empleado
            EmployeesEntity emp = ped.getEmployeesByEmployeeId();
// 3. Recuperamos los detalles del pedido
            Collection<OrderdetailsEntity> detalles = ped.getOrderdetailsByOrderId();
            Iterator<OrderdetailsEntity> itDetalles = detalles.iterator();

            while (itDetalles.hasNext()) {
// 3.1 Por cada detalle de pedido recuperamos el producto asociado
                OrderdetailsEntity det = itDetalles.next();
                ProductsEntity prod = det.getProductsByProductId();
// 3.2 Por cada producto recuperamos la categoria
                CategoriesEntity cat = prod.getCategoriesByCategoryId();
// 4. Imprimimos
                System.out.printf("OrderId: %d, LastName: %s, FirstName: %s, ProductName: %s, CategoryName: %s\n",
                        ped.getOrderId(), emp.getLastName(), emp.getFirstName(), prod.getProductName(), cat.getCategoryName());
                count++;
            }
        }
        System.out.println(count);
    }

    private static void actividad0(Session session) {
        String sql = "FROM OrderdetailsEntity ";
        Query q = session.createQuery(sql); // HQL, usamos el nombre de la entidad no de la tabla
        List<OrderdetailsEntity> listaProductos = q.list();
        int count = 0;
        Iterator<OrderdetailsEntity> it = listaProductos.iterator();

        while (it.hasNext()) {
            OrderdetailsEntity pedido = (OrderdetailsEntity) it.next();
            OrdersEntity order = pedido.getOrdersByOrderId();
            EmployeesEntity empleado = order.getEmployeesByEmployeeId();
            ProductsEntity producto = pedido.getProductsByProductId();
            CategoriesEntity categoria = producto.getCategoriesByCategoryId();
            System.out.println("Id de pedido: " + pedido.getOrderId() + ", nombre de empleado: " + empleado.getFirstName() + ", apellido empleado: " + empleado.getLastName() + ", nombre del producto: " + producto.getProductName() + ", nombre de categoría: " + categoria.getCategoryName());
            count++;
        }
        System.out.println(count);
    }

    /* Obtener el nombre del cliente, nombre del proveedor,
    nombre del empleado y el nombre de los productos que estan en la orden 10794 */

    private static void actividad10(Session session) {
        String sql = "from CustomersEntity ";
        Query q = session.createQuery(sql);
        List<CustomersEntity> listaCLientes = q.list();
        Iterator<CustomersEntity> customersEntityIterator = listaCLientes.iterator();

        while (customersEntityIterator.hasNext()) {
            CustomersEntity cliente = customersEntityIterator.next(); // nombre cliente
            Collection<OrdersEntity> or = cliente.getOrdersByCustomerId();
            Iterator<OrdersEntity> itPo = or.iterator();
            while (itPo.hasNext()) {
                OrdersEntity ti = itPo.next();
                EmployeesEntity emp = ti.getEmployeesByEmployeeId(); // nombre empleado
                Collection<OrderdetailsEntity> ordet = ti.getOrderdetailsByOrderId();
                Iterator<OrderdetailsEntity> itOr = ordet.iterator();
                while (itPo.hasNext()) {
                    OrderdetailsEntity oden = itOr.next();
                    ProductsEntity pr = oden.getProductsByProductId();
                    SuppliersEntity su = pr.getSuppliersBySupplierId(); // nombre del proveedor

                    if (ti.getOrderId() == 10794) {
                        System.out.println(cliente.getContactName() + su.getContactName() + pr.getProductName());
                    }
                }
            }
        }

    }

    //Mostrar el id de la orden, fecha, código del producto, precio, código del empleado y su
    // nombre completo
    private static void actividad11(Session session) {
        String sql = "from OrdersEntity ";
        Query q = session.createQuery(sql);

        Collection<OrdersEntity> ordersEntityCollection = q.list();
        Iterator<OrdersEntity> ordersEntityIterator = ordersEntityCollection.iterator();

        while (ordersEntityIterator.hasNext()) {
            OrdersEntity ordersEntity = ordersEntityIterator.next(); // id de la orden fecha
            EmployeesEntity employeesEntity = ordersEntity.getEmployeesByEmployeeId();// codigo empleado nobre completo
            Collection<OrderdetailsEntity> orderdetailsEntity = ordersEntity.getOrderdetailsByOrderId();
            Iterator<OrderdetailsEntity> orderdetailsEntityIterator = orderdetailsEntity.iterator();
            while (orderdetailsEntityIterator.hasNext()) {
                OrderdetailsEntity orderdetailsEntity1 = orderdetailsEntityIterator.next();
                ProductsEntity productsEntity = orderdetailsEntity1.getProductsByProductId();// codigo producto yprecio

                System.out.println(ordersEntity.getOrderId() + "" + ordersEntity.getOrderDate() + productsEntity.getProductId() + productsEntity.getUnitPrice() + employeesEntity.getEmployeeId() + employeesEntity.getFirstName() + employeesEntity.getLastName());
            }
        }

    }

    /*
    Por cada producto de la lista, imprimir el id de producto y nombre de producto
junto con el nombre de compañia del proveedor. Realizar el
metodo de la forma mas eficiente posible, sin usar HQL.

 System.out.printf("OrderId: %d, LastName: %s, FirstName: %s, ProductName: %s, CategoryName: %s\n",
                        ped.getOrderId(), emp.getLastName(), emp.getFirstName(), prod.getProductName(), cat.getCategoryName());
     */
    public static void ejercicios1(Session session) {
        String sql = "FROM ProductsEntity";
        Query q = session.createQuery(sql);
        List<ProductsEntity> listaProductos = q.list();

        Iterator<ProductsEntity> productsEntityIterator = listaProductos.iterator();
        while (productsEntityIterator.hasNext()) {
            ProductsEntity productsEntity = productsEntityIterator.next(); //nombre producto idproducto
            SuppliersEntity suppliersEntity = productsEntity.getSuppliersBySupplierId(); //nombrecompañia
            System.out.println("Nombre del producto: " + productsEntity.getProductName() + " ,id prodructo: " +
                    productsEntity.getProductId() + ", nombre compañia: " + suppliersEntity.getCompanyName());
        }
    }


    /*
EJERCICIO 2

Por cada pedido de la lista, imprimir el id de pedido, nombre del empleado que
atendio el pedido y nombre del cliente que hizo el pedido. Realizar el metodo
de la forma mas eficiente posible, sin usar HQL.
*/

    public static void ejercicio1(List listaPedidos) {
        Iterator<OrdersEntity> ordersEntityIterator = listaPedidos.iterator();

        while (ordersEntityIterator.hasNext()) {
            OrdersEntity ordersEntity = ordersEntityIterator.next(); //id del pedido
            EmployeesEntity employeesEntity = ordersEntity.getEmployeesByEmployeeId(); // nombre del empleado
            CustomersEntity customersEntity = ordersEntity.getCustomersByCustomerId(); // nombre cliente
            System.out.println("Id del pedido: " + ordersEntity.getOrderId() + ", nombre del empleado: " + employeesEntity.getFirstName() + " " + employeesEntity.getLastName() + " ,nombre del cliente: " + customersEntity.getContactName());
        }

    }

    /*
EJERCICIO 3

Por cada empleado de la lista, imprimir el nombre y apellido de dicho empleado,
el nombre y apellido del empleado al que le tiene que presentar sus informes,
y el nombre y apellido de todos los empleados que le presentan sus informes.
Realizar el metodo de la forma mas eficiente posible, sin usar HQL.
*/
    public static void ejercicio03(Session session) {

        String sql = "FROM EmployeesEntity";
        Query q = session.createQuery(sql);
        List<EmployeesEntity> listaEmpleado = q.list();
        Iterator<EmployeesEntity> employeesEntityIterator = listaEmpleado.iterator();

        while (employeesEntityIterator.hasNext()) {
            EmployeesEntity employeesEntity = employeesEntityIterator.next(); //nombre y apellido empleado
            Iterator<EmployeesEntity> employeesEntityIterator2 = listaEmpleado.iterator();
            while (employeesEntityIterator2.hasNext()) {
                EmployeesEntity employeesEntity2 = employeesEntityIterator2.next(); //nombre y apellido empleado
                if (employeesEntity2.getReportsTo() == employeesEntity.getEmployeeId()) {
                    EmployeesEntity employeesEntity3 = employeesEntityIterator.next(); //nombre y apellido empleado

                    Iterator<EmployeesEntity> employeesEntityIterator3 = listaEmpleado.iterator();
                    while (employeesEntityIterator3.hasNext()) {
                        EmployeesEntity employeesEntity4 = employeesEntityIterator2.next(); //nombre y apellido empleado
                        if (employeesEntity2.getReportsTo() == employeesEntity.getEmployeeId()) {
                            System.out.println("Nombre y apellido de empleado: " + employeesEntity2.getFirstName() + " " +
                                    employeesEntity2.getLastName() + " ,nombre y apellidos al que le tiene que reportar el informe: " +
                                    employeesEntity.getFirstName() + " " + employeesEntity.getLastName() + employeesEntity4.getFirstName());
                        }
                    }
                }
            }
        }

    }


}
