package com.example.tasker.domain.report.service;

import com.example.tasker.domain.report.entity.Report;
import com.example.tasker.domain.report.entity.ReportType;
import com.example.tasker.domain.report.exception.DuplicateReportException;
import com.example.tasker.domain.report.exception.NotFoundReportException;
import com.example.tasker.domain.report.repository.ReportRepository;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.task.repository.TaskRepository;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.domain.user.exception.NotFoundUserException;
import com.example.tasker.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public String createReport(User user,Long taskId, String report) {
        // 테스크 찾기
        Task task = taskRepository.findById(taskId).orElseThrow();

        // 이미 신고한 테스크북이라면
        Report findReport = reportRepository.findByUserAndTask(user, task);
        if(findReport != null) throw new DuplicateReportException();

        // Report 찾기
        ReportType getReport = getReport(report);

        reportRepository.save(Report.of(task, user, getReport));
        return "신고 완료되었습니다.";
    }

    @Override
    public ReportType getReport(String report){
        ReportType getReport = null;
        for(ReportType reportType : ReportType.values()){
            if(reportType.getReport().equals(report)){
                getReport = reportType;
            }
        }
        if(getReport == null) throw new NotFoundReportException();
        return getReport;
    }

}
