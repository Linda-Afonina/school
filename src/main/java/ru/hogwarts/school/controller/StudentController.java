package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collections;
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
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        Student foundStudent = studentService.updateStudent(id, student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable("id") long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Student>> printStudentsOfCertainAge(@RequestParam("age") int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.printStudentsOfCertainAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("byAgeBetween")
    public ResponseEntity<List<Student>> printStudentsOfAgeBetween(@RequestParam("min") int minAge,
                                                                   @RequestParam("max") int maxAge) {
        if (minAge > 0 && maxAge > 0) {
            return ResponseEntity.ok(studentService.printStudentsOfAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("{id}/faculty")
    public Faculty getFacultyOfStudent(@PathVariable("id") long id) {
        return studentService.getFacultyOfStudent(id);
    }

    @GetMapping("get-all-students")
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam("page") Integer pageNumber,
                                                        @RequestParam("size") Integer pageSize) {
        return ResponseEntity.ok(studentService.getAllStudent(pageNumber, pageSize));
    }

}


