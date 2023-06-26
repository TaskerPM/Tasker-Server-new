package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.task.entity.Category;
import com.example.tasker.domain.task.entity.Note;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.task.entity.TaskCategory;
import com.example.tasker.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatchTaskDetailReq {

    private String title;
    private String categoryName;
    private String categoryColorBack;
    private String categoryColorText;
    private String time_start;
    private String time_end;
    private List<String> notesContent;


    public Task toEntityTask(User user, String date) {
        return Task.builder()
                .user(user)
                .title(title)
                .date(date)
                .time_start(time_start)
                .time_end(time_end)
                .build();
    }

}
