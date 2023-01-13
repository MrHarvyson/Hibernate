package com.ejemplo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CustomercustomerdemoEntityPK implements Serializable {
    @Column(name = "CustomerID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String customerId;
    @Column(name = "CustomerTypeID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String customerTypeId;

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
        CustomercustomerdemoEntityPK that = (CustomercustomerdemoEntityPK) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(customerTypeId, that.customerTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerTypeId);
    }
}
