/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.entity;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

/**
 *
 * @author VIVOBOOK
 */
@Data
@Entity
@Table (name = "orderdetail")
@AssociationOverrides({
    @AssociationOverride (name = "primaryKey.order",
            joinColumns = @JoinColumn(name="OrderID")),
    @AssociationOverride (name = "primaryKey.vegetable",
            joinColumns = @JoinColumn(name="VegetableID"))
})
public class OrderDetail implements Serializable {
    
    @Id
    private OrderDetailID primaryKey = new OrderDetailID();
    
    public OrderDetailID getPrimaryKey() {
        return primaryKey;
    }
    
     public void setPrimaryKey(OrderDetailID primaryKey) {
        this.primaryKey = primaryKey;
    }
 
    @Transient
    public Order getOrder() {
        return getPrimaryKey().getOrder();
    }
 
    public void setOrder(Order order) {
        getPrimaryKey().setOrder(order);
    }
 
    @Transient
    public Vegetable getVegetable() {
        return getPrimaryKey().getVegetable();
    }
 
    public void setVegetable(Vegetable vegetable) {
        getPrimaryKey().setVegetable(vegetable);
    }
 
    @Column(name = "Quantity")
    private int Quantity;
    
    @Column(name = "Price")
    private float Price;

}
