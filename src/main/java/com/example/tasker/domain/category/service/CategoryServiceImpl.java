package com.example.tasker.domain.category.service;

import com.example.tasker.domain.category.dto.PostCategoryReq;
import com.example.tasker.domain.category.dto.ReadCategoryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    @Override
    public String createCategory(Long userId, PostCategoryReq postCategoryReq) {
        return "생성되었습니다.";
    }

    @Override
    public List<ReadCategoryRes> readCategory(Long userId) {
        return null;
    }

    @Override
    public String updateCategory(Long userId) {
        return "수정되었습니다.";
    }

    @Override
    public String deleteCategory(Long category_id) {
        return "삭제되었습니다.";
    }
}
