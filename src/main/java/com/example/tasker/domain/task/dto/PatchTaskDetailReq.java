package com.example.tasker.domain.task.dto;

import com.example.tasker.domain.task.entity.Note;
import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatchTaskDetailReq {

    private String title;
    private Long categoryId;
    private String timeStart;
    private String timeEnd;
    private List<String> notesContent;

}
