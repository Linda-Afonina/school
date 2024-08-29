package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student-by-faculties")
public class StudentByFacultiesController {

    public final StudentService studentService;

    public StudentByFacultiesController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("count-of-all-students")
    public Integer getCountAllStudents() {
        return studentService.getCountAllStudents();
    }

    @GetMapping("average-age")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("get-last-five-students")
    public List<Student> getLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }
}
