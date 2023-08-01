package com.example.tasker.domain.category.service;

import com.example.tasker.domain.category.dto.*;
import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.domain.category.entity.Color;
import com.example.tasker.domain.category.exception.DuplicateCategoryException;
import com.example.tasker.domain.category.exception.NoModifiedCategoryException;
import com.example.tasker.domain.category.exception.NotFoundCategoryException;
import com.example.tasker.domain.category.repository.CategoryRepository;
import com.example.tasker.domain.category.repository.ColorRepository;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.task.repository.TaskRepository;
import com.example.tasker.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;

    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public GetCategoryRes createCategory(User user, PostCategoryReq postCategoryReq) {

        Color color = colorRepository.findByColorBackAndColorText(postCategoryReq.getColorBack(), postCategoryReq.getColorText())
                .orElseGet(() -> colorRepository.save(Color.builder()
                        .colorBack(postCategoryReq.getColorBack())
                        .colorText(postCategoryReq.getColorText())
                        .build()));

        // 이미 존재하는 이름 + 색깔 조합의 카테고리인지 확인
        Optional<Category> findCategory = categoryRepository.findByUserAndNameAndColor(user, postCategoryReq.getName(), color);
        if(findCategory.isPresent()){
            throw new DuplicateCategoryException();
        }

        Category category = categoryRepository.save(Category.of(user, postCategoryReq.getName(), color));
        return GetCategoryRes.of(category);
    }

    @Override
    public List<GetCategoryRes> readCategory(User user) {
        // 카테고리 리스트 찾기
        List<Category> categoryList = categoryRepository.findByUser(user);

        List<GetCategoryRes> getCategoryRes = new ArrayList<>();
        categoryList.forEach(category -> {
            getCategoryRes.add(GetCategoryRes.of(category));
        });
        return getCategoryRes;
    }

    @Override
    @Transactional
    public GetCategoryRes updateCategory(User user, Long categoryId, UpdateCategoryReq updateCategoryReq) {
        // 카테고리 찾기
        Category category = categoryRepository.findById(categoryId).orElseThrow(NotFoundCategoryException::new);

        boolean check = false;

        if(updateCategoryReq.getName() != null && !updateCategoryReq.getName().equals(category.getName())){
            category.updateName(updateCategoryReq.getName());
            check = true;
        }

        // 색깔 수정
        if(updateCategoryReq.getColorBack() != null && !updateCategoryReq.getColorBack().equals(category.getColor().getColorBack())
                && updateCategoryReq.getColorText() != null && !updateCategoryReq.getColorText().equals(category.getColor().getColorText())){
            Color color = colorRepository.findByColorBackAndColorText(updateCategoryReq.getColorBack(), updateCategoryReq.getColorText())
                    .orElseGet(() -> colorRepository.save(Color.builder()
                            .colorBack(updateCategoryReq.getColorBack())
                            .colorText(updateCategoryReq.getColorText())
                            .build()));
            category.updateColor(color);
            check = true;
        }

        if(check){
            Category saveCategory = categoryRepository.save(category);
            return GetCategoryRes.of(saveCategory);
        }else{
            throw new NoModifiedCategoryException();
        }
    }

    @Override
    @Transactional
    public void deleteCategory(User user, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(NotFoundCategoryException::new);
        categoryRepository.delete(category);
    }

    /**
     * 모아 보기
     * */
    @Override
    public HashMap<String,List<GatheringRes>> gathering(User user, Long categoryId) {
        // 카테고리 찾기
        Category category = categoryRepository.findById(categoryId).orElseThrow(NotFoundCategoryException::new);
        List<Task> tasks = taskRepository.findByCategory(category);

        HashMap<String,List<GatheringRes>> map = new HashMap<>();
        tasks.forEach(task->{
            String getDate = task.getDate();
            String date = getDate.substring(0,6);
            List<GatheringRes> list;
            if(map.get(date) == null) list = new ArrayList<>();
            else list = map.get(date);
            list.add(GatheringRes.of(task));
        });

        return map;
    }
}
