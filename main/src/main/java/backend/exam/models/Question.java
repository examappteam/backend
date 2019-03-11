package backend.exam.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public QuestionType type;

    public String question;

    public String answer;

//    @OneToMany
//    public List<String> multipleChoice;
}
