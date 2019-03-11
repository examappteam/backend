package backend.course.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    @PostMapping("/course")
    public ResponseEntity<?> create(){
        // todo: implement
        return null;
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<?> getOne(@PathVariable long id){
        // todo: implement
        return null;
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<?> update(@PathVariable long id){
        // todo: implement
        return null;
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        // todo: implement
        return null;
    }
}