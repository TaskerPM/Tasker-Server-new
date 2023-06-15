package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostTaskReq {

    private String title;

    public Task toEntity(User user, String date) {
        return Task.builder()
                .user(user)
                .title(title)
                .date(date)
                .build();
    }
}
