/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.controller;

import Springweb.entity.Customers;
import Springweb.entity.Order;
import Springweb.entity.Orderdetail;
import Springweb.entity.Vegetable;
import Springweb.repository.OrderDetailRepository;
import Springweb.repository.OrderRepository;
import Springweb.repository.VegetableRepository;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author HP
 */
@Controller
public class CartController {

    @Autowired
    private VegetableRepository vegetableRepository;

    @Autowired
    private  OrderRepository orderRepository;
    
    @Autowired
    private  OrderDetailRepository orderDetailRepository;

    @GetMapping("/cart")
    public String index(Model m, HttpSession session) {
        if (session.getAttribute("cart") != null) {
            System.out.println("Exit cart");
        } else {
            System.out.println("Not exit cart");
        }
        if (session.getAttribute("cart") != null) {
            m.addAttribute("cart", session.getAttribute("cart"));
        }
        if (session.getAttribute("carts") != null) {
            m.addAttribute("carts", session.getAttribute("carts"));
        }
        if (session.getAttribute("total") != null) {
            m.addAttribute("total", session.getAttribute("total"));
        }
        return "cart/index";
    }

    @GetMapping("/cart/add")
    public String addCart(Model m, HttpSession session, @RequestParam("VegetableID") int VegetableID) {
        ArrayList<Orderdetail> list = new ArrayList<>();
        ArrayList<Vegetable> listV = new ArrayList<>();
        double Total = 0;
        if (session.getAttribute("cart") != null) {
            System.out.println("Exit cart");
            list = (ArrayList<Orderdetail>) session.getAttribute("cart");
            listV = (ArrayList<Vegetable>) session.getAttribute("carts");
            Total = (double) session.getAttribute("total");
            ArrayList<Orderdetail> listTmp = list;
            Boolean checkItemInCart = false;
            for (int i = 0; i < listTmp.size(); i++) {
                if (listTmp.get(i).getVegetableID() == VegetableID) {
                    int amount = listTmp.get(i).getQuantity();
                    listTmp.get(i).setQuantity(amount + 1);
                    listV.get(i).setAmount(amount + 1);
                    checkItemInCart = true;
                    Total += listTmp.get(i).getPrice();
                }
            }
            if (!checkItemInCart) {
                Optional<Vegetable> vegetable = vegetableRepository.findById(VegetableID);
                Vegetable v;
                v = vegetable.get();
                Orderdetail orderD = new Orderdetail();
                orderD.setQuantity(1);
                orderD.setVegetableID(VegetableID);
                orderD.setPrice(v.getPrice());
                list.add(orderD);
                v.setAmount(1);
                listV.add(v);
                Total += v.getPrice();
            }
            session.setAttribute("cart", list);
            session.setAttribute("carts", listV);
            session.setAttribute("total", Total);
        } else {
            Optional<Vegetable> vegetable = vegetableRepository.findById(VegetableID);
            Vegetable v;
            v = vegetable.get();
            Orderdetail orderD = new Orderdetail();
            orderD.setQuantity(1);
            orderD.setVegetableID(VegetableID);
            orderD.setPrice(v.getPrice());
            list.add(orderD);
            v.setAmount(1);
            listV.add(v);
            Total += v.getPrice();
            session.setAttribute("cart", list);
            session.setAttribute("carts", listV);
            session.setAttribute("total", Total);
        }

        m.addAttribute("cart", list);
        m.addAttribute("carts", listV);
        m.addAttribute("total", Total);
//        return ("cart/index");
        return "redirect:/home";
    }
    
    @GetMapping("/cart/saveOrder")
    public String saveOrder(Model m, HttpSession session) {
        if(session.getAttribute("USERNAME") == null) {
            return "login";
        } else {
           if (session.getAttribute("cart") != null) {
            Order o = new Order();
            Customers c = (Customers) session.getAttribute("info");
            o.setCustomerID(c.getCustomerID());
            Date d = new Date();
            o.setNote("OK");
            o.setDate(d);
            float total = (float) (double) session.getAttribute("total");
            o.setTotal(total);
            
            orderRepository.save(o);
            ArrayList<Orderdetail> listOD = (ArrayList<Orderdetail>) session.getAttribute("cart");
            for(int i = 0; i <listOD.size(); i++) {
                Orderdetail od = new Orderdetail();
                od.setOrderID(o.getOrderID());
                od.setVegetableID(listOD.get(i).getVegetableID());
                od.setQuantity(listOD.get(i).getQuantity());
                od.setPrice(listOD.get(i).getPrice());
                orderDetailRepository.save(od);
            }
            session.removeAttribute("total");
            session.removeAttribute("cart");
            session.removeAttribute("carts");

        }  
        }
        return "redirect:/home";
    }
    
    @GetMapping("/history")
    public String History(Model m, HttpSession session) {
        ArrayList<Order> list = new ArrayList<>();
         if(session.getAttribute("USERNAME") == null) {
            return "login";
        }  else {
            Customers c = (Customers) session.getAttribute("info");
            list = (ArrayList<Order>) orderRepository.findByCustomerID(c.getCustomerID());
            m.addAttribute("cartHistory", list);
         }
       
        return ("cart/history");
    }
    @GetMapping("/view")
    public String viewBill(Model m, HttpSession session, @RequestParam("OrderID") int OrderID) {
        ArrayList<Orderdetail> list = new ArrayList<>();
        ArrayList<Vegetable> listV = new ArrayList<>();
         if(session.getAttribute("USERNAME") == null) {
            return "login";
        }  else {
            list = (ArrayList<Orderdetail>) orderDetailRepository.findByOrderID(OrderID);
            for(int i = 0; i< list.size(); i++) {
                Optional<Vegetable> vegetable = vegetableRepository.findById(list.get(i).getVegetableID());
                Vegetable v = vegetable.get();
                v.setAmount(list.get(i).getQuantity());
                listV.add(v);
            }
            m.addAttribute("views", listV);
         }
       
        return ("cart/detail");
    }
}
