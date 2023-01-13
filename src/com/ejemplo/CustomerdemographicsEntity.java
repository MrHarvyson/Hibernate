package com.ejemplo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "customerdemographics", schema = "northwind", catalog = "")
public class CustomerdemographicsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CustomerTypeID")
    private String customerTypeId;
    @Basic
    @Column(name = "CustomerDesc")
    private String customerDesc;
    @OneToMany(mappedBy = "customerdemographicsByCustomerTypeId")
    private Collection<CustomercustomerdemoEntity> customercustomerdemosByCustomerTypeId;

    public String getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(String customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerdemographicsEntity that = (CustomerdemographicsEntity) o;
        return Objects.equals(customerTypeId, that.customerTypeId) && Objects.equals(customerDesc, that.customerDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerTypeId, customerDesc);
    }

    public Collection<CustomercustomerdemoEntity> getCustomercustomerdemosByCustomerTypeId() {
        return customercustomerdemosByCustomerTypeId;
    }

    public void setCustomercustomerdemosByCustomerTypeId(Collection<CustomercustomerdemoEntity> customercustomerdemosByCustomerTypeId) {
        this.customercustomerdemosByCustomerTypeId = customercustomerdemosByCustomerTypeId;
    }
}
