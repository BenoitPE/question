package poller.questionContext.application.commands;

public class EvaluateQuestionCommand {
    private long questionId;
    private String answer;

    public EvaluateQuestionCommand() {
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
