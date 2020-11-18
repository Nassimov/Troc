
package troc.project.troc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import troc.project.troc.model.ListMsg;
import troc.project.troc.model.MsgList;

public interface ListMsgRepository extends CrudRepository<ListMsg, Long> {
    public List<ListMsg> findAllByMsgList(MsgList msgList);
}