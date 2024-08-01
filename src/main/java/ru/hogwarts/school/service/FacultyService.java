package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    Map<Long, Faculty> listOfFaculties = new HashMap<>();
    Long generatedFacultyId = 0L;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++generatedFacultyId);
        listOfFaculties.put(generatedFacultyId, faculty);
        return faculty;
    }

    public Faculty getFacultyById(Long id) {
        return listOfFaculties.get(id);
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        Faculty facultyForUpdate = listOfFaculties.get(id);
        facultyForUpdate.setName(faculty.getName());
        facultyForUpdate.setColor(faculty.getColor());
        return facultyForUpdate;
    }

    public Faculty deleteFacultyById(Long id) {
        return listOfFaculties.remove(id);
    }

    public List<Faculty> printFacultiesOfCertainColor(String color) {
        return listOfFaculties.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
