package backend.exam.controllers;

import lombok.Getter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class LeaveExam {
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private Date date = new Date();
    private String content;
    private String student;
    private String time = dateFormat.format(date);

    @Override
    public String toString() {
        return time + ": " + student + ": " + content;
    }
}
