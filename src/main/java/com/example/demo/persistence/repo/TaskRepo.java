package com.example.demo.persistence.repo;

import com.example.demo.persistence.domain.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Tasks, Long> {

}
