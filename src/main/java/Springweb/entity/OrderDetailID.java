/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


/**
 *
 * @author VIVOBOOK
 */
@Embeddable
public class OrderDetailID implements Serializable{
    private Order order;
    private Vegetable vegetable;
    
    @ManyToOne(cascade = CascadeType.ALL)
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    @ManyToOne(cascade = CascadeType.ALL)
    public Vegetable getVegetable() {
        return vegetable;
    }
 
    public void setVegetable(Vegetable vegetable) {
        this.vegetable = vegetable;
    }
    
    
}
