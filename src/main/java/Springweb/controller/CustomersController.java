/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.controller;

import Springweb.entity.Customers;
import Springweb.repository.CustomersRepository;
import Springweb.service.CustomerService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author caothanh
 */
@Controller
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/register")
    public String registerForm(HttpSession session) {
        if(session.getAttribute("USERNAME") != null) 
            return "home";
        return "register";
    }
    
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    
    @GetMapping("/login")
    public String loginForm(HttpSession session){
        if(session.getAttribute("USERNAME") != null) 
            return "home";
        return "login";
    }

    @PostMapping("/customer/checklogin")
    public String checklogin(Model model, @RequestParam("taikhoan") String username, 
            @RequestParam("pass") String password, HttpSession session) {
        boolean check = customerService.checkLogin(username, password);
        if (check == false) {
            model.addAttribute("ERROR", "Username or Password is wrong");
            return "login";
        }
        session.setAttribute("USERNAME", username);
        return "home";
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
