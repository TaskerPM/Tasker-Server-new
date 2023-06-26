package com.example.tasker.domain.task.entity;

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
@Builder
public class Color extends BaseTimeEntity {

    @Id
    @Column(name = "color_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long colorId;

    private String colorBack;
    private String colorText;

    @OneToMany(mappedBy = "color", cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        this.categories.add(category);
        category.setColor(this);
    }

}
