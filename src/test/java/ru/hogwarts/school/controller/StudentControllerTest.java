package ru.hogwarts.school.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void clear() {
        studentRepository.deleteAll();
    }

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() throws Exception {
        assertThat(studentRepository).isNotNull();
    }

    @Test
    public void testGetStudentById() throws Exception {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setId(2);
        student.setName("Harry Potter");
        student.setAge(25);

        ResponseEntity<Student> studentResponseEntity = testRestTemplate.postForEntity
                ("http://localhost:" + port + "/student", student, Student.class);

        assertNotNull(studentResponseEntity);
        assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));
        Student actualStudent = studentResponseEntity.getBody();
        assertNotNull(actualStudent.getId());
        assertThat(actualStudent.getName()).isEqualTo(student.getName());
        assertThat(actualStudent.getAge()).isEqualTo(student.getAge());
    }

    @Test
    public void testPutStudent() throws Exception {
        Student student = new Student();
        student.setName("Harry Potter");
        student.setAge(25);
        studentRepository.save(student);

        Student studentForUpdate = new Student("Harry Potter", 25);

        HttpEntity<Student> studentHttpEntity = new HttpEntity<>(studentForUpdate);
        ResponseEntity<Student> studentResponseEntity = testRestTemplate
                .exchange("http://localhost:" + port + "/student/" + student.getId(),
                        HttpMethod.PUT, studentHttpEntity, Student.class);

        assertNotNull(studentResponseEntity);
        assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));
        Student actualStudent = studentResponseEntity.getBody();
        assertEquals(actualStudent.getId(), student.getId());
        assertEquals(actualStudent.getName(), student.getName());
        assertEquals(actualStudent.getAge(), student.getAge());
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student();
        student.setId(2);
        student.setName("Harry Potter");
        student.setAge(25);
        studentRepository.save(student);

        ResponseEntity<Student> studentResponseEntity = testRestTemplate
                .exchange("http://localhost:" + port + "/student/" + student.getId(),
                        HttpMethod.DELETE, null, Student.class);

        assertNotNull(studentResponseEntity);
        assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));
        assertThat(studentRepository.findById(student.getId())).isNotPresent();
    }
}
