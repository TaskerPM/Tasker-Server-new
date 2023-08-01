package com.example.tasker.domain.category;

import com.example.tasker.domain.category.dto.GatheringRes;
import com.example.tasker.domain.category.dto.PostCategoryReq;
import com.example.tasker.domain.category.dto.GetCategoryRes;
import com.example.tasker.domain.category.dto.UpdateCategoryReq;
import com.example.tasker.domain.category.service.CategoryService;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.global.dto.ApplicationResponse;
import com.example.tasker.global.jwt.service.JwtService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("v1/category")
@RequiredArgsConstructor
@Api(tags = "Category API", value = "카테고리 관련 API")
public class CategoryController {

    private final CategoryService categoryService;
    private final JwtService jwtService;

    @PostMapping("")
    @Operation(summary = "카테고리 생성", description = "카테고리 생성 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 유저를 찾을 수 없습니다.(U0004)"),
            @ApiResponse(responseCode = "401", description = "존재하지 않는 색깔입니다.(C0004)")
    })
    public ApplicationResponse<GetCategoryRes> createCategory(@RequestBody PostCategoryReq postCategoryReq){
        User user = jwtService.getUser();
        return ApplicationResponse.create(categoryService.createCategory(user, postCategoryReq));
    }

    @GetMapping
    @Operation(summary = "카테고리 조회", description = "카테고리 조회 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 유저를 찾을 수 없습니다.(U0004)")
    })
    public ApplicationResponse<List<GetCategoryRes>> readCategory(){
        User user = jwtService.getUser();
        return ApplicationResponse.ok(categoryService.readCategory(user));
    }

    @PutMapping("/{category_id}")
    @Operation(summary = "카테고리 수정", description = "카테고리 수정 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 유저를 찾을 수 없습니다.(U0004)"),
            @ApiResponse(responseCode = "401", description = "해당 카테고리를 찾을 수 없습니다.(C0001)")
    })
    public ApplicationResponse<GetCategoryRes> updateCategory(@PathVariable Long category_id,
                                                      @RequestBody UpdateCategoryReq updateCategoryReq){
        User user = jwtService.getUser();
        return ApplicationResponse.ok(categoryService.updateCategory(user, category_id, updateCategoryReq));
    }

    @DeleteMapping("/{category_id}")
    @Operation(summary = "카테고리 삭제", description = "카테고리 삭제 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 유저를 찾을 수 없습니다.(U0004)"),
            @ApiResponse(responseCode = "401", description = "해당 카테고리를 찾을 수 없습니다.(C0001)")
    })
    public ApplicationResponse<?> deleteCategory(@PathVariable Long category_id){
        User user = jwtService.getUser();
        categoryService.deleteCategory(user, category_id);
        return ApplicationResponse.ok();
    }

    @GetMapping("/gathering/{category_id}")
    @Operation(summary = "카테고리 별 모아보기", description = "모아보기 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 유저를 찾을 수 없습니다.(U0004)"),
            @ApiResponse(responseCode = "401", description = "해당 카테고리를 찾을 수 없습니다.(C0001)")
    })
    public ApplicationResponse<HashMap<String,List<GatheringRes>>> gathering(@PathVariable Long category_id){
        User user = jwtService.getUser();
        return ApplicationResponse.ok(categoryService.gathering(user, category_id));
    }

}
