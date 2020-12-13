package com.example.demo.persistence.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    public String name;

    @OneToMany(mappedBy = "tasks", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Tasks> tasks =  new ArrayList<>();

    public TaskList(String name) {
        super();
        this.name = name;
    }

    public TaskList(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

}
