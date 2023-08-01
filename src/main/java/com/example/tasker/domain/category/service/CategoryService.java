package com.example.tasker.domain.category.service;

import com.example.tasker.domain.category.dto.GatheringRes;
import com.example.tasker.domain.category.dto.PostCategoryReq;
import com.example.tasker.domain.category.dto.GetCategoryRes;
import com.example.tasker.domain.category.dto.UpdateCategoryReq;
import com.example.tasker.domain.user.entity.User;

import java.util.HashMap;
import java.util.List;

public interface CategoryService {
    GetCategoryRes createCategory(User user, PostCategoryReq postCategoryReq);

    List<GetCategoryRes> readCategory(User user);

    GetCategoryRes updateCategory(User user, Long categoryId, UpdateCategoryReq updateCategoryReq);

    void deleteCategory(User user, Long categoryId);

    HashMap<String,List<GatheringRes>> gathering(User user, Long categoryId);
}
