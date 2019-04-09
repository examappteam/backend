package backend.classroom.controllers;

import backend.classroom.models.UpcomingExam;
import backend.classroom.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClassRoomController {

    private final ClassRoomService classRoomService;

    @Autowired
    public ClassRoomController(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleNaN() {
        return new ResponseEntity<>("Could not parse id.", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/classroom")
    public ResponseEntity<?> create(){
        // todo: implement
        return null;
    }

    // no authorization: always allowed
    @GetMapping("/classroom/{id}")
    public ResponseEntity<?> getOne(@PathVariable long id){
        return ResponseEntity.ok(classRoomService.getOne(id)); // bubbled exception auto-generates an appropriate response
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/classroom/{id}")
    public ResponseEntity<?> update(@PathVariable long id){
        // todo: implement
        return null;
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/classroom/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        classRoomService.delete(id);
        return ResponseEntity.ok().body(String.format("ClassRoom %s deleted.", id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    @PutMapping("/classroom/{id}/student")
    public ResponseEntity<?> addStudent(@PathVariable long id){
        // todo: implement
        return null;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/classroom/{id}/student")
    public ResponseEntity<?> deleteStudent(@PathVariable long id){
        // todo: implement
        return null;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping("/classroom/{id}/upcoming-exam")
    public ResponseEntity<?> addUpcomingExam(@RequestBody UpcomingExam exam){
        // todo: implement
        return null;
    }

}