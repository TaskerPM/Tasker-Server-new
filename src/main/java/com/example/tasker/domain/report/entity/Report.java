package com.example.tasker.domain.report.entity;

import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Report extends BaseTimeEntity {

    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @Enumerated(EnumType.STRING)  // 이름을 DB에 저장
    @Column(nullable = false)
    private ReportType reportType;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;   // 신고한 유저

    @JoinColumn(name = "task_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;   // 신고한 테스크

    @Builder
    public Report(ReportType reportType, User user, Task task) {
        this.reportType = reportType;
        this.user = user;
        this.task = task;
    }

    public static Report of(Task task, User user, ReportType reportType){
        return Report.builder()
                .task(task)
                .user(user)
                .reportType(reportType)
                .build();
    }

}
