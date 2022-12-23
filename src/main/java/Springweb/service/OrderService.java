/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.service;

import Springweb.entity.Order;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HP
 */
public interface OrderService { 

    @Autowired
    public Optional<Order> findById(Integer id);
    public boolean existsById(Integer id);

    public Iterable<Order> findAll();

    public long count();

    public void deleteById(Integer id);

    public void delete(Order entity);

    public void deleteAll();   
}
