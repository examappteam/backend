package backend.exam.controllers;

import backend.exam.models.Exam;
import backend.exam.models.ExamDTO;
import backend.exam.models.ExamResource;
import backend.exam.models.QuestionDTO;
import backend.exam.repository.ExamRepository;
import backend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
public class ExamController {

    @Autowired
    private ExamRepository examRepo;

    @PostMapping("/exam")
    public ResponseEntity<?> create(@Valid @RequestBody ExamDTO examDTO){

        // TODO: once authorization works, limit this to teacher role.

        // note: creator id is not checked for Teacher role, because people say it goes against the microservice architecture.
        // This creates a vulnerability: a user logged in as a Teacher can create exams with incorrect creatorId.

        Exam exam = examRepo.save(examDTO.toExam());
        return ResponseEntity.ok().body(new ExamResource(exam));
    }

    @GetMapping("/exam/{id}")
    public ResponseEntity<?> getOne(@PathVariable long id){

        // TODO: once authorization works, limit this to teacher role.

        return examRepo.findById(id).map(s -> ResponseEntity.ok(new ExamResource(s)))
                .orElseThrow(() -> new ResourceNotFoundException(Exam.class));
    }

    @PutMapping("/exam/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ExamDTO examDTO){

        // TODO: once authorization works, limit this to teacher role.

        // note: creator id is not checked for Teacher role, because people say it goes against the microservice architecture.
        // This creates a vulnerability: a user logged in as a Teacher can update ANY exam and lie about who did it.

        Optional<Exam> retrieved = examRepo.findById(id);
        Exam exam;
        if(retrieved.isPresent()) {
            // update if present
            exam = retrieved.get();
            if(!Objects.equals(exam.getCreatorId(), examDTO.getCreatorId())) {
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
    public ResponseEntity<?> delete(@PathVariable long id){

        // TODO: once authorization works, limit this to teacher role.

        // note: creator id is not checked for Teacher role, because people say it goes against the microservice architecture.
        // This creates a vulnerability: a user logged in as a Teacher can delete ANY exam.

        examRepo.deleteById(id);
        return ResponseEntity.ok().body(String.format("Exam %s deleted.", id));
    }

    @PutMapping("/exam/{id}/student")
    public ResponseEntity<?> updateStudent(@PathVariable long id){

        // TODO: once authorization works, limit this to teacher role.

        // todo: implement
        return null;
    }

    @PostMapping("/exam/{id}/student")
    public ResponseEntity<?> deleteStudent(@PathVariable long id){

        // TODO: once authorization works, limit this to teacher role.

        // todo: implement
        return null;
    }
}
