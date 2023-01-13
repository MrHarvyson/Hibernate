# Hibernate

Sitios de documentación:

+ [DOCUMENTACIÓN OFICIAL](https://hibernate.org)
+ [TUTORIALSPOINT](https://www.tutorialspoint.com/hibernate/index.htm)

Las <span style="color:#50fa7b">**CLASES**</span> que vamos a usar serán:

+ <span style="color:#ff79c6">**Session:**</span>
  + Conectar con la BBDD
  + Guardar y muestra de los objetos del mapeo

Las <span style="color:#50fa7b">**INTERFACES**</span> que usaremos serán:

+ <span style="color:#ff79c6">**SessionFactory:**</span> 
  + Permite obtener instancias **Session**. 
  + Lee el archivo de configuracion.
  + Normalmente, hay una única **SessionFactory** para toda la aplicación. Si la aplicación accede
    a varias bases de datos se necesitará una **SessionFactory** por cada base de datos.

+ <span style="color:#ff79c6">**Configuration:**</span> configura Hibernate.

+ <span style="color:#ff79c6">**Query:**</span> permite realizar consultas.

+ <span style="color:#ff79c6">**Transaction:**</span> permite realizar transaciones.

Algunos de los <span style="color:#50fa7b">**MÉTODO:**</span> que usaremos serán:

+ `save()` : se usa para guardar el objeto, le pasamos el objeto de argumento.
+ `commit()` : hace un commit de la transacción actual, la transacción se crea al método `begindTransation()` de la sesion
  actual.
+ `close()` : cerrar sesión.

> **PASOS A SEGUIR**:
> 1. Crear `SessionFactory`
> 2. Crear objeto `Session`
> 3. crear objeto `Cliente`
> 4. Ejecutar transacción SQL
>    + Comenzar transacción
>    + Guardar objeto en BBDD
>      + Commit
>      + RollBack

<br/>

Usaremos **@NOTACIONES** que es la forma más moderna y no mediante xml.

`@Entity`: transforma la clase en una entidad, es decir, en una tabla.

`@Table(name=" ")` : con esta anotación especificamos la tabla de la BBDD.

`@Column(name=" ")` : especificamos la columna de la tabla.

`@Id` : especificamos cuál es el campo clave

`@OneToOne` : especificamos que la relación va a ser 1 : 1. Ejemplo `@OneToOne(cascade=CascadeType.ALL)`

`@JoinColumn(name=" ")` : especificamos con que columna hay relación.

````java
//ejemplo --> esto iría en la clase que se vincule a la otra clase (private DetallesEmpleado detallesEmpleado) la cual están 
//vinculadas por la id y es de tipo cascada
//habrá que crear los getter y setter de la clase (private DetallesEmpleado detallesEmpleado)
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "id")
private DetallesEmpleado detallesEmpleado;
````

`@GeneratedValue` : identifica cuál es la clave primaria. Ejemplo: `@GeneratedValue(strategy = GenerationType.IDENTITY) `
+ AUTO
+ IDENTITY
+ SEQUENCE
+ TABLE

**NOTAS**: 
+ Importar siempre `javax.persistence`
+ Crear constructor vacio siempre.
+ Hay que crear los getter, setter, toString.




````java
import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class CategoriesEntity {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Id
    @Column(name = "CategoryID")
    private int categoryId;
    @Basic
    @Column(name = "CategoryName")
    private String categoryName;
    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "Picture")
    private byte[] picture;

    //faltan constructores
    
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesEntity that = (CategoriesEntity) o;
        return categoryId == that.categoryId && Objects.equals(categoryName, that.categoryName) && Objects.equals(description, that.description) && Arrays.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(categoryId, categoryName, description);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }
}

````

---

## EJEMPLO:

