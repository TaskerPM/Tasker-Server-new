package com.example.tasker.domain.category.entity;

import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseTimeEntity {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

//    @OneToMany(mappedBy = "category")
//    private List<TaskCategory> taskCategories = new ArrayList<>();

}
