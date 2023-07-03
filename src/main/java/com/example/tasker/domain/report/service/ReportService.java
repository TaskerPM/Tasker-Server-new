package com.example.tasker.domain.report.service;

import com.example.tasker.domain.report.entity.ReportType;

public interface ReportService {
    String createReport(Long userId, Long taskId, String report);

    ReportType getReport(String report);
}
