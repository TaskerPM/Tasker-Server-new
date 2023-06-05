package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.task.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetTasksRes {

    private List<Task> tasks;

}
