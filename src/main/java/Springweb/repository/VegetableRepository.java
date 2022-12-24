
package Springweb.repository;

import Springweb.entity.Vegetable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VegetableRepository extends CrudRepository<Vegetable, Integer>{
    
    @Query("Select v from Vegetable v Where v.CategoryID = ?1")
    public Iterable<Vegetable> findByCategoryID(int id);
    
    @Query("Select v from Vegetable v ORDER BY Amount desc limit ?1")
    public Iterable<Vegetable> findByTopSell(int count);
    
     @Query("Select v from Vegetable v Where v.CategoryID = ?1")
    public Iterable<Vegetable> CheckForeignKey(int CategoryID);
        
}
