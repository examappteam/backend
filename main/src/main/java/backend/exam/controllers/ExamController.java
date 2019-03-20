package backend.exam.controllers;

import backend.exam.models.Exam;
import backend.exam.models.ExamDTO;
import backend.exam.models.ExamResource;
import backend.exam.models.Question;
import backend.exam.models.QuestionDTO;
import backend.exam.repository.ExamRepository;
import backend.exam.repository.QuestionRepository;
import backend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

@RestController
public class ExamController {

    @Autowired
    private ExamRepository examRepo;
    @Autowired
    private QuestionRepository questionRepo;

    @PostMapping("/exam")
    public ResponseEntity<?> create(@Valid @RequestBody ExamDTO examDTO){

        // TODO: once authorization works, limit this to teacher role.

        // note: creator id is not checked, because that is against the microservice architecture. We assume it is correct.
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
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody ExamDTO examDTO){

        // TODO: once authorization works, limit this to teacher role.


        //todo: null check for retrieved

        Exam retrieved = examRepo.getOne(examDTO.getCreatorId());
        // like at create(), we do not check the creatorId...
        retrieved.setCreatorId(examDTO.getCreatorId());
        retrieved.setQuestions(QuestionDTO.mapList(examDTO.getQuestionDTOs()));
        // examRepo.save(retrieved);
        return ResponseEntity.ok().body(new ExamResource(retrieved));
    }

    @DeleteMapping("/exam/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){

        // TODO: once authorization works, limit this to teacher role.

        // todo: implement
        return null;
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


//    @GetMapping("/")
//    public ResponseEntity<Resources<StudentResource>> all(){
//        List<StudentResource> collection = repository.findAll().stream().map(StudentResource::new)
//                .collect(Collectors.toList());
//        Resources<StudentResource> resources = new Resources<>(collection);
//        String uriString = ServletUriComponentsBuilder.fromCurrentRequest()
//                .build().toUriString();
//        resources.add(new Link(uriString, "self"));
//        return ResponseEntity.ok(resources);
//    }
//
//
//    @PostMapping("/")
//    public ResponseEntity<StudentResource> post(@RequestBody Student student){
//        repository.save(student);
//        URI uri = MvcUriComponentsBuilder.fromController(getClass())
//                .path("/{id}").buildAndExpand(student.getId()).toUri();
//        return ResponseEntity.created(uri).body(new StudentResource(student));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<StudentResource> put(@RequestBody Student student, @PathVariable long id){
//        student.setId(id);
//        repository.save(student);
//        StudentResource resource = new StudentResource(student);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
//        return ResponseEntity.created(uri).body(resource);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable long id){
//        return repository.findById(id).map(s -> {
//            repository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }).orElseThrow(() -> new StudentNotFoundException(id));
//    }