package backend.exam.controllers;

import backend.exam.models.JoinExam;
import backend.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ExamWSController {

    private final ExamService service;

    @Autowired
    public ExamWSController(ExamService service) {
        this.service = service;
    }

    @MessageMapping("/exam.{classnumber}.join")
    @SendTo("/topic/exam.{classnumber}")
    public void joinExam(@DestinationVariable("classnumber") String classnumber, JoinExam joinMessage){
        service.joinExam(classnumber,joinMessage.getStudent());
    }

    @MessageMapping("/exam.{classnumber}.leave")
    @SendTo("/topic/exam.{classnumber}")
    public void leaveExam(@DestinationVariable("classnumber") String classnumber, LeaveExam joinMessage){
        service.joinExam(classnumber,joinMessage.getStudent());
    }
}
