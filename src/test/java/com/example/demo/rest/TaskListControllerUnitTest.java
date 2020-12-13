package com.example.demo.rest;

import com.example.demo.dto.TaskListDTO;
import com.example.demo.persistence.domain.TaskList;
import com.example.demo.service.TaskListService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("dev")
public class TaskListControllerUnitTest {

    private final TaskList TTASKLIST1 = new TaskList(1L, "Grocery");
    private final TaskList TTASKLIST2 = new TaskList(1L, "Stationary");
    private final List<TaskList> TASKSLISTS = List.of(TTASKLIST1, TTASKLIST2);
    @Autowired
    private TaskListController controller;
    @MockBean
    private TaskListService service;
    @Autowired
    private ModelMapper mapper;

    private TaskListDTO maptoDto(TaskList taskList) {
        return this.mapper.map(taskList, TaskListDTO.class);
    }

    @Test
    void createTest() throws Exception {
        when(this.service.create(TTASKLIST1)).thenReturn(this.maptoDto(TTASKLIST1));
        assertThat(new ResponseEntity<TaskListDTO>(this.maptoDto(TTASKLIST1), HttpStatus.CREATED))
                .isEqualTo(this.controller.create(TTASKLIST1));
        verify(this.service, atLeastOnce()).create(TTASKLIST1);

    }

    @Test
    void readOneTest() throws Exception {
        when(this.service.readOne(TTASKLIST1.getId())).thenReturn(this.maptoDto(TTASKLIST1));
        assertThat(new ResponseEntity<TaskListDTO>(this.maptoDto(TTASKLIST1), HttpStatus.OK))
                .isEqualTo(this.controller.readOne(TTASKLIST1.getId()));
        verify(this.service, atLeastOnce()).readOne(TTASKLIST1.getId());
    }

    @Test
    void readAllTest() throws Exception {
        List<TaskListDTO> dtos = TASKSLISTS.stream().map(this::maptoDto).collect(Collectors.toList());
        when(this.service.readAll()).thenReturn(dtos);
        assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos, HttpStatus.OK));
        verify(this.service, atLeastOnce()).readAll();

    }

    @Test
    void UpdateTest() throws Exception {
        when(this.service.update(this.maptoDto(TTASKLIST1), TTASKLIST1.getId())).thenReturn(this.maptoDto(TTASKLIST1));
        assertThat(new ResponseEntity<TaskListDTO>(this.maptoDto(TTASKLIST1), HttpStatus.ACCEPTED))
                .isEqualTo(this.controller.update(TTASKLIST1.getId(), this.maptoDto(TTASKLIST1)));
        verify(this.service, atLeastOnce()).update(this.maptoDto(TTASKLIST1), TTASKLIST1.getId());
    }

    @Test
    void deleteTest() throws Exception {
        when(this.service.delete(TTASKLIST1.getId())).thenReturn(false);
        assertThat(this.controller.delete(TTASKLIST1.getId()))
                .isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        verify(this.service, atLeastOnce()).delete(TTASKLIST1.getId());

    }

    @Test
    void deleteTest2() throws Exception {
        when(this.service.delete(TTASKLIST1.getId())).thenReturn(true);
        assertThat(this.controller.delete(TTASKLIST1.getId())).isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        verify(this.service, atLeastOnce()).delete(TTASKLIST1.getId());
    }

}
