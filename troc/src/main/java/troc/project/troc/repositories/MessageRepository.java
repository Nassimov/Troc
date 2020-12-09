
package troc.project.troc.repositories;



import org.springframework.data.repository.CrudRepository;

import troc.project.troc.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
    public Message findTopByOrderByIdMessageDesc();

    public Message findByIdMsg(Long id);
    
}