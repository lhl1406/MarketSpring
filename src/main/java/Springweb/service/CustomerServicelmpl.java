/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.service;

import Springweb.*;
import Springweb.entity.Customers;
import Springweb.repository.CustomersRepository;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

/**
 *
 * @author caothanh
 */
@Service
public class CustomerServicelmpl implements CustomerService {

    @Autowired
    private CustomersRepository customerRepository;

    public CustomerServicelmpl() {
        list = new ArrayList<>();
    }
    @Override
    public boolean checkLogin(String name, String password) {
        Customers customer = customerRepository.findbyName(name,password);
        if(customer != null)
            return true;
        return false;
    } 

    @Override
    public boolean checkRegister(Customers cus) {
        Customers customer = customerRepository.existCus(cus.getFullname());
        if(customer != null)
            return false;
        customerRepository.save(cus);
        return true;
    }
     List<Customers> list;
    @Autowired
    private CustomersRepository Repository;

   

    @Override
    public Iterable<Customers> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public Customers update(int id,Customers cus) {
        //Customers c = findById(cus.getCustomerID()).orElse(null);
        Optional<Customers> customer = findById(cus.getCustomerID());
        Customers c = customer.get();     
        if (StringUtils.isEmpty(cus.getPassword())) {
            c.setPassword(c.getPassword());
        } else {
            c.setPassword(cus.getPassword());
        }
        return Repository.save(c);
    }

    @Override
    public Optional<Customers> findById(int id) {
        return Repository.findById(id);
    }
    
}