```java
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.security.auth.login.Configuration;
import java.util.List;

public class Hibernate {

  public static void main(String[] args) {

    //esta parte podría esta en un metodo aparte
    SessionFactory instancia = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    Session session = instancia.openSession();

  }
  
  //------------------------------------------ INTRODUCIR DATOS ---------------------------------------------------//
  
  // Hay varias formas -->
  
  public static void intro(Session session) {
      //insertamos nuevo registro
      Empleado empleado = new Empleado("juan", "moreno", 15);
      session.beginTransaction();
      session.save(empleado);
      session.getTransaction().commit();
      session.close();
  }

  public void setter(Session session){
    session.beginTransaction();
    //seleccionamos el empleado por la id --> 1
    Empleado empleado = session.get(Empleado.class, 1);
    //hago un setter
    empleado.setNombre("Paco");
    //commit
    session.getTransaction().commit();
    session.close();
  }

  //------------------------------------------ ELIMINAR DATOS ---------------------------------------------------//
  
  public void delete(Session session){
      session.beginTransaction();
      //eliminamos empleado
      session.createQuery("delete Empleado where Nombre ='Paco'").executeUpdate();
      session.getTransaction().commit();
      session.close();
  }

  //------------------------------------------ ACTUALIZAR DATOS ---------------------------------------------------//
  
  //-------------------------------------------------- CONSULTAS ------------------------------------------------//
  
  // Hay varias maneras de hacer consultas:
  
  // Consultas de un solo resultado -->

  private void media(Session session) {
    Query q = session.createQuery("select AVG(unitPrice) from ProductsEntity ");
    //para un unico resultadousamos --> getSingleResult y asi no tenemos que hacer un iterator
    System.out.println(q.getSingleResult());
  }
  
  // Consulta de una entidad en concreto -->
  
  public void getter(Session session){
      //seleccionamos el empleado por la id --> 1
      Empleado empleado = session.get(Empleado.class, 1);
      System.out.println(emp);
      session.close();
  }

  public void loader(Session session){
    //seleccionamos el empleado por la id --> 1
    Empleado empleado = session.load(Empleado.class, 1);
    System.out.println(emp);
    session.close();
  }
  
  // Consultas con varios resultados -->
  
  public void consulta01(Session session) {
      session.beginTransaction();
      Query q = session.createQuery("from Empleados"); // al no poder usar select * se pone directamente from [tabla]
      List<Empleado> empleados = q.getResultList(); // tambien --> q.list();
      //listarlo
      for (Empleado emp: empleados) {
        System.out.println(emp);
      }
      session.close();
  }

  public void consulta02(Session session) {
    Query q = session.createQuery("from Empleados");
    q.setFetchSize(3); // --> parte nueva: medida de optimizacion --> le dice al servidor que los pide de 3 en 3
    Iterator iter = q.iterate();
    while (iter.hasNext()) {
      //extraer el objeto
      Empleados cat = (Empleados) iter.next();
      System.out.println("Category info: " + cat.getCategoryId() + "," + cat.getCategoryName());
    }
    
  }

  private static void ejecutarQuery(Session session) {

    // HQL, usamos el nombre de la entidad no de la tabla
    Query q = session.createQuery("Empleado");
    List<Empleado> lista = q.list(); //List<CategoriesEntity> lista = q.getResultList() --> otra forma de hacer
    // Obtenemos un Iterador y recorremos la lista.
    Iterator<Empleado> iter = lista.iterator();
    System.out.println("Número de registros: " + lista.size());
    while (iter.hasNext()) {
      Empleado cat = iter.next();
      System.out.println("EmpleadoName: " + cat.getEmpleadoName());
    }

  }
  
  
  
  
  public void relacion(Session session){
    try {
      Empleado empleado = new Empleado("Lucas",32);
      DetallesEmpleado detallesEmpleado = new DetallesEmpleado ("soltero");
      //asociar los objetos
      empleado.setDetallesEmpleado(detallesEmpleado);
      session.beginTransaction();
      //al tener una relación de 1:1 entre Empleado y DetalleEmpleado no hace falta hacer en este caso un save de DetalleEmpleado
      session.save(empleado);
      session.getTransaction().commit();
      session.close();
    } finally {
      instancia.close();
    }
  }
  
}
```


