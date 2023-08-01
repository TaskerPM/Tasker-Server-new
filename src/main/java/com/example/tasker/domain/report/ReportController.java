package com.example.tasker.domain.report;

import com.example.tasker.domain.report.service.ReportService;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.global.dto.ApplicationResponse;
import com.example.tasker.global.jwt.service.JwtService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("v1/report")
@RequiredArgsConstructor
@Api(tags = "Report API", value = "신고 관련 API")
public class ReportController {

    private final ReportService reportService;
    private final JwtService jwtService;

    /**
     * @author 상민
     * */
    @Operation(summary = "신고", description = "신고하기")
    @PostMapping("/{taskId}")
    public ApplicationResponse<?> createReport(@PathVariable Long taskId,
                                               @RequestParam String report){
        User user = jwtService.getUser();
        return ApplicationResponse.create(reportService.createReport(user,taskId,report));
    }

}
