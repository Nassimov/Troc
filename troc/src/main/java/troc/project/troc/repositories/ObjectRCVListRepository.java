
package troc.project.troc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import troc.project.troc.model.ObjectRCVList;
import troc.project.troc.model.RcvObjectList;

public interface ObjectRCVListRepository extends CrudRepository<ObjectRCVList, Long> {
    public List<ObjectRCVList> findAllByRcvObjectList(RcvObjectList rcvObjectList);

}