package com.example.tasker.domain.task.service;

import com.example.tasker.domain.task.dto.GetTasksRes;
import com.example.tasker.domain.task.entity.Category;
import com.example.tasker.domain.task.entity.Color;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.task.entity.TaskCategory;
import com.example.tasker.domain.task.repository.CategoryRepository;
import com.example.tasker.domain.task.repository.TaskCategoryRepository;
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
    private final TaskCategoryRepository taskCategoryRepository;
    private final CategoryRepository categoryRepository;


    @Override
    @Transactional
    public List<GetTasksRes> getTasksByDate(Long userId, String date) {

        List<GetTasksRes> getTasksResList = new ArrayList<>();
        List<Task> tasks = taskRepository.findByUserUserIdAndDate(userId, date);

        for (Task t : tasks) {

            Category category = getCategoryByTaskId(t.getTaskId());
            Color color = getColorByCategoryId(category.getCategoryId());


            GetTasksRes getTasksRes = GetTasksRes.builder()
                    .taskId(t.getTaskId())
                    .title(t.getTitle())
                    .time_start(t.getTime_start())
                    .time_end(t.getTime_end())
                    .categoryName(category.getName())
                    .categoryColorBack(color.getColorBack())
                    .categoryColorText(color.getColorText())
                    .isCompeleted(t.getStatus())
                    .build();

            getTasksResList.add(getTasksRes);
        }

        return getTasksResList;
    }

    @Override
    @Transactional
    public Category getCategoryByTaskId(Long taskId) {
        return taskCategoryRepository.findByTaskTaskId(taskId).getCategory();
    }

    @Override
    @Transactional
    public Color getColorByCategoryId(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId).getColor();
    }


}
