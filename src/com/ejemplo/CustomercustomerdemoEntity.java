package com.ejemplo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customercustomerdemo", schema = "northwind", catalog = "")
@IdClass(CustomercustomerdemoEntityPK.class)
public class CustomercustomerdemoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CustomerID")
    private String customerId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CustomerTypeID", insertable=false, updatable=false)
    private String customerTypeId;
    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID", nullable = false, insertable=false, updatable=false)
    private CustomersEntity customersByCustomerId;
    @ManyToOne
    @JoinColumn(name = "CustomerTypeID", referencedColumnName = "CustomerTypeID", nullable = false, insertable=false, updatable=false)
    private CustomerdemographicsEntity customerdemographicsByCustomerTypeId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(String customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomercustomerdemoEntity that = (CustomercustomerdemoEntity) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(customerTypeId, that.customerTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerTypeId);
    }

    public CustomersEntity getCustomersByCustomerId() {
        return customersByCustomerId;
    }

    public void setCustomersByCustomerId(CustomersEntity customersByCustomerId) {
        this.customersByCustomerId = customersByCustomerId;
    }

    public CustomerdemographicsEntity getCustomerdemographicsByCustomerTypeId() {
        return customerdemographicsByCustomerTypeId;
    }

    public void setCustomerdemographicsByCustomerTypeId(CustomerdemographicsEntity customerdemographicsByCustomerTypeId) {
        this.customerdemographicsByCustomerTypeId = customerdemographicsByCustomerTypeId;
    }
}
