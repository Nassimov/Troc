
package troc.project.troc.repositories;

import org.springframework.data.repository.CrudRepository;

import troc.project.troc.model.Cat;

public interface CatRepository extends CrudRepository<Cat, Long> {

}