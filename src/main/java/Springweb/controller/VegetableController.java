
package Springweb.controller;

import Springweb.entity.Vegetable;

import Springweb.repository.VegetableRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VegetableController {

    @Autowired
    VegetableRepository vegetableRepository;
    @GetMapping("admin/vegetables")
    public String showAllCategories(Model m) {
        Iterable<Vegetable> list = vegetableRepository.findAll();
        m.addAttribute("data", list);
        return "admin/vegetables/vegetable_all";

    }

    @GetMapping("admin/vegetables/add")
    public String create(Model m) {
        Vegetable veg = new Vegetable();
        m.addAttribute("vegetable", veg);
        return "admin/vegetables/vegetable_add";

    }

    @PostMapping("admin/vegetables/save")
    public String save(Model model, @ModelAttribute("vegetable") Vegetable vegetable) {

        vegetableRepository.save(vegetable);

        return "redirect:/admin/vegetables";

    }

    @PostMapping("admin/vegetables/update")
    public String update(Model model, @ModelAttribute("vegetable") Vegetable vegetable) {
        Optional<Vegetable> veg = vegetableRepository.findById(vegetable.getVegetableID());
        Vegetable c;
        c = veg.get();
        c.setCategoryID(vegetable.getCategoryID());
        c.setVegetable_Name(vegetable.getVegetable_Name());
        c.setUnit(vegetable.getUnit());
        c.setAmount(vegetable.getAmount());
        c.setImage(vegetable.getImage());
        c.setPrice(vegetable.getPrice());
        vegetableRepository.save(c);

        return "redirect:/admin/vegetables";

    }

    @GetMapping(value = {"admin/vegetables/edit/{id}"})
    public String edit(@PathVariable("id") int id, Model model) {
        Optional<Vegetable> veg = vegetableRepository.findById(id);
        veg.ifPresent(vegetable -> model.addAttribute("vegetable", veg));
        return "admin/vegetables/vegetable_edit";
    }
    @GetMapping(value = {"admin/vegetables/delete/{id}"})
    public String delete(@PathVariable("id") int id, Model model){
        vegetableRepository.deleteById(id);
        model.addAttribute("vegetable", vegetableRepository.findAll());
        return "redirect:/admin/vegetables";
    }
    
//    @GetMapping("URL")
//    public String getListCategory(Model m) {
//        Iterable<Category> listCate = categoryRepository.findAll();//lấy danh sách các danh mục sản phẩm ra nè
//        m.addAttribute("list", listCate);//thêm nó vào model
//        return " ";
//    }
}
