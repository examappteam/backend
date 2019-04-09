package backend.exam.models;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ExamWrapper {
    private Set<String> currentStudents;
    private Exam exam;
    private boolean started;

    public ExamWrapper(Exam exam) {
        currentStudents = new HashSet<>();
        this.exam = exam;
    }

    public void addStudent(String student){
        this.currentStudents.add(student);
    }

    public void removeStudent(String student){
        if(currentStudents.contains(student)) currentStudents.remove(student);
    }

    public void startExam(){
        started = true;
    }

    public Exam getExam() {
        return exam;
    }
}
