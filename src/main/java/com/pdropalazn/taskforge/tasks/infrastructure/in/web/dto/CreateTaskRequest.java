package com.pdropalazn.taskforge.tasks.infrastructure.in.web.dto;

import com.pdropalazn.taskforge.tasks.domain.model.TaskPriority;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTaskRequest(
        UUID projectId,
        String title,
        String description,
        TaskPriority priority,
        LocalDateTime dateTime,
        UUID assigneeId
) {
}
