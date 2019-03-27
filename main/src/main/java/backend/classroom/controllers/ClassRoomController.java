package backend.classroom.controllers;

import backend.classroom.service.ClassRoomService;
import backend.classroom.service.ClassRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/classroom")
    public ResponseEntity<?> create(){
        // todo: implement
        return null;
    }

    // no authorization: always allowed
    @GetMapping("/classroom/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id){
//        try {
            return ResponseEntity.ok(classRoomService.getOne(id)); // bubbled exception auto-generates an appropriate response
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/classroom/{id}")
    public ResponseEntity<?> update(@PathVariable String id){
        // todo: implement
        return null;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/classroom/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        // todo: implement
        return null;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    @PutMapping("/classroom/{id}/student")
    public ResponseEntity<?> updateStudent(@PathVariable String id){
        // todo: implement
        return null;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/classroom/{id}/student")
    public ResponseEntity<?> deleteStudent(@PathVariable String id){
        // todo: implement
        return null;
    }
}