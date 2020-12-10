package com.example.demo.persistence.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    private TaskList taskList;

    public Tasks(String name) {
        super();
        this.name = name;
    }

    public Tasks(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

}
