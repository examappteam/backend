package backend.exam.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ExamDTO {
    @NotNull
    private Long creatorId;

    @NotNull @NotEmpty
    private List<QuestionDTO> questionDTOs;

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public List<QuestionDTO> getQuestionDTOs() {
        return questionDTOs;
    }

    public void setQuestionDTOs(List<QuestionDTO> questionDTOs) {
        this.questionDTOs = questionDTOs;
    }

    public Exam toExam() {
        Exam result = new Exam();
        result.setCreatorId(this.creatorId);

        List<Question> questions = QuestionDTO.mapList(this.questionDTOs);
        result.setQuestions(questions);

        return result;
    }
}
