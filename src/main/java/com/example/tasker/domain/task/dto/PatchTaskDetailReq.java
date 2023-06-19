package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.task.entity.TaskCategory;

import java.util.List;

public class PatchTaskDetailReq {

    private String title;
    private List<TaskCategory> taskCategories;
    private String time_start;
    private String time_end;
    private Integer status;

}
