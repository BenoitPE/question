package poller.questionContext.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import poller.questionContext.application.dtos.QuestionDTO;
import poller.questionContext.domain.events.QuestionCreatedEvent;
import poller.questionContext.domain.repository.QuestionRepository;
import poller.questionContext.domain.service.impl.QuestionServiceImpl;

@Service
public class QuestionEventService extends QuestionServiceImpl {
    private final ApplicationEventPublisher eventPublisher;

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionEventService(ApplicationEventPublisher eventPublisher, QuestionRepository questionRepository) {
        super(questionRepository);
        this.eventPublisher = eventPublisher;
        this.questionRepository = questionRepository;
    }

    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        QuestionDTO dto = super.createQuestion(questionDTO);

        QuestionCreatedEvent event = new QuestionCreatedEvent(this, dto.getId(), dto.getContent());
        eventPublisher.publishEvent(event);

        return dto;
    }
}