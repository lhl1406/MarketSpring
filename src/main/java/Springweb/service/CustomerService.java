package Springweb.service;

import Springweb.*;
import Springweb.entity.Customers;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author caothanh
 */
@Service
public interface CustomerService {

    boolean checkLogin(String name, String password);

    boolean checkRegister(Customers cus);
}
