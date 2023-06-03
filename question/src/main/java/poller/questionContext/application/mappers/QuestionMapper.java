package poller.questionContext.application.mappers;

import poller.questionContext.application.dtos.QuestionDTO;
import poller.questionContext.domain.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionMapper {
    public static QuestionDTO toDTO(Question question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setContent(question.getContent());
        dto.setCorrectAnswer(question.getCorrectAnswer());
        dto.setIdTag(question.getIdTag());
        return dto;
    }

    public static List<QuestionDTO> toDTO(List<Question> questions) {
        List<QuestionDTO> dtos = new ArrayList<>();
        for (Question question: questions) {
            dtos.add(toDTO(question));
        }
        return dtos;
    }
}
