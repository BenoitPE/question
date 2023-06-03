package poller.questionContext.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import poller.questionContext.application.dtos.QuestionDTO;
import poller.questionContext.domain.events.QuestionCreatedEvent;
import poller.questionContext.domain.model.Question;
import poller.questionContext.domain.repository.QuestionRepository;

@Service
public class QuestionEventService {
    private final ApplicationEventPublisher eventPublisher;

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionEventService(ApplicationEventPublisher eventPublisher, QuestionRepository questionRepository) {
        this.eventPublisher = eventPublisher;
        this.questionRepository = questionRepository;
    }

    public void createQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setContent(questionDTO.getContent());
        question.setCorrectAnswer(questionDTO.getCorrectAnswer());
        questionRepository.save(question);

        QuestionCreatedEvent event = new QuestionCreatedEvent(this, question.getId(), question.getContent());
        eventPublisher.publishEvent(event);
    }
}