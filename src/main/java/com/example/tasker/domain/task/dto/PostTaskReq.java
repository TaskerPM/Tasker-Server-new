package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostTaskReq {

    @Schema(description = "테스커 제목", defaultValue = "IA구조도 그리기")
    @NotNull @NotBlank
    private String title;

    public Task toEntity(User user, String date) {
        return Task.builder()
                .user(user)
                .title(title)
                .date(date)
                .build();
    }

}
