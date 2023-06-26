package com.example.tasker.domain.category.entity;

import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCategory extends BaseTimeEntity {

    @Id
    @Column(name = "task_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TaskCategoryId;

    @JoinColumn(name = "task_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

}
