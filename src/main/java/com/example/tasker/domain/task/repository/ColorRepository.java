package com.example.tasker.domain.task.repository;

import com.example.tasker.domain.task.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

}
