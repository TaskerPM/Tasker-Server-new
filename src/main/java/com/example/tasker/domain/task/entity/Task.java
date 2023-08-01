package com.example.tasker.domain.task.entity;

import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseTimeEntity {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String title;

    private String date;

    @Column(name = "time_start")
    private String timeStart;

    @Column(name = "time_end")
    private String timeEnd;

    @Column(name = "status", columnDefinition = "INT DEFAULT 0")
    private Integer status;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "category_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "task")
    private List<Note> noteList = new ArrayList<>();

    @Builder
    public Task(String title, String date, User user) {
        this.title = title;
        this.date = date;
        this.user = user;
        this.status = 0;
    }

    public void updateCategory(Category category){
        this.category = category;
    }

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateTime(String timeStart, String timeEnd){
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public void updateStatus(Integer status){
        this.status = status;
    }

}
