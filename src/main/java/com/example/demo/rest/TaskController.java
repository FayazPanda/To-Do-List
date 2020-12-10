package com.example.demo.rest;

import com.example.demo.dto.TaskDTO;
import com.example.demo.persistence.domain.Tasks;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDTO> create(@RequestBody Tasks tasks) {
        TaskDTO created = this.service.create(tasks);
        return new ResponseEntity<>(created, HttpStatus.CREATED);

    }


    @GetMapping("/read")
    public ResponseEntity<List<TaskDTO>> read() {
        return ResponseEntity.ok(this.service.readAll());

    }


    @GetMapping("/read/{id}")
    public ResponseEntity<TaskDTO> readOne(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.readOne(id));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return new ResponseEntity<>(this.service.update(taskDTO, id), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TaskDTO> delete(@PathVariable Long id) {
        return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
