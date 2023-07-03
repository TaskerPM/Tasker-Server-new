package com.example.tasker.domain.category.entity;

import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

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

}
