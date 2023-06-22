package com.example.tasker.domain.report.service;

import com.example.tasker.TaskerApplication;
import com.example.tasker.domain.report.entity.Report;
import com.example.tasker.domain.report.entity.ReportType;
import com.example.tasker.domain.report.exception.NotFoundReportException;
import com.example.tasker.domain.report.repository.ReportRepository;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.task.repository.TaskRepository;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor
public class ReportServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportService reportService;

    @Test
    void 리포트_타입확인() {
        ReportType getReport = null;
        for(ReportType reportType : ReportType.values()){
            if(reportType.getReport().equals("불법 정보")){
                getReport = reportType;
            }
        }
        if(getReport == null){
            throw new NotFoundReportException();
        }
        assertEquals(ReportType.R1, getReport);
    }

    @Test
    void 신고하기(){
        //Given
        User user = userRepository.save(User.builder()
                .userId(1L)
                .phoneNumber("010-000-000")
                .build());

        Task task = taskRepository.save(Task.builder()
                .title("")
                .user(user)
                .date("2023-01-01")
                .status(1)
                .build());

        ReportType getReport = reportService.getReport("불법 정보");

        // when
        Report save = reportRepository.save(Report.of(task, user, getReport));

        //Then
        assertThat(save.getReportId()).isNotNull();
        assertEquals(user, save.getUser());
        assertEquals(task, save.getTask());
        assertEquals(ReportType.R1, save.getReportType());
    }

}