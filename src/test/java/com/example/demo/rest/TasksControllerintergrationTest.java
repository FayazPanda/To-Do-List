package com.example.demo.rest;

import com.example.demo.dto.TaskDTO;
import com.example.demo.persistence.domain.Tasks;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:todolist-schema.sql",
        "classpath:todolist-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TasksControllerintergrationTest {

    private final String URI = "/task";
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper jsonifier;
    @Autowired
    private ModelMapper mapper;

    private TaskDTO mapToDTO(Tasks tasks) {
        return this.mapper.map(tasks, TaskDTO.class);
    }

    @Test
    void createTest() throws Exception {
        TaskDTO testDTO = mapToDTO(new Tasks("Panda"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isCreated();

        TaskDTO testSavedDTO = mapToDTO(new Tasks(5L, "Panda"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readOneTest() throws Exception {

        RequestBuilder request = get(URI + "/read/1");
        ResultMatcher checkStatus = status().isOk();

        TaskDTO testSavedDTO = mapToDTO(new Tasks(1L, "Milk"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readAllTest() throws Exception {

        RequestBuilder request = get(URI + "/read");
        ResultMatcher checkStatus = status().isOk();

        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(mapToDTO(new Tasks(1L, "Milk")));
        tasks.add(mapToDTO(new Tasks(2L, "Eggs")));
        tasks.add(mapToDTO(new Tasks(3L, "Flour")));
        tasks.add(mapToDTO(new Tasks(4L, "Toast")));


        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(tasks);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);


    }

    @Test
    void updateTest() throws Exception {
        TaskDTO testDTO = mapToDTO(new Tasks("Panda"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = put(URI + "/update/1").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isAccepted();

        TaskDTO testSavedDTO = mapToDTO(new Tasks(1L, "Panda"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void deleteTest() throws Exception {

        RequestBuilder request = delete(URI + "/delete/1");
        ResultMatcher checkStatus = status().isNoContent();

        this.mvc.perform(request).andExpect(checkStatus);

    }

}
