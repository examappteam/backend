package backend.classroom.models;

import backend.course.models.Course;
import backend.shared.models.User;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String name;

    @OneToMany
    public List<Course> courses;

    @ManyToMany
    public List<User> students;

    @OneToOne
    public User teacher;
}
