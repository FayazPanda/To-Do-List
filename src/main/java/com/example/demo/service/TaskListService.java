package com.example.demo.service;

import com.example.demo.dto.TaskListDTO;
import com.example.demo.persistence.domain.TaskList;
import com.example.demo.persistence.repo.TaskListRepo;
import com.example.demo.util.SpringBeanUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskListService {

    private final TaskListRepo repo;

    private final ModelMapper mapper;

    @Autowired
    public TaskListService(TaskListRepo repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private TaskListDTO mapToDTO(TaskList taskList) {
        return this.mapper.map(taskList, TaskListDTO.class);
    }

    public TaskListDTO create(TaskList taskList) {
        return this.mapToDTO(this.repo.save(taskList));
    }

    public List<TaskListDTO> readAll() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TaskListDTO readOne(Long id) {
        return this.mapToDTO(this.repo.findById(id).orElseThrow());
    }

    public TaskListDTO update(TaskListDTO taskListDTO, Long id) {
        TaskList toUpdate = this.repo.findById(id).orElseThrow();
        toUpdate.setName(taskListDTO.getName());
        SpringBeanUtil.mergeNotNull(taskListDTO, toUpdate);
        return this.mapToDTO(this.repo.save(toUpdate));

    }

    public boolean delete(Long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}
