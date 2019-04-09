package backend.exam.service;

import backend.exam.models.ExamWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExamServiceImpl implements ExamService {

    private Map<String, ExamWrapper> currentExams = new HashMap<>();

    @Override
    public void createExam(String classnumber, ExamWrapper newExam) {
        currentExams.put(classnumber,newExam);
    }

    @Override
    public ResponseEntity<?> startExam(String classnumber) {
        if (doesExamExist(classnumber)) return ResponseEntity.notFound().build();

        currentExams.get(classnumber).startExam();
        return ResponseEntity.ok().build();
    }

    @Override
    public void joinExam(String classnumber, String student){
        if (doesExamExist(classnumber)) return;
        currentExams.get(classnumber).addStudent(student);
    }

    @Override
    public void leaveExam(String classnumber, String student){
        if (doesExamExist(classnumber)) return;
        currentExams.get(classnumber).removeStudent(student);
    }




    private boolean doesExamExist(String classnumber){
        return currentExams.containsKey(classnumber) || currentExams.get(classnumber).getExam() == null;
    }

}
