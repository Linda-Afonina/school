package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        return facultyRepository.findById(id).map(facultyForUpdate -> {
            facultyForUpdate.setName(faculty.getName());
            facultyForUpdate.setColor(faculty.getColor());
            facultyRepository.save(facultyForUpdate);
            return facultyForUpdate;
        }).orElse(null);
    }

    public void deleteFacultyById(Long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> printFacultiesOfCertainColor(String color) {
        return facultyRepository.findAllByColor(color);
    }

    public List<Faculty> printFacultiesOfCertainNameOrCertainColorIgnoreCase(String name, String color) {
        return facultyRepository.findAllByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public Collection<Student> printStudentsOfFaculty(long facultyId) {
        return facultyRepository.findById(facultyId)
                .map(Faculty::getStudentsOfFaculty)
                .orElse(null);
    }

    public List<Faculty> getAllByNameAndColor(String name, String color) {
        return facultyRepository.findAllByNameAndColor(name, color);
    }
}
