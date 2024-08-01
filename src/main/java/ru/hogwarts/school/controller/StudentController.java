package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{id}")
    public Student getStudentById(@PathVariable("id") long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("{id}")
    public Student updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("{id}")
    public Student deleteStudentById(@PathVariable("id") long id) {
        return studentService.deleteStudentById(id);
    }

    @GetMapping
    public List<Student> printStudentsOfCertainAge(@RequestParam("age") int age) {
        return studentService.printStudentsOfCertainAge(age);
    }
}
