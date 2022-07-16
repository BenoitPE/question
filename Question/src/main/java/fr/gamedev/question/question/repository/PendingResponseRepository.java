package fr.gamedev.question.question.repository;

import fr.gamedev.question.question.data.PendingResponse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * PendingResponseRepository interface.
 */
@RepositoryRestResource(
        collectionResourceRel = "pendingResponse",
        path = "pendingResponse")
public interface PendingResponseRepository
        extends PagingAndSortingRepository<PendingResponse, Long> {
    /**findPendingResponsesByIdUser method.
     * @param idUser the user ID
     * @return a list of PendingResponse*/
    List<PendingResponse> findPendingResponsesByIdUser(
            @Param("idUser") long idUser);
}
