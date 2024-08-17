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
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void clear() {
        facultyRepository.deleteAll();
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(facultyRepository).isNotNull();
    }

    @Test
    public void testGetFacultyById() throws Exception {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty", String.class))
                .isNotNull();
    }

    @Test
    public void testPostFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(2);
        faculty.setName("Slytherin");
        faculty.setColor("grey");

        ResponseEntity<Faculty> facultyResponseEntity = testRestTemplate.postForEntity
                ("http://localhost:" + port + "/faculty", faculty, Faculty.class);

        assertNotNull(facultyResponseEntity);
        assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));
        Faculty actualFaculty = facultyResponseEntity.getBody();
        assertNotNull(actualFaculty.getId());
        assertThat(actualFaculty.getName()).isEqualTo(faculty.getName());
        assertThat(actualFaculty.getColor()).isEqualTo(faculty.getColor());
    }

    @Test
    public void testPutFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Slytherin");
        faculty.setColor("grey");
        facultyRepository.save(faculty);

        Faculty facultyForUpdate = new Faculty("Slytherin", "grey");

        HttpEntity<Faculty> facultyHttpEntity = new HttpEntity<>(facultyForUpdate);
        ResponseEntity<Faculty> facultyResponseEntity = testRestTemplate
                .exchange("http://localhost:" + port + "/faculty/" + faculty.getId(),
                        HttpMethod.PUT, facultyHttpEntity, Faculty.class);

        assertNotNull(facultyResponseEntity);
        assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));
        Faculty actualFaculty = facultyResponseEntity.getBody();
        assertEquals(actualFaculty.getId(), faculty.getId());
        assertEquals(actualFaculty.getName(), faculty.getName());
        assertEquals(actualFaculty.getColor(), faculty.getColor());
    }

    @Test
    public void testDeleteFaculty() {
        Faculty faculty = new Faculty();
        faculty.setId(2);
        faculty.setName("Slytherin");
        faculty.setColor("grey");
        facultyRepository.save(faculty);

        ResponseEntity<Faculty> facultyResponseEntity = testRestTemplate
                .exchange("http://localhost:" + port + "/faculty/" + faculty.getId(),
                        HttpMethod.DELETE, null, Faculty.class);

        assertNotNull(facultyResponseEntity);
        assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));
        assertThat(facultyRepository.findById(faculty.getId())).isNotPresent();
    }
}
