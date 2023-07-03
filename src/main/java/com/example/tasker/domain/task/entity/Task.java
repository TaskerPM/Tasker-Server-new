package com.example.tasker.domain.task.entity;

import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.domain.category.entity.TaskCategory;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.global.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @ColumnDefault("0")
    private Integer status;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "category_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "task")
    private List<Note> notes = new ArrayList<>();

    @OneToMany(mappedBy = "task")
    private List<TaskCategory> taskCategories = new ArrayList<>();

    public void updateCategory(Category category){
        this.category = category;
    }
}
