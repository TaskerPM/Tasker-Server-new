package com.example.tasker.domain.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class GetNoteRes {
    private Long noteId;
    private String notesContent;
}
