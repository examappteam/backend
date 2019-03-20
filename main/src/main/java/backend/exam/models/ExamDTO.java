package backend.exam.models;

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
}
