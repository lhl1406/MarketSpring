/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Springweb.service;

import Springweb.entity.Category;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 84378
 */
public interface CategoryService {

    @Autowired
    public Optional<Category> findById(Integer id);

    public boolean existsById(Integer id);

    public Iterable<Category> findAll();

    public long count();

    public void deleteById(Integer id);

    public void delete(Category entity);

    public void deleteAll();
}
