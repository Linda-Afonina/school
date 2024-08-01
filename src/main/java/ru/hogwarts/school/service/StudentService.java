package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    Map<Long, Student> listOfStudents = new HashMap<>();
    Long generatedStudentId = 0L;

    public Student createStudent(Student student) {
        student.setId(++generatedStudentId);
        listOfStudents.put(generatedStudentId, student);
        return student;
    }

    public Student getStudentById(Long id) {
        return listOfStudents.get(id);
    }

    public Student updateStudent(Long id, Student student) {
        Student studentForUpdate = listOfStudents.get(id);
        studentForUpdate.setAge(student.getAge());
        studentForUpdate.setName(student.getName());
        return studentForUpdate;
    }

    public Student deleteStudentById(Long id) {
        return listOfStudents.remove(id);
    }

    public List<Student> printStudentsOfCertainAge(int age) {
        return listOfStudents.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}
