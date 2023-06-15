package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.task.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class GetTasksRes {

    private List<Task> tasks;

}
