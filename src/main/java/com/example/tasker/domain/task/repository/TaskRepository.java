package com.example.tasker.domain.task.repository;

import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserUserIdAndDate(long userId, String date);

    void deleteByUserUserIdAndTaskId(long userId, long taskId);

    List<Task> findByCategory(Category category);

    Task findByDateAndTimeStart(String date, String timeStart);

    Task findByDateAndTimeEnd(String date, String timeStart);

    Optional<Task> findByTaskIdAndUser(Long taskId, User user);
}
