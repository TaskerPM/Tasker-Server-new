package com.example.tasker.domain.category.service;

import com.example.tasker.domain.category.dto.PostCategoryReq;
import com.example.tasker.domain.category.dto.ReadCategoryRes;
import com.example.tasker.domain.category.dto.UpdateCategoryReq;
import com.example.tasker.domain.category.entity.Category;
import com.example.tasker.domain.category.entity.Color;
import com.example.tasker.domain.category.exception.DuplicateCategoryException;
import com.example.tasker.domain.category.exception.NoModifiedCategoryException;
import com.example.tasker.domain.category.exception.NonExistentColorException;
import com.example.tasker.domain.category.exception.NotFoundCategoryException;
import com.example.tasker.domain.category.repository.CategoryRepository;
import com.example.tasker.domain.category.repository.ColorRepository;
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
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public String createCategory(Long userId, PostCategoryReq postCategoryReq) {
        // 유저 찾기
        User findUser = userRepository.findByUserId(userId).orElseThrow(NotFoundUserException::new);

        // 이미 존재하는 이름의 카테고리인지 확인 - 에러 추가하기
        Category findCategory = categoryRepository.findByUserAndName(findUser, postCategoryReq.getName());
//        if(findCategory != null){
//            throw new DuplicateCategoryException();
//        }

        // color 찾기
        Color getColor = colorRepository.findByColorBackAndColorText(postCategoryReq.getColorBack(), postCategoryReq.getColorText())
                .orElseThrow(NonExistentColorException::new);

        categoryRepository.save(Category.of(findUser, postCategoryReq.getName(), getColor));
        return "생성되었습니다.";
    }

    @Override
    public List<ReadCategoryRes> readCategory(Long userId) {
        // 유저 찾기
        User user = userRepository.findByUserId(userId).orElseThrow(NotFoundUserException::new);
        // 카테고리 리스트 찾기
        List<Category> categoryList = categoryRepository.findByUser(user);

        List<ReadCategoryRes> readCategoryRes = new ArrayList<>();
        categoryList.forEach(category -> {
            readCategoryRes.add(ReadCategoryRes.of(category));
        });
        return readCategoryRes;
    }

    @Override
    @Transactional
    public String updateCategory(Long userId, Long categoryId, UpdateCategoryReq updateCategoryReq) {
        // 유저 찾기
        User user = userRepository.findByUserId(userId).orElseThrow(NotFoundUserException::new);
        // 카테고리 찾기
        Category category = categoryRepository.findById(categoryId).orElseThrow(NotFoundCategoryException::new);
        // 색깔 찾기

        boolean check = false;
        if(updateCategoryReq.getName() != null && !updateCategoryReq.getName().equals(category.getName())){
            category.updateName(updateCategoryReq.getName());
            check = true;
        }

        // 색깔 수정

        if(check){
            categoryRepository.save(category);
            return "수정되었습니다.";
        }else{
            throw new NoModifiedCategoryException();
        }
    }

    @Override
    @Transactional
    public String deleteCategory(Long userId, Long categoryId) {
        // 유저 찾기
        User user = userRepository.findByUserId(userId).orElseThrow(NotFoundUserException::new);
        // 카테고리 찾기
        Category category = categoryRepository.findById(categoryId).orElseThrow(NotFoundCategoryException::new);

        categoryRepository.delete(category);
        return "삭제되었습니다.";
    }

    @Override
    public Object gathering(Long userId) {
        return null;
    }
}
