package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        logger.debug("Was invoked method for get student by id: {}", id);
        Student student = studentRepository.findById(id).orElse(null);
        logger.debug("Student by id: {} is {}", id, student);
        return student;
    }

    public Student updateStudent(Long id, Student student) {
        logger.info("Was invoked method for update student with id: {}", id);
        return studentRepository.findById(id).map(studentForUpdate -> {
            studentForUpdate.setName(student.getName());
            studentForUpdate.setAge(student.getAge());
            studentRepository.save(studentForUpdate);
            return studentForUpdate;
        }).orElse(null);

    }

    public void deleteStudentById(Long id) {
        logger.warn("Student by id {} will be deleted", id);
        studentRepository.deleteById(id);
    }

    public List<Student> printStudentsOfCertainAge(int age) {
        logger.info("Was invoked method for print students of age: {}", age);
        return studentRepository.findAllByAge(age);
    }

    public List<Student> printStudentsOfAgeBetween(int minAge, int maxAge) {
        logger.info("Was invoked method for print students of age between {} and {}", minAge, maxAge);
        return studentRepository.findAllByAgeBetween(minAge, maxAge);
    }

    public Faculty getFacultyOfStudent(long studentId) {
        logger.debug("Was invoked method for get faculty of student with id {}", studentId);
        Faculty faculty = studentRepository.findById(studentId)
                .map(Student::getFaculty)
                .orElse(null);
        logger.debug("Faculty of student with id {} is {}", studentId, faculty);
        return faculty;
    }

    public Integer getCountAllStudents() {
        logger.info("Was invoked method for get count all students");
        return studentRepository.getCountAllStudents();
    }

    public double getAverageAge() {
        logger.info("Was invoked method for get average age from students");
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastFiveStudents() {
        logger.info("Was invoked method for get last five students");
        return studentRepository.getLastFiveStudents();
    }

    public List<Student> getAllStudent(Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method for get {} students from page {}", pageSize, pageNumber);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return studentRepository.findAll(pageRequest).getContent();
    }

    public List<Student> getAllByName(String name) {
        logger.info("Was invoked method for get all students by name {}", name);
        return studentRepository.findAllByName(name);
    }
}
