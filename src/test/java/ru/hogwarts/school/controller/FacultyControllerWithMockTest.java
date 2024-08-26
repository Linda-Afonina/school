package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(FacultyController.class)
public class FacultyControllerWithMockTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void testGetFaculty() throws Exception {
        long facultyId = 1;
        Faculty faculty = new Faculty();
        faculty.setName("Slytherin");
        faculty.setColor("red");
        facultyRepository.save(faculty);

        when(facultyService.getFacultyById(facultyId)).thenReturn(faculty);

        ResultActions resultActions = mockMvc.perform(get("/faculty/{id}", facultyId));

        resultActions
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()))
                .andDo(print());
    }

    @Test
    public void testPostFaculty() throws Exception {
        Long facultyId = 1L;
        Faculty faculty = new Faculty();
        faculty.setName("Slytherin");
        faculty.setColor("green");
        facultyRepository.save(faculty);
        Faculty savedFaculty = new Faculty("Slytherin", "green");
        savedFaculty.setId(facultyId);

        when(facultyService.createFaculty(faculty)).thenReturn(savedFaculty);

        ResultActions resultActions = mockMvc.perform(post("/faculty")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faculty)));

        resultActions
                .andExpect(jsonPath("$.id").value(savedFaculty.getId()))
                .andExpect(jsonPath("$.name").value(savedFaculty.getName()))
                .andExpect(jsonPath("$.color").value(savedFaculty.getColor()))
                .andDo(print());
    }

    @Test
    public void testPutFaculty() throws Exception {
        long facultyId = 1;
        Faculty faculty = new Faculty();
        faculty.setName("Slytherin");
        faculty.setColor("grey");

        when(facultyService.updateFaculty(facultyId, faculty)).thenReturn(faculty);

        ResultActions resultActions = mockMvc.perform(put("/faculty/{id}", facultyId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faculty)));

        resultActions
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()))
                .andDo(print());
    }
}
