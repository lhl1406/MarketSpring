package Springweb.controller;

import Springweb.entity.Customers;
import Springweb.entity.Vegetable;
import Springweb.entity.Category;
import Springweb.repository.CustomersRepository;
import Springweb.repository.VegetableRepository;
import Springweb.repository.CategoryRepository;
import Springweb.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CustomersController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomersRepository customersReposity;
   
    @GetMapping("/register")
    public String registerForm(HttpSession session) {
        if(session.getAttribute("USERNAME") != null) 
            return "home";
        return "register";
    }
    
    
    @GetMapping("/login")
    public String loginForm(HttpSession session){
        if(session.getAttribute("USERNAME") != null) 
            return "home";
        return "login";
    }
    @PostMapping("/customer/update")
    public String update(Model model, @ModelAttribute("customer") Customers customer)
    {
        Optional<Customers> cus = customersReposity.findById(customer.getCustomerID());
        Customers c;
        c = cus.get();
        //c.setCustomerID(customer.getCustomerID());
        c.setPassword(customer.getPassword());
        c.setAddress(customer.getAddress());
        c.setCity(customer.getCity());
        c.setFullname(customer.getFullname());
        customersReposity.save(c);
        
        return "redirect:/customer/all";
        
    }
    
    @GetMapping("/customer/all")
    public String showAllCustomer(Model m)
    {
        Iterable<Customers> list = customersReposity.findAll();
        m.addAttribute("data", list);
        return "customer_all";
        
    }
    @GetMapping(value = {"customer/edit/{id}"})
    public String edit(@PathVariable("id") int id, Model model) {
        Optional<Customers> cus = customersReposity.findById(id);
        cus.ifPresent(customer->model.addAttribute("customer", cus));
        //model.addAttribute("customer", cus);
        return "customer_edit";
    }
    @RequestMapping("/customer/delete/{id}")
    public String CusDelete(@PathVariable int id, Model model) {
        customersReposity.deleteById(id);
        model.addAttribute("listCustomer", customersReposity.findAll());
        return "redirect:/customer/all";
    }  
    
    //

    @PostMapping("/customer/checklogin")
    public String checklogin(Model model, @RequestParam("taikhoan") String username, 
            @RequestParam("pass") String password, HttpSession session) {
        boolean check = customerService.checkLogin(username, password);
        if (check == false) {
            model.addAttribute("ERROR", "Username or Password is wrong");
            return "login";
        }
        Customers c = customersReposity.findbyName(username, password);
        session.setAttribute("USERNAME", username);
        session.setAttribute("info", c);
        return "redirect:/home";
//        return "home";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("USERNAME");
        return "redirect:/home";
    }
    
    @PostMapping("/customer/checkRegister")
    public String checkRegister(Model model, @ModelAttribute("customer") Customers customer,HttpSession session){
        boolean check = customerService.checkRegister(customer);
        if(check == false){
            model.addAttribute("ERROR", "Username has exist");
            return "register";
        }
        session.setAttribute("USERNAME", customer.getFullname());
        return "home";
    }   
}
