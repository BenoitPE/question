package fr.gamedev.question.question.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.gamedev.question.question.data.Question;

/**
 * @author djer1
 *
 */
@RepositoryRestResource(collectionResourceRel = "question", path = "question")
public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
    Question findQuestionById(@Param("id") long id);
}
