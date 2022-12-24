/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.repository;

import Springweb.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>{

    @Query("Select c from Order c Where c.CustomerID = ?1")
    public Iterable<Order> findByCustomerID(int ID);
    @Query("Select o from Order o Where o.CustomerID = ?1")
    public Iterable<Order> CheckForeignKey(int CustomerID);
}
