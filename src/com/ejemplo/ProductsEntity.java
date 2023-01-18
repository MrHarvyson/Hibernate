package com.ejemplo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "northwind", catalog = "")
public class ProductsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ProductID")
    private int productId;
    @Basic
    @Column(name = "ProductName")
    private String productName;
    @Basic
    @Column(name = "SupplierID")
    private Integer supplierId;
    @Basic
    @Column(name = "CategoryID")
    private Integer categoryId;
    @Basic
    @Column(name = "QuantityPerUnit")
    private String quantityPerUnit;
    @Basic
    @Column(name = "UnitPrice")
    private Double unitPrice;
    @Basic
    @Column(name = "UnitsInStock")
    private Short unitsInStock;
    @Basic
    @Column(name = "UnitsOnOrder")
    private Short unitsOnOrder;
    @Basic
    @Column(name = "ReorderLevel")
    private Short reorderLevel;
    @Basic
    @Column(name = "Discontinued")
    private byte discontinued;
    @OneToMany(mappedBy = "productsByProductId")
    private Collection<OrderdetailsEntity> orderdetailsByProductId;
    @ManyToOne
    @JoinColumn(name = "SupplierID", referencedColumnName = "SupplierID", insertable=false, updatable=false)
    private SuppliersEntity suppliersBySupplierId;
    @ManyToOne
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID", insertable=false, updatable=false)
    private CategoriesEntity categoriesByCategoryId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Short getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Short unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public Short getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(Short unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public Short getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Short reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public byte getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(byte discontinued) {
        this.discontinued = discontinued;
    }

    @Override
    public String toString() {
        return "ProductsEntity{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", supplierId=" + supplierId +
                ", categoryId=" + categoryId +
                ", quantityPerUnit='" + quantityPerUnit + '\'' +
                ", unitPrice=" + unitPrice +
                ", unitsInStock=" + unitsInStock +
                ", unitsOnOrder=" + unitsOnOrder +
                ", reorderLevel=" + reorderLevel +
                ", discontinued=" + discontinued +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsEntity that = (ProductsEntity) o;
        return productId == that.productId && discontinued == that.discontinued && Objects.equals(productName, that.productName) && Objects.equals(supplierId, that.supplierId) && Objects.equals(categoryId, that.categoryId) && Objects.equals(quantityPerUnit, that.quantityPerUnit) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(unitsInStock, that.unitsInStock) && Objects.equals(unitsOnOrder, that.unitsOnOrder) && Objects.equals(reorderLevel, that.reorderLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, supplierId, categoryId, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);
    }

    public Collection<OrderdetailsEntity> getOrderdetailsByProductId() {
        return orderdetailsByProductId;
    }

    public void setOrderdetailsByProductId(Collection<OrderdetailsEntity> orderdetailsByProductId) {
        this.orderdetailsByProductId = orderdetailsByProductId;
    }

    public SuppliersEntity getSuppliersBySupplierId() {
        return suppliersBySupplierId;
    }

    public void setSuppliersBySupplierId(SuppliersEntity suppliersBySupplierId) {
        this.suppliersBySupplierId = suppliersBySupplierId;
    }

    public CategoriesEntity getCategoriesByCategoryId() {
        return categoriesByCategoryId;
    }

    public void setCategoriesByCategoryId(CategoriesEntity categoriesByCategoryId) {
        this.categoriesByCategoryId = categoriesByCategoryId;
    }
}
