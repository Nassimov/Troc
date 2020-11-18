
package troc.project.troc.repositories;

import org.springframework.data.repository.CrudRepository;

import troc.project.troc.model.FileTroc;

public interface FileTrocRepository extends CrudRepository<FileTroc, Long> {
    public FileTroc findTopByOrderByIdFileTrocDesc();

}