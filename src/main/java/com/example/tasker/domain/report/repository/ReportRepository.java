package com.example.tasker.domain.report.repository;

import com.example.tasker.domain.report.entity.Report;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findByUserAndTask(User user, Task task);
}
