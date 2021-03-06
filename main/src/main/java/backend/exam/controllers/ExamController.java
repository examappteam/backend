package backend.exam.controllers;

import backend.exam.models.*;
import backend.exam.repository.ExamRepository;
import backend.exam.service.ExamService;
import backend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class ExamController {

    private final ExamRepository examRepo;
    private final ExamService service;



    @Autowired
    public ExamController(ExamRepository examRepo, ExamService service) {
        this.examRepo = examRepo;
        this.service = service;
    }

    @PostMapping("/exam")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<?> create(@Valid @RequestBody ExamDTO examDTO) {

        // note: creator id is not checked for Teacher role, because people say it goes against the microservice architecture.
        // This creates a vulnerability: a user logged in as a Teacher can create exams with incorrect creatorId.

        Exam exam = examRepo.save(examDTO.toExam());
        return ResponseEntity.ok().body(new ExamResource(exam));
    }

    @GetMapping("/exam/{id}")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<?> getOne(@PathVariable long id) {

        return examRepo.findById(id).map(s -> ResponseEntity.ok(new ExamResource(s)))
                .orElseThrow(() -> new ResourceNotFoundException(Exam.class));
    }

    @PutMapping("/exam/{id}")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ExamDTO examDTO) {

        // note: creator id is not checked for Teacher role, because people say it goes against the microservice architecture.
        // This creates a vulnerability: a user logged in as a Teacher can update ANY exam and lie about who did it.

        Optional<Exam> retrieved = examRepo.findById(id);
        Exam exam;
        if (retrieved.isPresent()) {
            // update if present
            exam = retrieved.get();
            if (!Objects.equals(exam.getCreatorId(), examDTO.getCreatorId())) {
                // Exam can only be edited by creator
                return ResponseEntity.badRequest().body("Exam can only be edited by creator");
            }
            exam.setCreatorId(examDTO.getCreatorId());
            exam.setQuestions(QuestionDTO.mapList(examDTO.getQuestionDTOs()));
        } else {
            // create if not present (default PUT behaviour is UPSERT)
            exam = examDTO.toExam();
        }
        examRepo.save(exam);
        return ResponseEntity.ok().body(new ExamResource(exam));
    }

    @DeleteMapping("/exam/{id}")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<?> delete(@PathVariable long id) {

        // note: creator id is not checked for Teacher role, because people say it goes against the microservice architecture.
        // This creates a vulnerability: a user logged in as a Teacher can delete ANY exam.

        examRepo.deleteById(id);
        return ResponseEntity.ok().body(String.format("Exam %s deleted.", id));
    }

    @PutMapping("/exam/{id}/student")
    public ResponseEntity<?> updateStudent(@PathVariable long id) {

        // todo: implement
        return null;
    }

    @PostMapping("/exam/{id}/student")
    public ResponseEntity<?> deleteStudent(@PathVariable long id) {

        // todo: implement
        return null;
    }


    @PostMapping("/exam/create/{classnumber}")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<?> createExam(@PathVariable String classnumber, Exam exam) {
        ExamWrapper newExam = new ExamWrapper(exam);
        service.createExam(classnumber,newExam);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/exam/start/{classnumber}")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<?> startExam(@PathVariable String classnumber) {
        return service.startExam(classnumber);

    }

}
