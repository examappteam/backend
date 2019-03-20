package backend.exam.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class QuestionDTO {
    @NotNull @NotEmpty
    private String description;

    @NotNull
    private String answer;
}
