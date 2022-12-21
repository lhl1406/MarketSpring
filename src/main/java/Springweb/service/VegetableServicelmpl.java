
package Springweb.service;

import Springweb.entity.Vegetable;
import Springweb.repository.VegetableRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VegetableServicelmpl implements VegetableService{
    
    @Autowired
    private VegetableRepository vegetableRepository;

    @Override
    public Optional<Vegetable> findById(Integer id) {
        return vegetableRepository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return vegetableRepository.existsById(id);
    }

    @Override
    public Iterable<Vegetable> findAll() {
        return vegetableRepository.findAll();
    }

    @Override
    public long count() {
        return vegetableRepository.count();
    }

    @Override
    public void deleteById(Integer id) {
        vegetableRepository.deleteById(id);
    }

    @Override
    public void delete(Vegetable entity) {
        vegetableRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        vegetableRepository.deleteAll();
    }
}
