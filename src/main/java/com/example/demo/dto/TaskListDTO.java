package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TaskListDTO {

    public Long id;
    public String name;

    private List<TaskDTO> cars = new ArrayList<>();
}
