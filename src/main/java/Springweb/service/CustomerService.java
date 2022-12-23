package Springweb.service;

import Springweb.entity.Customers;
import java.util.Optional;
import org.springframework.stereotype.Service;

import Springweb.entity.Customers;

import org.springframework.stereotype.Service;


@Service
public interface CustomerService {
    Iterable<Customers> findAll();
    Customers update(int id, Customers cusID);
    
    Optional<Customers> findById(int id);

    boolean checkLogin(String name, String password);

    boolean checkRegister(Customers cus);
}
