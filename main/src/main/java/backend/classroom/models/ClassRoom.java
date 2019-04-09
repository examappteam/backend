package backend.classroom.models;

import backend.course.models.Course;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    private String className;

    @OneToMany
    private Set<UpcomingExam> upcomingExams;

    @ManyToMany
    private Set<Course> courses;

    @ManyToMany
    private Set<Student> students;

    @ManyToMany
    private Set<Teacher> teacher;
}
