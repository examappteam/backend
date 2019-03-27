package backend.classroom.models;

import backend.course.models.Course;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(unique=true)
    public String className;

    @ManyToMany
    public List<Course> courses;

    @ManyToMany
    public List<Student> students;

    @ManyToMany
    public List<Teacher> teacher;
}
