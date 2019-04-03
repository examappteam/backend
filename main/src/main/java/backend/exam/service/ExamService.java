package backend.exam.service;

import backend.exam.models.ExamWrapper;
import org.springframework.http.ResponseEntity;

public interface ExamService {
    void createExam(String classnumber, ExamWrapper newExam);

    ResponseEntity<?> startExam(String classnumber);

    void joinExam(String classnumber, String student);

    void leaveExam(String classnumber, String student);
}
