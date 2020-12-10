package com.example.demo.rest;

import com.example.demo.dto.TaskListDTO;
import com.example.demo.persistence.domain.TaskList;
import com.example.demo.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tasklist")
public class TaskListController {

    private final TaskListService service;

    @Autowired
    public TaskListController(TaskListService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<TaskListDTO> create(@RequestBody TaskList taskList) {
        TaskListDTO created = this.service.create(taskList);
        return new ResponseEntity<>(created, HttpStatus.CREATED);

    }

    @GetMapping("/read")
    public ResponseEntity<List<TaskListDTO>> read() {
        return ResponseEntity.ok(this.service.readAll());

    }


    @GetMapping("/read/{id}")
    public ResponseEntity<TaskListDTO> readOne(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.readOne(id));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<TaskListDTO> update(@PathVariable Long id, @RequestBody TaskListDTO taskListDTO) {
        return new ResponseEntity<>(this.service.update(taskListDTO, id), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TaskListDTO> delete(@PathVariable Long id) {
        return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
