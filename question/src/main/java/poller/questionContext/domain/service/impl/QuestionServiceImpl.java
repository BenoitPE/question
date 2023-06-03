package poller.questionContext.domain.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import poller.questionContext.domain.model.*;
import poller.questionContext.domain.repository.QuestionRepository;
import poller.questionContext.domain.service.interfaces.QuestionService;

import java.util.List;

/**
 * QuestionService class.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    /**
     * The questionRepository.
     */
    private final transient QuestionRepository questionRepository;

    /**
     * QuestionService constructor.
     *
     * @param questionRepository the questionRepository
     */
    public QuestionServiceImpl(final QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * setTag method.
     *
     * @param question the question
     * @param nameTag  the tag name
     */
    public void setTag(final Question question, final String nameTag) {
        final String uri = "http://localhost:8080/tag/search/findTagsByName?name=";

        final RestTemplate restTemplate = new RestTemplate();
        final String result = restTemplate.getForObject(uri + nameTag, String.class);
        final JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();

        System.out.println("question tag id : " + question.getIdTag());
        final int id = Integer.parseInt(String.valueOf(jsonObject.get("id")));
        System.out.println("id from tag: " + id);

        question.setIdTag(id);
        System.out.println("question tag id : " + question.getIdTag());
    }

    /**
     * updateTag method.
     *
     * @param question      the question
     * @param content       the content
     * @param correctAnswer the correct answer
     */
    public void updateTag(
            final Question question,
            final String content,
            final String correctAnswer) {
        question.setContent(content);
        question.setCorrectAnswer(correctAnswer);
    }

    /**
     * deleteTag method.
     *
     * @param question the question
     */
    public void deleteTag(
            final Question question) {
        question.setIdTag(0);
    }

    /**
     * findQuestionsByTag method.
     *
     * @param tagName the tag name
     * @return a list of Question
     */
    public List<Question> findQuestionsByTag(
            final String tagName) {
        final String uri = "http://localhost:8080/tag/search/findTagsByName?name=";

        final RestTemplate restTemplate = new RestTemplate();
        final String result = restTemplate.getForObject(uri + tagName, String.class);
        final JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();

        final int idTag = Integer.parseInt(String.valueOf(jsonObject.get("id")));
        return questionRepository.findQuestionsByIdTag(idTag);
    }
}
