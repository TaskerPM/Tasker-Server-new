package com.example.tasker.domain.category;

import com.example.tasker.domain.category.dto.GatheringRes;
import com.example.tasker.domain.category.dto.PostCategoryReq;
import com.example.tasker.domain.category.dto.ReadCategoryRes;
import com.example.tasker.domain.category.dto.UpdateCategoryReq;
import com.example.tasker.domain.category.exception.NotFoundCategoryException;
import com.example.tasker.domain.category.service.CategoryService;
import com.example.tasker.domain.task.exception.NotFoundTaskException;
import com.example.tasker.domain.user.exception.NotFoundUserException;
import com.example.tasker.global.dto.ApplicationResponse;
import com.example.tasker.global.dto.ErrorResponse;
import com.example.tasker.global.jwt.service.JwtService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Operation(summary = "(Post) 카테고리 생성", description = "카테고리 생성 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 유저를 찾을 수 없습니다.(U0004)"),
            @ApiResponse(responseCode = "401", description = "존재하지 않는 색깔입니다.(C0004)")
    })
    public ApplicationResponse<String> createCategory(@RequestBody PostCategoryReq postCategoryReq){
        Long userId = jwtService.getUserId();
        return ApplicationResponse.create(categoryService.createCategory(userId, postCategoryReq));
    }

    @GetMapping
    @Operation(summary = "(Get) 카테고리 조회", description = "카테고리 조회 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 유저를 찾을 수 없습니다.(U0004)")
    })
    public ApplicationResponse<List<ReadCategoryRes>> readCategory(){
        Long userId = jwtService.getUserId();
        return ApplicationResponse.ok(categoryService.readCategory(userId));
    }

    // 수정 필요
    @PutMapping("/{category_id}")
    @Operation(summary = "(Put) 카테고리 수정", description = "카테고리 수정 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 유저를 찾을 수 없습니다.(U0004)"),
            @ApiResponse(responseCode = "401", description = "해당 카테고리를 찾을 수 없습니다.(C0001)")
    })
    public ApplicationResponse<String> updateCategory(@PathVariable Long category_id,
                                                      @RequestBody UpdateCategoryReq updateCategoryReq){
        Long userId = jwtService.getUserId();
        return ApplicationResponse.ok(categoryService.updateCategory(userId, category_id, updateCategoryReq));
    }

    @DeleteMapping("/{category_id}")
    @Operation(summary = "(Delete) 카테고리 삭제", description = "카테고리 삭제 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 유저를 찾을 수 없습니다.(U0004)"),
            @ApiResponse(responseCode = "401", description = "해당 카테고리를 찾을 수 없습니다.(C0001)")
    })
    public ApplicationResponse<String> deleteCategory(@PathVariable Long category_id){
        Long userId = jwtService.getUserId();
        return ApplicationResponse.ok(categoryService.deleteCategory(userId, category_id));
    }

    @GetMapping("/gathering/{category_id}")
    @Operation(summary = "모아보기", description = "모아보기 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 유저를 찾을 수 없습니다.(U0004)"),
            @ApiResponse(responseCode = "401", description = "해당 카테고리를 찾을 수 없습니다.(C0001)")
    })
    public ApplicationResponse<HashMap<String,List<GatheringRes>>> gathering(@PathVariable Long category_id){
        Long userId = jwtService.getUserId();
        return ApplicationResponse.ok(categoryService.gathering(userId, category_id));
    }

}
