
package troc.project.troc.repositories;

import org.springframework.data.repository.CrudRepository;

import troc.project.troc.model.Accept;

public interface AcceptRepository extends CrudRepository<Accept, Long> {
    public Accept findByIdPropositionMsg(Long idPropositionMsg);
}