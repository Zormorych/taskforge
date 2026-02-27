package com.pdropalazn.taskforge.tasks.application.command;

import com.pdropalazn.taskforge.tasks.domain.model.TaskPriority;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTaskCommand() {

    UUID projectId,
    String title,
    String description,
    TaskPriority priority,
    LocalDateTime dateTime,
    UUID userId

}
