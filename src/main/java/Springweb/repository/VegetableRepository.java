
package Springweb.repository;

import Springweb.entity.Vegetable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VegetableRepository extends CrudRepository<Vegetable, Integer>{
    
}
