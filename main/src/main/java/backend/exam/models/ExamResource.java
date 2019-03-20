package backend.exam.models;

import backend.exam.controllers.ExamController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ExamResource extends ResourceSupport {

    private final Exam exam;

    public ExamResource(Exam exam){
        this.exam = exam;
        final long id = exam.getId();

        add(linkTo(methodOn(ExamController.class).getOne(id)).withSelfRel());
    }

    public Exam getExam() {
        return exam;
    }
}

