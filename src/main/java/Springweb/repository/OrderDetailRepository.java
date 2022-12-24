/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.repository;

import Springweb.entity.Orderdetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDetailRepository extends CrudRepository<Orderdetail, Integer>{
   
    @Query("Select o from Orderdetail o Where o.OrderID = ?1")
    public Iterable<Orderdetail> findByOrderID(int OrderID);
    
    @Query("Select o from Orderdetail o Where o.VegetableID = ?1")
    public Iterable<Orderdetail> CheckForeignKey(int VegetableID);
}
