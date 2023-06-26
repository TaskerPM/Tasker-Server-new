package com.example.tasker.domain.category;

import com.example.tasker.domain.category.dto.PostCategoryReq;
import com.example.tasker.domain.category.dto.ReadCategoryRes;
import com.example.tasker.domain.category.service.CategoryService;
import com.example.tasker.global.dto.ApplicationResponse;
import com.example.tasker.global.jwt.service.JwtService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/category")
@RequiredArgsConstructor
@Api(tags = "Category API", value = "카테고리 관련 API")
public class CategoryController {

    private final CategoryService categoryService;
    private final JwtService jwtService;

    @PostMapping
    @Operation(summary = "(Post) 카테고리 생성", description = "카테고리 생성 API 입니다.")
    public ApplicationResponse<?> createCategory(@RequestBody PostCategoryReq postCategoryReq){
        Long userId = jwtService.getUserId();
        return ApplicationResponse.create(categoryService.createCategory(userId, postCategoryReq));
    }

    @GetMapping
    @Operation(summary = "(Get) 카테고리 조회", description = "카테고리 조회 API 입니다.")
    public ApplicationResponse<List<ReadCategoryRes>> readCategory(){
        Long userId = jwtService.getUserId();
        return ApplicationResponse.ok(categoryService.readCategory(userId));
    }

    @PutMapping("/{category_id}")
    @Operation(summary = "(Put) 카테고리 수정", description = "카테고리 수정 API 입니다.")
    public ApplicationResponse<?> updateCategory(@PathVariable Long category_id){
        return ApplicationResponse.ok(categoryService.updateCategory(category_id));
    }

    @DeleteMapping("/{category_id}")
    @Operation(summary = "(Delete) 카테고리 삭제", description = "카테고리 삭제 API 입니다.")
    public ApplicationResponse<?> deleteCategory(@PathVariable Long category_id){
        return ApplicationResponse.ok(categoryService.deleteCategory(category_id));
    }
}
