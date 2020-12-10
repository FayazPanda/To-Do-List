package com.example.demo.persistence.repo;

import com.example.demo.persistence.domain.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepo extends JpaRepository<TaskList, Long> {

}
