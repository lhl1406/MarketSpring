package Springweb.controller;

import Springweb.entity.Category;

import Springweb.repository.CategoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;
    @GetMapping("admin/categories")
    public String showAllCategories(Model m) {
        Iterable<Category> list = categoryRepository.findAll();
        m.addAttribute("data", list);
        return "admin/categories/category_all";

    }

    @GetMapping("admin/categories/add")
    public String create(Model m) {
        Category cate = new Category();
        m.addAttribute("category", cate);
        return "admin/categories/category_add";

    }

    @PostMapping("admin/categories/save")
    public String save(Model model, @ModelAttribute("category") Category category) {

        categoryRepository.save(category);

        return "redirect:/admin/categories";

    }

    @PostMapping("admin/categories/update")
    public String update(Model model, @ModelAttribute("category") Category category) {
        Optional<Category> cate = categoryRepository.findById(category.getCategoryID());
        Category c;
        c = cate.get();
        c.setName(category.getName());
        c.setDescription(category.getDescription());
        categoryRepository.save(c);

        return "redirect:/admin/categories";

    }

    @GetMapping(value = {"admin/categories/edit/{id}"})
    public String edit(@PathVariable("id") int id, Model model) {
        Optional<Category> cate = categoryRepository.findById(id);
        cate.ifPresent(category -> model.addAttribute("category", cate));
        return "admin/categories/category_edit";
    }
    @GetMapping(value = {"admin/categories/delete/{id}"})
    public String delete(@PathVariable("id") int id, Model model){
        categoryRepository.deleteById(id);
        model.addAttribute("category", categoryRepository.findAll());
        return "redirect:/admin/categories";
    }
}
