package com.example.tasker.domain.task.entity;

import com.example.tasker.global.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category  extends BaseTimeEntity {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @ColumnDefault("선택 안 함")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<TaskCategory> taskCategories = new ArrayList<>();

}
