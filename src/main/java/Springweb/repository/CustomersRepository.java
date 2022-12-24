/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.repository;

import Springweb.entity.Customers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository  extends CrudRepository<Customers, Integer>{ 

//    public Customers findbyName(String name, String password);
    
    @Query("Select c from Customers c Where c.Fullname = ?1 and c.Password = ?2")
    Customers findbyName(String name , String password);
    
    @Query("Select c from Customers c Where c.Fullname = ?1")
    Customers existCus(String name);
}

