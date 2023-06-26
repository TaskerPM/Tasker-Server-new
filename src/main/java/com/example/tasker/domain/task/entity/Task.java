package com.example.tasker.domain.task.entity;

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
@Setter
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
    private String time_start;
    private String time_end;

    @ColumnDefault("0")
    private Integer status;

    @OneToMany(mappedBy = "task")
    private List<Note> notes = new ArrayList<>();

    public void addNote(Note note) {
        this.notes.add(note);
        note.setTask(this);
    }

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "task")
    private List<TaskCategory> taskCategories = new ArrayList<>();

}
