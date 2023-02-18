
import com.ejemplo.CategoriesEntity;
import com.ejemplo.EmployeesEntity;
import com.ejemplo.OrdersEntity;
import com.ejemplo.ProductsEntity;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;

import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Main {
    /*
    Factory --> patron de diseño --> Singleton
    Debería de existir una única SessionFactory
    */
    private static final SessionFactory ourSessionFactory;

    /*
    los bloques static se suelen usar para inicializar (a veces antes del constructor), es otra forma de inicializar
    una variable como la que tenemos arriba de SessionFactory
    hay otras formas de inicializar todo esto como sale en el libro de clase
    */
    static {
        try {
            //esta clase se utiliza para cargar el archivo de configuracion (hibernate.cfg.xml) entre otras cosas
            Configuration configuration = new Configuration();
            configuration.configure();
            //SessionFactory permite obtener instancias de Session que usaremos para crear sesiones
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    //con este metodo podremos acceder a la sesion abierta desde cualquier parte de nuestro programa
    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    //parecido al ejericio de base de datos los metadata
    public static void main(final String[] args) {
        //accedemos a la sesion, no hacemos un login porque el archivo conf ya nos lo hace
        final Session session = getSession();

        //printAllEntities(session);
        //ejecutarQuery(session);
        //ejecutarGet(session);
        //ejecutarLoad(session);
        //media(session);
        //ejecutarIterate(session);
        //ejecutarLoad(session);
        //numEmpleado(session);
        //ejecutarUniqueResult(session);
        //ejecutarConsultasParametros(session);
        //ejercicio(session);
        //session.close();
        //ejemploJoinEmpleadosPedidos(session);

    }

    public static void printAllEntities(Session session) {
        try {
            //recuperamos en esta instancia por cada identidad
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                //imprimimos
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    private static void ejecutarQuery(Session session) {

        // HQL, usamos el nombre de la entidad no de la tabla
        Query q = session.createQuery("from CategoriesEntity");
        List<CategoriesEntity> lista = q.list(); //List<CategoriesEntity> lista = q.getResultList() --> otra forma de hacer
        // Obtenemos un Iterador y recorremos la lista.
        Iterator<CategoriesEntity> iter = lista.iterator();
        System.out.println("Número de registros: " + lista.size());

        while (iter.hasNext()) {
            CategoriesEntity cat = iter.next();
            System.out.println("CategoryName: " + cat.getCategoryName());
            System.out.println("CategoryId: " + cat.getCategoryId());
            System.out.println("CategoryDescription: " + cat.getDescription());
        }
/*
        //otra forma de recorrer
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getCategoryName());
            System.out.println(lista.get(i).getCategoryId());
            System.out.println(lista.get(i).getDescription());
        }
*/
        session.close();
    }

    //tenenemos dos tipos get y load

    private static void ejecutarGet(Session session) {

        //selecionamos a un solo datos de la tabla de empleados el 1
        // da null si no existe por lo que es una buena practica crear una condicion por si se da el caso

        EmployeesEntity emp = session.get(EmployeesEntity.class, 1);
        if (emp != null) {
            System.out.println("FirstName: " + emp.getFirstName());
            System.out.println("LastName: " + emp.getLastName());
            System.out.println("City: " + emp.getCity());
            System.out.println("Country: " + emp.getCountry());
            System.out.println("Birthdate: " + emp.getBirthDate());
        } else {
            System.out.println("no existe este dato");
        }
    }

    private static void ejecutarLoad(Session session) {

        // da una excepcion si no existe
        EmployeesEntity emp = session.load(EmployeesEntity.class, 1);
        System.out.println("FirstName: " + emp.getFirstName());
        System.out.println("LastName: " + emp.getLastName());
        System.out.println("City: " + emp.getCity());
        System.out.println("Country: " + emp.getCountry());
        System.out.println("Birthdate: " + emp.getBirthDate());

    }

    private static void media(Session session) {

        // HQL, usamos el nombre de la entidad no de la tabla
        Query q = session.createQuery("select AVG(unitPrice) from ProductsEntity ");
        //para un unico resultadousamos .getSingleResult y asi no tenemos que hacer un iterator
        System.out.println(q.getSingleResult());
    }

    private static double media2(Session session) {

        Query q = session.createQuery("select AVG(unitPrice) from ProductsEntity ");
        return (Double) q.getSingleResult();
    }

    private static void ejecutarTransacciones(Session session) {

        // para una modificación de una forma segura --> transacion
        Transaction tx = session.beginTransaction();
        EmployeesEntity emp = new EmployeesEntity();
        emp.setFirstName("Jose");
        emp.setLastName("Gamez");
        emp.setEmployeeId(107);
        // Parar aqui y comprobar que en la base de datos no existe el nuevo empleado
        System.out.println("Haciendo persistente a Jose Gamez");
        // queda guardado pero no reflejado en la base de datos hasta que le demos a commits
        session.save(emp);
        //como la entidad no es nueva no hace falta hacer el save
        emp.setFirstName("Pepe"); // Esto se verá reflejado
        // Parar aqui y comprobar que en la base de datos no existe el nuevo empleado
        tx.commit(); // Si no hacemos commit, no se guardan nunca los cambios
        System.out.println("Commit hecho");
        session.close();

        // ¿Como podemos añadir un constructor para no tener que usar los set?
    }

    private static void ejecutarIterate(Session session) {

        Query q = session.createQuery("from CategoriesEntity");
        q.setFetchSize(3); // --> parte nueva: medida de optimizacion --> le dice al servidor que los pide de 3 en 3
        Iterator iter = q.iterate();
        while (iter.hasNext()) {
            //extraer el objeto
            CategoriesEntity cat = (CategoriesEntity) iter.next();
            System.out.println("Category info: " + cat.getCategoryId() + "," + cat.getCategoryName());
        }
        session.close();
    }

    //devolver todos los empleados
    private static void numEmpleado(Session session) {

        Query q = session.createQuery("select count(*) from EmployeesEntity ");

        Long cantidad = (Long) q.getSingleResult();

        for (int i = 1; i < cantidad; i++) {
            EmployeesEntity emp = (EmployeesEntity) session.load(EmployeesEntity.class, i);
            System.out.println(emp);
        }
    }

    //uniqueResult parecido a SingleResult
    // si la query vuelve mas de un resultado devuelve una excepcion a diferencia del singleresult que solo muestra el primer resultado
    private static void ejecutarUniqueResult(Session session) {
        // Cuand sabemos que un query devuelve un solo resultado, podemos usar unique result y hacer
        // un casting a la clase esperada.
        String hql = "from CategoriesEntity as cat where cat.categoryId = 1";
        Query q = session.createQuery(hql);
        CategoriesEntity cat = (CategoriesEntity) q.uniqueResult();
        System.out.println("Category: " + cat.getCategoryId() + "," + cat.getCategoryName());

        hql = "select avg(cat.categoryId) from CategoriesEntity as cat";
        Query cons = session.createQuery(hql);
        Double suma = (Double) cons.uniqueResult();
        System.out.println("Average: " + suma);
        session.close();
    }

    // Parametros

    private static void ejecutarConsultasParametros(Session session) {
        // selecionar un empleado del que sepamos el id
        String hql = "from EmployeesEntity where employeeId = :numemple";
        Query q = session.createQuery(hql);
        q.setParameter("numemple", 1);
        EmployeesEntity emple = (EmployeesEntity) q.uniqueResult();
        System.out.println("Employee: " + emple.getEmployeeId() + "," + emple.getLastName());
        System.out.println("");

        // Empleados cuyo número de departamento sea 10 y el oficio DIRECTOR

        Query q2 = session.createQuery("from EmployeesEntity where employeeId > :numemple and title = :ofi");
        q2.setParameter("numemple", 1);
        q2.setParameter("ofi", "Sales Representative");
        List<EmployeesEntity> lista = q2.list();
        Iterator<EmployeesEntity> iter = lista.iterator();
        while (iter.hasNext()) {
            EmployeesEntity emp = (EmployeesEntity) iter.next();
            System.out.println("Employee: " + emp.getEmployeeId() + "," + emp.getLastName() + "," + emp.getTitle());
        }
        System.out.println("");

        //lo mismo que el anterior pero con una lista
        List<Integer> numeros = new ArrayList<Integer>();
        numeros.add(1);
        numeros.add(3);
        numeros.add(5);
        String hql5 = "from EmployeesEntity emp where emp.employeeId in (:lista)";
        Query q5 = session.createQuery(hql5);
        q5.setParameterList("lista", numeros);
        List<EmployeesEntity> lista4 = q5.list();
        Iterator<EmployeesEntity> iter4 = lista4.iterator();
        while (iter4.hasNext()) {
            EmployeesEntity emp = (EmployeesEntity) iter4.next();
            System.out.println("Employee: " + emp.getEmployeeId() + "," + emp.getLastName());
        }

        session.close();
    }

    //Mediante HQL, obtener todos los pedidos hechos por el empleado King, Robert. almacenarlos
    // en una lista e imprimirla por pantalla, extraer solo id de pedidos

    private static void ejercicio(Session session) {
        //como no me acuerdo del join hago la consulta en dos partes
        Query n = session.createQuery("select employeeId from EmployeesEntity where firstName='King' and lastName='Robert'");
        EmployeesEntity cat = (EmployeesEntity) n.uniqueResult();
        String nom = String.valueOf(cat);
        Query q = session.createQuery("select orderId from OrdersEntity where" + nom + "");
        List<OrdersEntity> lista = q.list();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    private static void ejemploJoinEmpleadosPedidos(Session session) {
        String sql = "FROM EmployeesEntity";
        Query q = session.createQuery(sql); // HQL, usamos el nombre de la entidad no de la tabla
        List<EmployeesEntity> listaEmpleados = q.list();
        System.out.println("Numero de empleados: " + listaEmpleados.size());
        System.out.println("****************");
        System.out.println("Empleado cero:");
        System.out.println("****************");
        EmployeesEntity emp0 = listaEmpleados.get(0);
        System.out.println(emp0);
        System.out.println();
        Collection pedidosEmp0 = emp0.getOrdersByEmployeeId();
        Iterator it = pedidosEmp0.iterator();
        System.out.println("*******");
        System.out.println("Pedidos");
        System.out.println("*******");
        while(it.hasNext()) {
            OrdersEntity pedido = (OrdersEntity) it.next();
            System.out.println(pedido);
        }
    }

    /*
    Por cada producto de la lista, imprimir el id de producto y nombre de producto
junto con el nombre de compañia del proveedor. Realizar el
metodo de la forma mas eficiente posible, sin usar HQL.
     */


}