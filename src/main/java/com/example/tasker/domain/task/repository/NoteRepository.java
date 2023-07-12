package com.example.tasker.domain.task.repository;

import com.example.tasker.domain.task.entity.Note;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findByContentAndTask(String content, Task task);
}
