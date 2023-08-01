package com.example.tasker.domain.category.entity;

import com.example.tasker.domain.user.entity.User;
import com.example.tasker.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

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

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @Builder
    public Category(User user, String name, Color color) {
        this.user = user;
        this.name = name;
        this.color = color;
    }

    public static Category of(User user, String name, Color color){
        return Category.builder()
                .user(user)
                .name(name)
                .color(color)
                .build();
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updateColor(Color color){
        this.color = color;
    }

}
