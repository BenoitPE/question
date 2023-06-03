package poller.questionContext.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poller.questionContext.application.dtos.QuestionDTO;
import poller.questionContext.application.mappers.QuestionMapper;
import poller.questionContext.application.service.QuestionEventService;
import poller.questionContext.domain.model.*;
import poller.questionContext.domain.repository.PendingResponseRepository;
import poller.questionContext.domain.repository.QuestionRepository;
import poller.questionContext.domain.repository.UserAnswerRepository;
import poller.questionContext.domain.service.interfaces.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author djer1
 */
@RestController
public class QuestionController {

    /**
     * The questionService.
     */
    private final transient QuestionService questionService;

    private final transient QuestionEventService questionEventService;

    /**
     * The questionRepository.
     */
    private final transient QuestionRepository questionRepository;
    /**
     * The userAnswerService.
     */
    private final transient UserAnswerService userAnswerService;
    /**
     * The userAnswerRepository.
     */
    private final transient UserAnswerRepository userAnswerRepository;
    /**
     * The pendingResponseRepository.
     */
    private final transient PendingResponseRepository pendingResponseRepository;
    /**
     * The pendingResponseService.
     */
    private final transient PendingResponseService pendingResponseService;

    /**
     * ResponseController constructor.
     *
     * @param questionService           the questionService
     * @param questionRepository        the questionRepository
     * @param userAnswerService         the userAnswerService
     * @param userAnswerRepository      the userAnswerRepository
     * @param pendingResponseRepository the pendingResponseRepository
     * @param pendingResponseService    the pendingResponseService
     * @param questionEventService      the questionEventService
     */
    @Autowired
    public QuestionController(final QuestionService questionService,
                              final QuestionRepository questionRepository,
                              final UserAnswerService userAnswerService,
                              final UserAnswerRepository userAnswerRepository,
                              final PendingResponseRepository pendingResponseRepository,
                              final PendingResponseService pendingResponseService,
                              final QuestionEventService questionEventService) {
        this.questionService = questionService;
        this.questionRepository = questionRepository;
        this.userAnswerService = userAnswerService;
        this.userAnswerRepository = userAnswerRepository;
        this.pendingResponseRepository = pendingResponseRepository;
        this.pendingResponseService = pendingResponseService;
        this.questionEventService = questionEventService;
    }

    /**
     * Gives a response if the answer is correct or not.
     *
     * @param questionId the question ID
     * @param answer     the answer
     * @param userId     the user ID
     * @return the response as a string
     */
    @GetMapping("/response")
    public final String answer(@RequestParam final long questionId, @RequestParam final Boolean answer,
                               @RequestParam final long userId) {
        String response;

        if (answer.equals(Boolean.TRUE)) {
            // Add points
            response = "Bravo ! vous avez trouvé ! ";
        } else {
            // Do not add points
            response = "Oops ! Ca n'est pas correcte";
        }
        return response;
    }

    /**
     * updateTagFromQuestion method.
     *
     * @param questionId the question id
     * @param nameTag    the tag name
     */
    @PostMapping("/updateTagFromQuestion")
    private void updateTagFromQuestion(
            @RequestParam final long questionId,
            @RequestParam final String nameTag) {
        final Question question = questionRepository.findQuestionById(questionId);
        questionService.setTag(question, nameTag);
        questionRepository.save(question);
    }

    /**
     * updateTagFromQuestion method.
     *
     * @param questionId    a question ID
     * @param content       a content
     * @param correctAnswer the correct answer
     */
    @PostMapping("/updateQuestionById")
    private void updateTagFromQuestion(
            @RequestParam final long questionId,
            @RequestParam final String content,
            @RequestParam final String correctAnswer) {
        final Question question = questionRepository.findQuestionById(questionId);
        questionService.updateTag(question, content, correctAnswer);
        questionRepository.save(question);
    }

    /**
     * updateUserAnswerById method.
     *
     * @param userAnswerId  a user answer ID
     * @param points        the points
     * @param correctAnswer the correct answer
     * @param response      the response
     */
    @PostMapping("/updateUserAnswerById")
    private void updateUserAnswerById(@RequestParam final long userAnswerId,
                                      @RequestParam final Integer points,
                                      @RequestParam final boolean correctAnswer,
                                      @RequestParam final String response) {
        final UserAnswer userAnswer = userAnswerRepository.findUserAnswerById(userAnswerId);
        userAnswerService.updateUserAnswerById(userAnswer, points, correctAnswer, response);
        userAnswerRepository.save(userAnswer);
    }

