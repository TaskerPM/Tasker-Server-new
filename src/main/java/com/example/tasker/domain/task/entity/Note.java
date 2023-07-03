package com.example.tasker.domain.task.entity;

import com.example.tasker.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note extends BaseTimeEntity {

    @Id
    @Column(name = "note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;

    private String content;

    @JoinColumn(name = "task_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

}
