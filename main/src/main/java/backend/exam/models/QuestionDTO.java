package backend.exam.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class QuestionDTO {

    private int weightPercentage;

    @NotNull @NotEmpty
    private String description;

    @NotNull
    private String answer;

    public static List<Question> mapList(List<QuestionDTO> questionDTOs) {
        List<Question> converted = new ArrayList<>();
        for (QuestionDTO dto :
                questionDTOs) {
            converted.add(dto.toQuestion());
        }
        return converted;
    }

    private Question toQuestion() {
        Question result = new Question();
        result.setWeightPercentage(this.weightPercentage);
        result.setDescription(this.description);
        result.setAnswer(this.answer);
        return result;
    }

    public int getWeightPercentage() {
        return weightPercentage;
    }

    public void setWeightPercentage(int weightPercentage) {
        this.weightPercentage = weightPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
