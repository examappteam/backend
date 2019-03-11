package backend.classroom.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClassRoomController {

    @PostMapping("/classroom")
    public ResponseEntity<?> create(){
        // todo: implement
        return null;
    }

    @PreAuthorize("hasRole('ROLE_STUDENTT')")
    @GetMapping("/classroom/{id}")
    public String getOne(@PathVariable long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
        // todo: implement
    }

    @PutMapping("/classroom/{id}")
    public ResponseEntity<?> update(@PathVariable long id){
        // todo: implement
        return null;
    }

    @DeleteMapping("/classroom/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        // todo: implement
        return null;
    }

    @PutMapping("/classroom/{id}/student")
    public ResponseEntity<?> updateStudent(@PathVariable long id){
        // todo: implement
        return null;
    }

    @DeleteMapping("/classroom/{id}/student")
    public ResponseEntity<?> deleteStudent(@PathVariable long id){
        // todo: implement
        return null;
    }
}