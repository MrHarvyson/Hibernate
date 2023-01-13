package com.ejemplo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orderdetails", schema = "northwind", catalog = "")
@IdClass(OrderdetailsEntityPK.class)
public class OrderdetailsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "OrderID")
    private int orderId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ProductID")
    private int productId;
    @Basic
    @Column(name = "UnitPrice")
    private double unitPrice;
    @Basic
    @Column(name = "Quantity")
    private short quantity;
    @Basic
    @Column(name = "Discount")
    private double discount;
    @ManyToOne
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID", nullable = false, insertable=false, updatable=false)
    private OrdersEntity ordersByOrderId;
    @ManyToOne
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID", nullable = false, insertable=false, updatable=false)
    private ProductsEntity productsByProductId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderdetailsEntity that = (OrderdetailsEntity) o;
        return orderId == that.orderId && productId == that.productId && Double.compare(that.unitPrice, unitPrice) == 0 && quantity == that.quantity && Double.compare(that.discount, discount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, unitPrice, quantity, discount);
    }

    public OrdersEntity getOrdersByOrderId() {
        return ordersByOrderId;
    }

    public void setOrdersByOrderId(OrdersEntity ordersByOrderId) {
        this.ordersByOrderId = ordersByOrderId;
    }

    public ProductsEntity getProductsByProductId() {
        return productsByProductId;
    }

    public void setProductsByProductId(ProductsEntity productsByProductId) {
        this.productsByProductId = productsByProductId;
    }
}
