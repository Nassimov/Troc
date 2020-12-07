package troc.project.troc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import troc.project.troc.model.TrocBdd;

@Repository
public interface TrocBddRepository extends CrudRepository<TrocBdd, Long>{
    public List<TrocBdd> findAll();
    public List<TrocBdd> findAllByIdF(int idF);
    public List<TrocBdd> findAllByIdMsg(int idMsg);
}
