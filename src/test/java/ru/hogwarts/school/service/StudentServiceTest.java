package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentServiceTest {

    StudentService out = new StudentService();

    @Test
    public void shouldCreateCurrentStudent() {
        Student expectedStudent = new Student(1, "Hermione", 20);
        Student actualStudent = out.createStudent(expectedStudent);

        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    public void shouldGetStudentById() {
        Student expectedStudent = new Student(2, "Harry", 25);
        out.createStudent(expectedStudent);
        Student actualStudent = out.getStudentById(expectedStudent.getId());

        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    public void shouldUpdateStudent() {
        Student initialStudent = new Student(1, "Harry", 25);
        Student expectedStudent = new Student(1, "Harry ", 27);
        out.createStudent(initialStudent);
        Student actualStudent = out.updateStudent(1L, expectedStudent);

        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    public void shouldDeleteStudent() {
        Student expectedStudent = new Student(2, "Harry", 25);
        out.createStudent(expectedStudent);
        Student actualStudent = out.deleteStudentById(expectedStudent.getId());

        assertEquals(expectedStudent, actualStudent);
    }
}
