
package Springweb.service;

import Springweb.entity.Vegetable;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public interface VegetableService {
        
    @Autowired
    public Optional<Vegetable> findById(Integer id);

    public boolean existsById(Integer id);

    public Iterable<Vegetable> findAll();

    public long count();

    public void deleteById(Integer id);

    public void delete(Vegetable entity);

    public void deleteAll();
}
