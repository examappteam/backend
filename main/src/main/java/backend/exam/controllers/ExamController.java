package backend.exam.controllers;

import backend.exam.models.Exam;
import backend.exam.models.ExamResource;
import backend.exam.repository.ExamRepository;
import backend.exam.repository.QuestionRepository;
import backend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExamController {

    @Autowired
    private ExamRepository examRepo;
    @Autowired
    private QuestionRepository questionRepo;

    @PostMapping("/exam")
    public ResponseEntity<?> create(){

        // todo: implement
        

        return null;
    }

    @GetMapping("/exam/{id}")
    public ResponseEntity<?> getOne(@PathVariable long id){
        // todo: remove dummy Exam. But this works for requesting id 1, of course.
        examRepo.save(new Exam());
        return examRepo.findById(id).map(s -> ResponseEntity.ok(new ExamResource(s)))
                .orElseThrow(() -> new ResourceNotFoundException(Exam.class));
    }

    @PutMapping("/exam/{id}")
    public ResponseEntity<?> update(@PathVariable long id){
        // todo: implement
        return null;
    }

    @DeleteMapping("/exam/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        // todo: implement
        return null;
    }

    @PutMapping("/exam/{id}/student")
    public ResponseEntity<?> updateStudent(@PathVariable long id){
        // todo: implement
        return null;
    }

    @PostMapping("/exam/{id}/student")
    public ResponseEntity<?> deleteStudent(@PathVariable long id){
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