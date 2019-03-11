package backend.exam.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;

    @OneToMany
    public List<Question> questions;

}