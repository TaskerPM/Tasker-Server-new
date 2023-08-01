package com.example.tasker.domain.report.service;

import com.example.tasker.domain.report.entity.ReportType;
import com.example.tasker.domain.user.entity.User;

public interface ReportService {
    String createReport(User user, Long taskId, String report);

    ReportType getReport(String report);
}
