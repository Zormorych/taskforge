package com.pdropalazn.taskforge.tasks.infrastructure.in.web.dto;

import com.pdropalazn.taskforge.tasks.domain.model.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

//DTO  PUT /api/tasks/{id}.
public record UpdateTaskRequest(
        @NotBlank @Size(min = 3, max = 200) String title,
        @Size(max = 1000) String description,
        @NotNull TaskPriority priority,
        LocalDateTime dueDate,
        UUID assigneeId
) {
}
