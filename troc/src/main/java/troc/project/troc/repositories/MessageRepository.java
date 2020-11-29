
package troc.project.troc.repositories;

import java.security.MessageDigest;

import org.springframework.data.repository.CrudRepository;

import troc.project.troc.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
    public Message findTopByOrderByIdMessageDesc();
}