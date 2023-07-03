package com.example.tasker.domain.category.service;

import com.example.tasker.domain.category.dto.GatheringRes;
import com.example.tasker.domain.category.dto.PostCategoryReq;
import com.example.tasker.domain.category.dto.ReadCategoryRes;
import com.example.tasker.domain.category.dto.UpdateCategoryReq;

import java.util.HashMap;
import java.util.List;

public interface CategoryService {
    String createCategory(Long userId, PostCategoryReq postCategoryReq);

    List<ReadCategoryRes> readCategory(Long userId);

    String updateCategory(Long userId, Long categoryId, UpdateCategoryReq updateCategoryReq);

    String deleteCategory(Long userId, Long categoryId);

    HashMap<String,List<GatheringRes>> gathering(Long userId, Long categoryId);
}
