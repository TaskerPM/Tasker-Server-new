package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.GetTasksRes;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.task.entity.TaskCategory;
import com.example.tasker.domain.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskProviderImpl implements TaskProvider{

    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public List<GetTasksRes> getTasksByDate(Long userId, String date) {

        List<GetTasksRes> getTasksResList = new ArrayList<>();
        List<Task> tasks = taskRepository.findByUserUserIdAndDate(userId, date);

        for (Task t : tasks) {

            GetTasksRes getTasksRes = GetTasksRes.builder()
                    .taskId(t.getTaskId())
                    .title(t.getTitle())
                    .date(t.getDate())
                    .time_start(t.getTime_start())
                    .time_end(t.getTime_end())
                    .taskCategories(t.getTaskCategories())
                    .status(t.getStatus())
                    .build();

            getTasksResList.add(getTasksRes);
        }

        return getTasksResList;
    }
}
