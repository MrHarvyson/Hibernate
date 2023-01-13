package com.ejemplo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "customers", schema = "northwind", catalog = "")
public class CustomersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CustomerID")
    private String customerId;
    @Basic
    @Column(name = "CompanyName")
    private String companyName;
    @Basic
    @Column(name = "ContactName")
    private String contactName;
    @Basic
    @Column(name = "ContactTitle")
    private String contactTitle;
    @Basic
    @Column(name = "Address")
    private String address;
    @Basic
    @Column(name = "City")
    private String city;
    @Basic
    @Column(name = "Region")
    private String region;
    @Basic
    @Column(name = "PostalCode")
    private String postalCode;
    @Basic
    @Column(name = "Country")
    private String country;
    @Basic
    @Column(name = "Phone")
    private String phone;
    @Basic
    @Column(name = "Fax")
    private String fax;
    @OneToMany(mappedBy = "customersByCustomerId")
    private Collection<CustomercustomerdemoEntity> customercustomerdemosByCustomerId;
    @OneToMany(mappedBy = "customersByCustomerId")
    private Collection<OrdersEntity> ordersByCustomerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomersEntity that = (CustomersEntity) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(companyName, that.companyName) && Objects.equals(contactName, that.contactName) && Objects.equals(contactTitle, that.contactTitle) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(region, that.region) && Objects.equals(postalCode, that.postalCode) && Objects.equals(country, that.country) && Objects.equals(phone, that.phone) && Objects.equals(fax, that.fax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax);
    }

    public Collection<CustomercustomerdemoEntity> getCustomercustomerdemosByCustomerId() {
        return customercustomerdemosByCustomerId;
    }

    public void setCustomercustomerdemosByCustomerId(Collection<CustomercustomerdemoEntity> customercustomerdemosByCustomerId) {
        this.customercustomerdemosByCustomerId = customercustomerdemosByCustomerId;
    }

    public Collection<OrdersEntity> getOrdersByCustomerId() {
        return ordersByCustomerId;
    }

    public void setOrdersByCustomerId(Collection<OrdersEntity> ordersByCustomerId) {
        this.ordersByCustomerId = ordersByCustomerId;
    }
}
