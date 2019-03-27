package backend.exam.models;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ExamDTO {
    private String creatorId;

    @NotNull
    private List<QuestionDTO> questionDTOs;

    private String title;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public List<QuestionDTO> getQuestionDTOs() {
        return questionDTOs;
    }

    public void setQuestionDTOs(List<QuestionDTO> questionDTOs) {
        this.questionDTOs = questionDTOs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Exam toExam() {
        Exam result = new Exam();
        result.setCreatorId(this.creatorId);
        result.setTitle(this.title);

        List<Question> questions = QuestionDTO.mapList(this.questionDTOs);
        result.setQuestions(questions);

        return result;
    }
}
