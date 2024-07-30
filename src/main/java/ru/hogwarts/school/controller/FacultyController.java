package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public Faculty getFacultyById(@PathVariable("id") long id) {
        return facultyService.getFacultyById(id);
    }

    @PutMapping("{id}")
    public Faculty updateFaculty(@PathVariable("id") long id, @RequestBody Faculty faculty) {
        return facultyService.updateFaculty(id, faculty);
    }

    @DeleteMapping("{id}")
    public Faculty deleteFacultyById(@PathVariable("id") long id) {
        return facultyService.deleteFacultyById(id);
    }

    @GetMapping
    public List<Faculty> printFacultiesOfCertainColor(@RequestParam("color") String color) {
        return facultyService.printFacultiesOfCertainColor(color);
    }
}
