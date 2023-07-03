package com.example.tasker.domain.task.repository;

import com.example.tasker.domain.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    //테스크 보기
    //  param : userId, 날짜,
    //    List<Task>
    List<Task> findByUserUserIdAndDate(long userId, String date);

    void deleteByUserUserIdAndTaskId(long userId, long taskId);
//
//    Task findByUserIdAndTaskId(Long userId, Long taskId);
}
