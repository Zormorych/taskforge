package com.pdropalazn.taskforge.tasks.infrastructure.in.web;

import com.pdropalazn.taskforge.tasks.domain.model.TaskPriority;
import com.pdropalazn.taskforge.tasks.domain.model.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(
        String taskId,
        UUID projectId,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDateTime dateTime,
        UUID userAssignedId
) {
}
