
package troc.project.troc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import troc.project.troc.model.ObjectSNDList;
import troc.project.troc.model.SndObjectList;

public interface ObjectSNDListRepository extends CrudRepository<ObjectSNDList, Long> {
    public List<ObjectSNDList> findAllBySndObjectList(SndObjectList SndObjectList);

}