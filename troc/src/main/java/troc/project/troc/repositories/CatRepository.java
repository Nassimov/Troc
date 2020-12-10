
package troc.project.troc.repositories;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import troc.project.troc.model.Cat;

public interface CatRepository extends CrudRepository<Cat, Long> {
    public Cat findByIdCat(Long id);
    public Cat findByCatDate(Date catDate);

}