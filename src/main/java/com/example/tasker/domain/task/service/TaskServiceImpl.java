package com.example.tasker.domain.task.service;

import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.domain.category.exception.NotFoundCategoryException;
import com.example.tasker.domain.category.repository.CategoryRepository;
import com.example.tasker.domain.task.dto.GetTasksRes;
import com.example.tasker.domain.task.dto.PostTaskReq;
import com.example.tasker.domain.task.dto.PostTaskRes;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.task.exception.NotFoundTaskException;
import com.example.tasker.domain.task.repository.TaskRepository;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.domain.user.exception.NotFoundUserException;
import com.example.tasker.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public PostTaskRes createTask(Long userId, PostTaskReq postTaskReq, String date) {
        User user = userRepository.findByUserId(userId).orElseThrow(NotFoundUserException::new); // 유저 찾기
        Task save = taskRepository.save(postTaskReq.toEntity(user, date));
        return PostTaskRes.of(save);
    }

    /**
     * 카테고리 추가
     * */
    @Override
    @Transactional
    public String addCategory(Long userId, Long taskId, Long categoryId) {
        // 유저 확인
        userRepository.findByUserId(userId).orElseThrow(NotFoundUserException::new);
        // task 찾기
        Task task = taskRepository.findById(taskId).orElseThrow(NotFoundTaskException::new);
        Category category = categoryRepository.findById(categoryId).orElseThrow(NotFoundCategoryException::new);

        task.updateCategory(category);
        taskRepository.save(task);
        return "카테고리가 추가되었습니다.";
    }

    @Override
    @Transactional
    public String deleteTask(Long userId, Long taskId) {
        // 유저 확인
        userRepository.findByUserId(userId).orElseThrow(NotFoundUserException::new);
        // task 찾기
        Task task = taskRepository.findById(taskId).orElseThrow(NotFoundTaskException::new);
        // task 삭제
        taskRepository.deleteByUserUserIdAndTaskId(userId, taskId);
        return "테스크가 삭제되었습니다.";
    }

    @Override
    public List<GetTasksRes> getTasksByDate(Long userId, String date) {
        // 유저 확인
        userRepository.findByUserId(userId).orElseThrow(NotFoundUserException::new);

        List<Task> tasks = taskRepository.findByUserUserIdAndDate(userId, date);
        List<GetTasksRes> getTasksResList = new ArrayList<>();
        tasks.forEach(task -> {
            getTasksResList.add(GetTasksRes.of(task));
        });
        return getTasksResList;
    }

}
