package com.example.demo.service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.persistence.domain.Tasks;
import com.example.demo.persistence.repo.TaskRepo;
import com.example.demo.util.SpringBeanUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepo repo;

    private final ModelMapper mapper;

    @Autowired
    public TaskService(TaskRepo repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private TaskDTO mapToDTO(Tasks tasks) {
        return this.mapper.map(tasks, TaskDTO.class);
    }

    public TaskDTO create(Tasks tasks) {
        return this.mapToDTO(this.repo.save(tasks));
    }


    public List<TaskDTO> readAll() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TaskDTO readOne(Long id) {
        return this.mapToDTO(this.repo.findById(id).orElseThrow());
    }


    public TaskDTO update(TaskDTO taskDTO, Long id) {
        Tasks toUpdate = this.repo.findById(id).orElseThrow();
        toUpdate.setName(taskDTO.getName());
        SpringBeanUtil.mergeNotNull(taskDTO, toUpdate);
        return this.mapToDTO(this.repo.save(toUpdate));
    }

    public boolean delete(Long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}
