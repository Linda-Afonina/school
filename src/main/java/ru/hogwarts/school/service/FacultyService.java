package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long id) {
        logger.debug("Was invoked method for get faculty by id: {}", id);
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        logger.debug("Faculty by id: {} is {}", id, faculty);
        return faculty;
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        logger.info("Was invoked method for update faculty");
        return facultyRepository.findById(id).map(facultyForUpdate -> {
            facultyForUpdate.setName(faculty.getName());
            facultyForUpdate.setColor(faculty.getColor());
            facultyRepository.save(facultyForUpdate);
            return facultyForUpdate;
        }).orElse(null);
    }

    public void deleteFacultyById(Long id) {
        logger.warn("Faculty by id {} will be deleted", id);
        facultyRepository.deleteById(id);
    }

    public List<Faculty> printFacultiesOfCertainColor(String color) {
        logger.info("Was invoked method for print faculties of color: {}", color);
        return facultyRepository.findAllByColor(color);
    }

    public List<Faculty> printFacultiesOfCertainNameOrCertainColorIgnoreCase(String name, String color) {
        logger.info("Was invoked method for print faculties of name {} or color {} ignore case", name, color);
        return facultyRepository.findAllByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public Collection<Student> printStudentsOfFaculty(long facultyId) {
        logger.debug("Was invoked method for print students of faculty by id: {}", facultyId);
        Collection<Student> students = facultyRepository.findById(facultyId)
                .map(Faculty::getStudentsOfFaculty)
                .orElse(null);
        logger.debug("Students of faculty {} are {}", facultyId, students);
        return students;
    }

    public List<Faculty> getAllByNameAndColor(String name, String color) {
        logger.info("Was invoked method for get all faculties by name {} and color {}", name, color);
        return facultyRepository.findAllByNameAndColor(name, color);
    }

    public String getLongestNameOfFaculty() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse("Факультеты не найдены");
    }
}
