/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.service;

import Springweb.*;
import Springweb.entity.Customers;
import Springweb.repository.CustomersRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author caothanh
 */
@Service
public class CustomerServicelmpl implements CustomerService {

    @Autowired
    private CustomersRepository customerRepository;

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
    
}
