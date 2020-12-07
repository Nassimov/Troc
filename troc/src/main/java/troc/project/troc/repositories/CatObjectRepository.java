
package troc.project.troc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import troc.project.troc.model.Cat;
import troc.project.troc.model.CatObjects;

public interface CatObjectRepository extends CrudRepository<CatObjects, Long> {
    public List<CatObjects> findAllByCat(Cat cat);

}