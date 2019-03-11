package backend.exam.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExamController {

    @PostMapping("/exam")
    public ResponseEntity<?> create(){
        // todo: implement
        return null;
    }

    @GetMapping("/exam/{id}")
    public ResponseEntity<?> getOne(@PathVariable long id){
        // todo: implement
        return null;
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
