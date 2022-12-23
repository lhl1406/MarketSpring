/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.controller;

import Springweb.entity.Order;
import Springweb.repository.VegetableRepository;
import Springweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author HP
 */
@Controller
public class CartController {
     @Autowired
    private OrderService orderService;
     @GetMapping("/cart")
     public String index(Model m) {
         Iterable<Order> list = orderService.findAll();
         m.addAttribute("cart", list);
         return "/cart/index";
     }
}
