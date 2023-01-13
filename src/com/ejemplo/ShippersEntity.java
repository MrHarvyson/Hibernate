package com.ejemplo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "shippers", schema = "northwind", catalog = "")
public class ShippersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ShipperID")
    private int shipperId;
    @Basic
    @Column(name = "CompanyName")
    private String companyName;
    @Basic
    @Column(name = "Phone")
    private String phone;
    @OneToMany(mappedBy = "shippersByShipVia")
    private Collection<OrdersEntity> ordersByShipperId;

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippersEntity that = (ShippersEntity) o;
        return shipperId == that.shipperId && Objects.equals(companyName, that.companyName) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipperId, companyName, phone);
    }

    public Collection<OrdersEntity> getOrdersByShipperId() {
        return ordersByShipperId;
    }

    public void setOrdersByShipperId(Collection<OrdersEntity> ordersByShipperId) {
        this.ordersByShipperId = ordersByShipperId;
    }
}
