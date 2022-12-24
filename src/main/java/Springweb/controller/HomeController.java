/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.controller;

import Springweb.entity.Category;
import Springweb.entity.Vegetable;
import Springweb.repository.CategoryRepository;
import Springweb.repository.VegetableRepository;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
public class HomeController {

    @Autowired
    private VegetableRepository vegetableRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/home")
    public String home(Model m, HttpSession session) {
        Iterable<Category> listC = categoryRepository.findAll();
        m.addAttribute("categories", listC);
        if (!m.containsAttribute("keys")) {
            if (m.containsAttribute("vegetables")) {
                System.out.println("Exit list V");
            } else {
                System.out.println("Not exit list V");
                if (session.getAttribute("vegetables") != null) {
                    System.out.println("Exit session");
                    m.addAttribute("vegetables", session.getAttribute("vegetables"));
                    m.addAttribute("keys", session.getAttribute("keys"));
                    m.addAttribute("keyWords", session.getAttribute("keyWords"));
                    if (session.getAttribute("searchPrice") != null) {
                        m.addAttribute("searchPrice", session.getAttribute("searchPrice"));
                    }
                    if (session.getAttribute("topSell") != null) {
                        m.addAttribute("topSell", session.getAttribute("topSell"));
                    }
                    
                } else {
                    m.addAttribute("keys", "all");
                    m.addAttribute("keyWords", "");
                    Iterable<Vegetable> list = vegetableRepository.findAll();
                    m.addAttribute("vegetables", list);
                    m.addAttribute("searchPrice", "des");
                    m.addAttribute("topSell", 5);
                }

            }
        }
        
        return "home";
    }

    @GetMapping("/home/filterByCategory")
    public String searchByCategoryID(Model m, @RequestParam Map<String, String> allParams, HttpSession session) {

        Iterable<String> keys = allParams.values();
        Iterable<Vegetable> list = null;
        Iterable<Category> listC = categoryRepository.findAll();
        List<Vegetable> listSearch = (List<Vegetable>) vegetableRepository.findAll();
        listSearch.removeAll(listSearch);
        for (String ID : keys) {
            list = vegetableRepository.findByCategoryID(Integer.parseInt(ID));
            for (Vegetable vegetable : list) {
                listSearch.add(vegetable);
            }
        }
        if (!listSearch.isEmpty()) {
            Iterable<Vegetable> listSearchMain = listSearch;
            m.addAttribute("vegetables", listSearchMain);
        } else {
            Iterable<Vegetable> listV = vegetableRepository.findAll();
            m.addAttribute("vegetables", listV);
        }
        m.addAttribute("categories", listC);
        m.addAttribute("keys", allParams.keySet());
        session.setAttribute("vegetables", m.getAttribute("vegetables"));
        session.setAttribute("keys", m.getAttribute("keys"));
        return "redirect:/home";
    }

    @GetMapping("/home/searchByKeyWords")
    public String searchByKeyWords(Model m, HttpSession session, @RequestParam("keyWords") String keyWords) {
        //handle key words
        keyWords = keyWords.trim().replaceAll("  +", " ").toLowerCase();
        String oldCondition = keyWords;
        String[] conditions = keyWords.split(" ");

        Iterable<Category> listC = categoryRepository.findAll();
        m.addAttribute("categories", listC);
        if (session.getAttribute("vegetables") != null) {
            System.out.println("Exit session");
            m.addAttribute("vegetables", session.getAttribute("vegetables"));
            m.addAttribute("keys", session.getAttribute("keys"));
            session.setAttribute("keys", session.getAttribute("keys"));
            if (session.getAttribute("searchPrice") != null) {
                m.addAttribute("searchPrice", session.getAttribute("searchPrice"));
            }
        } else {
            m.addAttribute("keys", "all");
            Iterable<Vegetable> list = vegetableRepository.findAll();
            m.addAttribute("vegetables", list);
            m.addAttribute("searchPrice", "des");
            session.setAttribute("keys", "all");

        }

        List<Vegetable> listSearch = (List<Vegetable>) vegetableRepository.findAll();
        listSearch.removeAll(listSearch);
        List<Vegetable> list = (List<Vegetable>) m.getAttribute("vegetables");
        for (int i = 0; i < list.size(); i++) {
            String regex = list.get(i).getALL();
            for (int j = 0; j < conditions.length; j++) {
                String oldChirlCondition = conditions[j];
                conditions[j] = "(.*)" + conditions[j] + "(.*)";
                if (regex.toLowerCase().matches(conditions[j])) {
                    listSearch.add(list.get(i));
                    break;
                }
                conditions[j] = oldChirlCondition;
            }
        }
        session.setAttribute("vegetables", listSearch);
        session.setAttribute("keyWords", oldCondition);

        return "redirect:/home";
    }

    @GetMapping("/home/filterByPrices")
    public String filterByPrices(Model m, HttpSession session, @RequestParam("searchPrice") String KeyWords) {
        Iterable<Category> listC = categoryRepository.findAll();
        m.addAttribute("categories", listC);
        if (session.getAttribute("vegetables") != null) {
            System.out.println("Exit session");
            m.addAttribute("vegetables", session.getAttribute("vegetables"));
            m.addAttribute("keys", session.getAttribute("keys"));
//            session.setAttribute("keys", session.getAttribute("keys"));

        } else {
            m.addAttribute("keys", "all");
            Iterable<Vegetable> list = vegetableRepository.findAll();
            m.addAttribute("vegetables", list);
            session.setAttribute("keys", "all");

        }
        ArrayList<Vegetable> listSearch = (ArrayList<Vegetable>) m.getAttribute("vegetables");

        if (!KeyWords.equals("des")) {
            Collections.sort(listSearch);
        } else {
            Collections.sort(listSearch, Collections.reverseOrder());
        }

        session.setAttribute("vegetables", listSearch);
        session.setAttribute("searchPrice", KeyWords);
        return "redirect:/home";

    }
    
    @GetMapping("/home/selling")
    public String selling(Model m, HttpSession session, @RequestParam("topSell") int Count) {
        Iterable<Category> listC = categoryRepository.findAll();
        m.addAttribute("categories", listC);
//        if (session.getAttribute("vegetables") != null) {
//            System.out.println("Exit session");
//            m.addAttribute("vegetables", session.getAttribute("vegetables"));
//            m.addAttribute("keys", session.getAttribute("keys"));
//        } else {
            m.addAttribute("keys", "all");
            Iterable<Vegetable> list = vegetableRepository.findAll();
            m.addAttribute("vegetables", list);
            session.setAttribute("keys", "all");
//        }
        ArrayList<Vegetable> listSearch = (ArrayList<Vegetable>) vegetableRepository.findByTopSell(Count);
        session.setAttribute("vegetables", listSearch);
        session.setAttribute("topSell", Count);
        return "redirect:/home";
    }
    
}
