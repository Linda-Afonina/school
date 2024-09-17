package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;
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
    public ResponseEntity<Faculty> getFacultyById(@PathVariable("id") long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping("{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable("id") long id, @RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.updateFaculty(id, faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFacultyById(@PathVariable("id") long id) {
        facultyService.deleteFacultyById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> printFacultiesOfCertainColor(@RequestParam("color") String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.printFacultiesOfCertainColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("byNameIgnoreCaseOrColorIgnoreCase")
    public ResponseEntity<List<Faculty>> printFacultiesOfCertainNameOrCertainColorIgnoreCase(
            @RequestParam(required = false, value = "name") String name,
            @RequestParam(required = false, value = "color") String color
    ) {
        if ((name != null && !name.isBlank()) || (color != null && !color.isBlank())) {
            return ResponseEntity.ok(facultyService.printFacultiesOfCertainNameOrCertainColorIgnoreCase(name, color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("{id}/students")
    public Collection<Student> printStudentsOfFaculty(@PathVariable("id") long facultyId) {
        return facultyService.printStudentsOfFaculty(facultyId);
    }

    @GetMapping("{name}/{color}")
    public List<Faculty> getAllByNameAndColor(@PathVariable("name") String name, @PathVariable("color") String color) {
        List<Faculty> facultyList = facultyService.getAllByNameAndColor(name, color);
        return ResponseEntity.ok(facultyList).getBody();
    }

    @GetMapping("faculty-with-longest-name")
    public String getLongestNameOfFaculty() {
        return facultyService.getLongestNameOfFaculty();
    }
}
