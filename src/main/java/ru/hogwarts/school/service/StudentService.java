package ru.hogwarts.school.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Long id, Student student) {
        return studentRepository.findById(id).map(studentForUpdate -> {
            studentForUpdate.setName(student.getName());
            studentForUpdate.setAge(student.getAge());
            studentRepository.save(studentForUpdate);
            return studentForUpdate;
        }).orElse(null);

    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> printStudentsOfCertainAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    public List<Student> printStudentsOfAgeBetween(int minAge, int maxAge) {
        return studentRepository.findAllByAgeBetween(minAge, maxAge);
    }

    public Faculty getFacultyOfStudent(long studentId) {
        return studentRepository.findById(studentId)
                .map(Student::getFaculty)
                .orElse(null);
    }

    public Integer getCountAllStudents() {
        return studentRepository.getCountAllStudents();
    }

    public double getAverageAge() {
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastFiveStudents() {
        return studentRepository.getLastFiveStudents();
    }

    public List<Student> getAllStudent(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return studentRepository.findAll(pageRequest).getContent();
    }

    public List<Student> getAllByName(String name) {
        return studentRepository.findAllByName(name);
    }
}
