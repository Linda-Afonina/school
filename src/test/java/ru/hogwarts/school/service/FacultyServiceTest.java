package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FacultyServiceTest {

    FacultyService out = new FacultyService();

    @Test
    public void shouldCreateCurrentFaculty() {
        Faculty expectedFaculty = new Faculty(1, "Griffindor", "Green");
        Faculty actualFaculty = out.createFaculty(expectedFaculty);

        assertEquals(expectedFaculty, actualFaculty);
    }

    @Test
    public void shouldGetFacultyById() {
        Faculty expectedFaculty = new Faculty(1, "Griffindor", "Green");
        out.createFaculty(expectedFaculty);
        Faculty actualFaculty = out.getFacultyById(expectedFaculty.getId());

        assertEquals(expectedFaculty, actualFaculty);
    }

    @Test
    public void shouldUpdateFaculty() {
        Faculty initialFaculty = new Faculty(1, "Griffindor", "Green");
        Faculty expectedFaculty = new Faculty(1, "Griffindor", "Blue");
        out.createFaculty(initialFaculty);
        Faculty actualFaculty = out.updateFaculty(1L, expectedFaculty);

        assertEquals(expectedFaculty, actualFaculty);
    }

    @Test
    public void shouldDeleteFaculty() {
        Faculty expectedFaculty = new Faculty(1, "Griffindor", "Green");
        out.createFaculty(expectedFaculty);
        Faculty actualFaculty = out.deleteFacultyById(expectedFaculty.getId());

        assertEquals(expectedFaculty, actualFaculty);
    }
}
