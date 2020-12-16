package com.example.demo.rest;

import com.example.demo.dto.TaskDTO;
import com.example.demo.persistence.domain.Tasks;
import com.example.demo.service.TaskService;
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
public class TasksControllerUnitTest {

    private final Tasks TTASK1 = new Tasks(1L, "Milk");
    private final Tasks TTASK2 = new Tasks(2L, "Eggs");
    private final Tasks TTASK3 = new Tasks(3L, "Flour");
    private final Tasks TTASK4 = new Tasks(4L, "Toast");
    private final List<Tasks> TASKS = List.of(TTASK1, TTASK2, TTASK3, TTASK4);
    @Autowired
    private TaskController controller;
    @MockBean
    private TaskService service;
    @Autowired
    private ModelMapper mapper;

    private TaskDTO maptoDto(Tasks tasks) {
        return this.mapper.map(tasks, TaskDTO.class);
    }

    @Test
    void createTest() throws Exception {
        when(this.service.create(TTASK1)).thenReturn(this.maptoDto(TTASK1));
        assertThat(new ResponseEntity<TaskDTO>(this.maptoDto(TTASK1), HttpStatus.CREATED))
                .isEqualTo(this.controller.create(TTASK1));
        verify(this.service, atLeastOnce()).create(TTASK1);

    }

    @Test
    void readOneTest() throws Exception {
        when(this.service.readOne(TTASK1.getId())).thenReturn(this.maptoDto(TTASK1));
        assertThat(new ResponseEntity<TaskDTO>(this.maptoDto(TTASK1), HttpStatus.OK))
                .isEqualTo(this.controller.readOne(TTASK1.getId()));
        verify(this.service, atLeastOnce()).readOne(TTASK1.getId());
    }

    @Test
    void readAllTest() throws Exception {
        List<TaskDTO> dtos = TASKS.stream().map(this::maptoDto).collect(Collectors.toList());
        when(this.service.readAll()).thenReturn(dtos);
        assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos, HttpStatus.OK));
        verify(this.service, atLeastOnce()).readAll();

    }

    @Test
    void updateTest() throws Exception {
        when(this.service.update(this.maptoDto(TTASK1), TTASK1.getId())).thenReturn(this.maptoDto(TTASK1));
        assertThat(new ResponseEntity<TaskDTO>(this.maptoDto(TTASK1), HttpStatus.ACCEPTED))
                .isEqualTo(this.controller.update(TTASK1.getId(), this.maptoDto(TTASK1)));
        verify(this.service, atLeastOnce()).update(this.maptoDto(TTASK1), TTASK1.getId());
    }

    @Test
    void deleteTest() throws Exception {
        when(this.service.delete(TTASK1.getId())).thenReturn(false);
        assertThat(this.controller.delete(TTASK1.getId()))
                .isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        verify(this.service, atLeastOnce()).delete(TTASK1.getId());

    }

    @Test
    void deleteTest2() throws Exception {
        when(this.service.delete(TTASK1.getId())).thenReturn(true);
        assertThat(this.controller.delete(TTASK1.getId())).isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        verify(this.service, atLeastOnce()).delete(TTASK1.getId());
    }

}
