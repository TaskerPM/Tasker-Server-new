package com.example.tasker.domain.task.repository;

import com.example.tasker.domain.task.entity.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {

    TaskCategory findByTaskTaskId(long taskId);

}
