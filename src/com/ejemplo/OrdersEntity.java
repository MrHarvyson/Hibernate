package com.ejemplo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "northwind", catalog = "")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "OrderID")
    private int orderId;
    @Basic
    @Column(name = "CustomerID")
    private String customerId;
    @Basic
    @Column(name = "EmployeeID")
    private Integer employeeId;
    @Basic
    @Column(name = "OrderDate")
    private Timestamp orderDate;
    @Basic
    @Column(name = "RequiredDate")
    private Timestamp requiredDate;
    @Basic
    @Column(name = "ShippedDate")
    private Timestamp shippedDate;
    @Basic
    @Column(name = "ShipVia")
    private Integer shipVia;
    @Basic
    @Column(name = "Freight")
    private Double freight;
    @Basic
    @Column(name = "ShipName")
    private String shipName;
    @Basic
    @Column(name = "ShipAddress")
    private String shipAddress;
    @Basic
    @Column(name = "ShipCity")
    private String shipCity;
    @Basic
    @Column(name = "ShipRegion")
    private String shipRegion;
    @Basic
    @Column(name = "ShipPostalCode")
    private String shipPostalCode;
    @Basic
    @Column(name = "ShipCountry")
    private String shipCountry;
    @OneToMany(mappedBy = "ordersByOrderId")
    private Collection<OrderdetailsEntity> orderdetailsByOrderId;
    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID", insertable=false, updatable=false)
    private CustomersEntity customersByCustomerId;
    @ManyToOne
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID", insertable=false, updatable=false)
    private EmployeesEntity employeesByEmployeeId;
    @ManyToOne
    @JoinColumn(name = "ShipVia", referencedColumnName = "ShipperID", insertable=false, updatable=false)
    private ShippersEntity shippersByShipVia;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Timestamp requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Timestamp getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Timestamp shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Integer getShipVia() {
        return shipVia;
    }

    public void setShipVia(Integer shipVia) {
        this.shipVia = shipVia;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return orderId == that.orderId && Objects.equals(customerId, that.customerId) && Objects.equals(employeeId, that.employeeId) && Objects.equals(orderDate, that.orderDate) && Objects.equals(requiredDate, that.requiredDate) && Objects.equals(shippedDate, that.shippedDate) && Objects.equals(shipVia, that.shipVia) && Objects.equals(freight, that.freight) && Objects.equals(shipName, that.shipName) && Objects.equals(shipAddress, that.shipAddress) && Objects.equals(shipCity, that.shipCity) && Objects.equals(shipRegion, that.shipRegion) && Objects.equals(shipPostalCode, that.shipPostalCode) && Objects.equals(shipCountry, that.shipCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, employeeId, orderDate, requiredDate, shippedDate, shipVia, freight, shipName, shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry);
    }

    public Collection<OrderdetailsEntity> getOrderdetailsByOrderId() {
        return orderdetailsByOrderId;
    }

    public void setOrderdetailsByOrderId(Collection<OrderdetailsEntity> orderdetailsByOrderId) {
        this.orderdetailsByOrderId = orderdetailsByOrderId;
    }

    public CustomersEntity getCustomersByCustomerId() {
        return customersByCustomerId;
    }

    public void setCustomersByCustomerId(CustomersEntity customersByCustomerId) {
        this.customersByCustomerId = customersByCustomerId;
    }

    public EmployeesEntity getEmployeesByEmployeeId() {
        return employeesByEmployeeId;
    }

    public void setEmployeesByEmployeeId(EmployeesEntity employeesByEmployeeId) {
        this.employeesByEmployeeId = employeesByEmployeeId;
    }

    public ShippersEntity getShippersByShipVia() {
        return shippersByShipVia;
    }

    public void setShippersByShipVia(ShippersEntity shippersByShipVia) {
        this.shippersByShipVia = shippersByShipVia;
    }
}
