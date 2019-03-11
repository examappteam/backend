package backend.course.models;

import backend.exam.models.Exam;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;

    public String name;

    @OneToMany
    public List<Exam> exams;
}
