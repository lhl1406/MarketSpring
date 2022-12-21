package Springweb.service;

import Springweb.entity.Customers;
import Springweb.repository.CustomersRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class CustomerServiceImpl implements CustomerService {

    List<Customers> list;
    @Autowired
    private CustomersRepository Repository;

    public CustomerServiceImpl() {
        list = new ArrayList<>();
    }

    @Override
    public Iterable<Customers> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
//    @Override
//    public Customers update(int id,Customers cus) {
//        Customers c = findById(cus.getCustomerID()).orElse(null);
//        //Optional<Customers> customer = findById(cus.getCustomerID());
//        //Customers c = customer.get();     
//        if (StringUtils.isEmpty(cus.getPassword())) {
//            c.setPassword(c.getPassword());
//        } else {
//            c.setPassword(cus.getPassword());
//        }
//        return Repository.save(c);
//    }

    @Override
    public Optional<Customers> findById(int id) {
        return Repository.findById(id);
    }

}
