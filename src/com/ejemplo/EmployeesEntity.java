package com.ejemplo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "employees", schema = "northwind", catalog = "")
public class EmployeesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "EmployeeID")
    private int employeeId;
    @Basic
    @Column(name = "LastName")
    private String lastName;
    @Basic
    @Column(name = "FirstName")
    private String firstName;
    @Basic
    @Column(name = "Title")
    private String title;
    @Basic
    @Column(name = "TitleOfCourtesy")
    private String titleOfCourtesy;
    @Basic
    @Column(name = "BirthDate")
    private Timestamp birthDate;
    @Basic
    @Column(name = "HireDate")
    private Timestamp hireDate;
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
    @Column(name = "HomePhone")
    private String homePhone;
    @Basic
    @Column(name = "Extension")
    private String extension;
    @Basic
    @Column(name = "Photo")
    private byte[] photo;
    @Basic
    @Column(name = "Notes")
    private String notes;
    @Basic
    @Column(name = "ReportsTo")
    private Integer reportsTo;
    @Basic
    @Column(name = "PhotoPath")
    private String photoPath;
    @ManyToOne
    @JoinColumn(name = "ReportsTo", referencedColumnName = "EmployeeID", insertable=false, updatable=false)
    private EmployeesEntity employeesByReportsTo;
    @OneToMany(mappedBy = "employeesByReportsTo")
    private Collection<EmployeesEntity> employeesByEmployeeId;
    @OneToMany(mappedBy = "employeesByEmployeeId")
    private Collection<EmployeeterritoriesEntity> employeeterritoriesByEmployeeId;
    @OneToMany(mappedBy = "employeesByEmployeeId")
    private Collection<OrdersEntity> ordersByEmployeeId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public Timestamp getHireDate() {
        return hireDate;
    }

    public void setHireDate(Timestamp hireDate) {
        this.hireDate = hireDate;
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

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(Integer reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "EmployeesEntity{" +
                "employeeId=" + employeeId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", title='" + title + '\'' +
                ", titleOfCourtesy='" + titleOfCourtesy + '\'' +
                ", birthDate=" + birthDate +
                ", hireDate=" + hireDate +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", extension='" + extension + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", notes='" + notes + '\'' +
                ", reportsTo=" + reportsTo +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeesEntity that = (EmployeesEntity) o;
        return employeeId == that.employeeId && Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(title, that.title) && Objects.equals(titleOfCourtesy, that.titleOfCourtesy) && Objects.equals(birthDate, that.birthDate) && Objects.equals(hireDate, that.hireDate) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(region, that.region) && Objects.equals(postalCode, that.postalCode) && Objects.equals(country, that.country) && Objects.equals(homePhone, that.homePhone) && Objects.equals(extension, that.extension) && Arrays.equals(photo, that.photo) && Objects.equals(notes, that.notes) && Objects.equals(reportsTo, that.reportsTo) && Objects.equals(photoPath, that.photoPath);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(employeeId, lastName, firstName, title, titleOfCourtesy, birthDate, hireDate, address, city, region, postalCode, country, homePhone, extension, notes, reportsTo, photoPath);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    public EmployeesEntity getEmployeesByReportsTo() {
        return employeesByReportsTo;
    }

    public void setEmployeesByReportsTo(EmployeesEntity employeesByReportsTo) {
        this.employeesByReportsTo = employeesByReportsTo;
    }

    public Collection<EmployeesEntity> getEmployeesByEmployeeId() {
        return employeesByEmployeeId;
    }

    public void setEmployeesByEmployeeId(Collection<EmployeesEntity> employeesByEmployeeId) {
        this.employeesByEmployeeId = employeesByEmployeeId;
    }

    public Collection<EmployeeterritoriesEntity> getEmployeeterritoriesByEmployeeId() {
        return employeeterritoriesByEmployeeId;
    }

    public void setEmployeeterritoriesByEmployeeId(Collection<EmployeeterritoriesEntity> employeeterritoriesByEmployeeId) {
        this.employeeterritoriesByEmployeeId = employeeterritoriesByEmployeeId;
    }

    public Collection<OrdersEntity> getOrdersByEmployeeId() {
        return ordersByEmployeeId;
    }

    public void setOrdersByEmployeeId(Collection<OrdersEntity> ordersByEmployeeId) {
        this.ordersByEmployeeId = ordersByEmployeeId;
    }
}
