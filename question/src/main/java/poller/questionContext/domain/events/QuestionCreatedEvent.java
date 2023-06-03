package poller.questionContext.domain.events;

import org.springframework.context.ApplicationEvent;

public class QuestionCreatedEvent extends ApplicationEvent {
    private long questionId;
    private String questionContent;

    public QuestionCreatedEvent(Object source, long questionId, String questionContent) {
        super(source);
        this.questionId = questionId;
        this.questionContent = questionContent;
    }

    public long getQuestionId() {
        return questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }
}
