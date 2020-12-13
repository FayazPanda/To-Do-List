package com.example.demo.rest;

import com.example.demo.dto.TaskListDTO;
import com.example.demo.persistence.domain.TaskList;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:todolist-schema.sql",
        "classpath:todolist-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TaskListControllerintergrationTest {

    private final String URI = "/tasklist";
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper jsonifier;
    @Autowired
    private ModelMapper mapper;

    private TaskListDTO mapToDTO(TaskList taskList) {
        return this.mapper.map(taskList, TaskListDTO.class);
    }

    @Test
    void createTest() throws Exception {
        TaskListDTO testDTO = mapToDTO(new TaskList("Pandamonium"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isCreated();

        TaskListDTO testSavedDTO = mapToDTO(new TaskList(3L, "Pandamonium"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readOneTest() throws Exception {

        RequestBuilder request = get(URI + "/read/1");
        ResultMatcher checkStatus = status().isOk();

        TaskList LTASK1 = new TaskList(1L, "Grocery");
        List<Tasks> TASKS1 = List.of(new Tasks(1L, "Milk"), new Tasks(2L, "Eggs"), new Tasks(3L, "Flour"));
        LTASK1.setTasks(TASKS1);

        TaskListDTO testSavedDTO = mapToDTO(LTASK1);
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readAllTest() throws Exception {

        RequestBuilder request = get(URI + "/read");
        ResultMatcher checkStatus = status().isOk();

        TaskList LTASK1 = new TaskList(1L, "Grocery");
        List<Tasks> TASKS1 = List.of(new Tasks(1L, "Milk"), new Tasks(2L, "Eggs"), new Tasks(3L, "Flour"));
        LTASK1.setTasks(TASKS1);
        TaskListDTO TASK1DTO = mapToDTO(LTASK1);
        TaskList LTASK2 = new TaskList(2L, "Stationary");
        List<Tasks> TASKS2 = List.of(new Tasks(4L, "Toast"));
        LTASK2.setTasks(TASKS2);
        TaskListDTO TASK2DTO = mapToDTO(LTASK2);
        List<TaskListDTO> taskList = List.of(TASK1DTO, TASK2DTO);


        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(taskList);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);


    }

    @Test
    void updateTest() throws Exception {
        TaskListDTO testDTO = mapToDTO(new TaskList("Pandamonium"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = put(URI + "/update/1").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isAccepted();

        TaskListDTO testSavedDTO = mapToDTO(new TaskList(1L, "Pandamonium"));
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