    /**
     * updatePendingResponseAnswerById method.
     *
     * @param userAnswerId  a user answer ID
     * @param points        points
     * @param correctAnswer the correct answer
     * @param response      the response
     */
    @PostMapping("/updatePendingResponseAnswerById")
    private void updatePendingResponseAnswerById(
            @RequestParam final long userAnswerId,
            @RequestParam final Integer points,
            @RequestParam final boolean correctAnswer,
            @RequestParam final String response) {
        final UserAnswer userAnswer = userAnswerRepository.findUserAnswerById(userAnswerId);
        userAnswerService.updateUserAnswerById(userAnswer, points, correctAnswer, response);
        userAnswerRepository.save(userAnswer);
    }

    /**
     * deleteTagFromQuestion method.
     *
     * @param questionId a question ID
     */
    @PostMapping("/deleteTagFromQuestion")
    private void deleteTagFromQuestion(@RequestParam final long questionId) {
        final Question question = questionRepository.findQuestionById(questionId);
        questionService.deleteTag(question);
        questionRepository.save(question);
    }

    /**
     * deleteTagById method.
     *
     * @param idQuestion a question ID
     */
    @DeleteMapping("/deleteQuestionById")
    public final void deleteTagById(@RequestParam final long idQuestion) {
        questionRepository.deleteById(idQuestion);
    }

    /**
     * deleteUserAnswerById method.
     *
     * @param userAnswerId a user answer ID
     */
    @DeleteMapping("/deleteUserAnswerById")
    public final void deleteUserAnswerById(@RequestParam final long userAnswerId) {
        questionRepository.deleteById(userAnswerId);
    }

    /**
     * findQuestionByTagName method.
     *
     * @param tagName a tag name
     * @return a list of Question
     */
    @GetMapping("/findQuestionByTagName")
    public final List<QuestionDTO> findQuestionByTagName(@RequestParam final String tagName) {
        return QuestionMapper.toDTO(questionService.findQuestionsByTag(tagName));
    }

    /**
     * userAnswer method.
     *
     * @param idUser     a user ID
     * @param idQuestion a question ID
     * @param response   the response
     * @return a string result if user has earned points or not
     */
    @PostMapping("/userAnswer")
    public final String userAnswer(
            @RequestParam final long idUser,
            @RequestParam final long idQuestion,
            @RequestParam final String response) {
        final Question question = questionRepository.findQuestionById(idQuestion);

        final PendingResponse pending = pendingResponseService.checkUserPendingResponse(idUser);
        String result = "";
        if (pending.getStatus() == Status.WAITING_FOR_ANSWER && pending.getIdUser() == idUser) {
            final UserAnswer userAnswer =
                    userAnswerRepository.findByIdUserAndIdQuestionAndResponse(idUser, idQuestion, "");
            userAnswer.setResponse(response);
            userAnswerService.setPoints(userAnswer, question, pending);
            userAnswerRepository.save(userAnswer);
            pendingResponseRepository.save(pending);
            result = "You have earned : " + userAnswer.getPoints() + " points";
        } else {
            final UserAnswer userAnswer = userAnswerService.initialiseUserAnswer(idUser, idQuestion, response);
            final PendingResponse pendingResponse = pendingResponseService.initialiseUserAnswer(idUser, idQuestion);
            userAnswerService.setPoints(userAnswer, question, pendingResponse);
            userAnswerRepository.save(userAnswer);
            pendingResponseRepository.save(pendingResponse);
            result = "You have earned : " + userAnswer.getPoints() + " points";
        }
        return result;
    }

    @PostMapping("createQuestion")
    public void createQuestion(@RequestBody QuestionDTO questionDTO) {
        questionEventService.createQuestion(questionDTO);
    }

    @GetMapping("getAllQuestions")
    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        var result = questionRepository.findAll();
        result.forEach(element -> questions.add(element));

        return QuestionMapper.toDTO(questions);
    }
}
