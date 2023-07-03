package com.example.tasker.domain.category.service;

import com.example.tasker.domain.category.dto.PostCategoryReq;
import com.example.tasker.domain.category.dto.ReadCategoryRes;
import com.example.tasker.domain.category.dto.UpdateCategoryReq;

import java.util.List;

public interface CategoryService {
    String createCategory(Long userId, PostCategoryReq postCategoryReq);

    List<ReadCategoryRes> readCategory(Long userId);

    String updateCategory(Long userId, Long category_id, UpdateCategoryReq updateCategoryReq);

    String deleteCategory(Long userId, Long categoryId);

    Object gathering(Long userId);
}
