package com.pdropalazn.taskforge.tasks.application.usecase.port.dto;

import com.pdropalazn.taskforge.identity.domain.model.vo.UserId;
import com.pdropalazn.taskforge.tasks.domain.model.TaskPriority;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskDescription;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskTitle;

import java.time.LocalDateTime;

// Command para update task use case
public record UpdateTaskCommand(
        TaskId taskId,
        TaskTitle title,
        TaskDescription description,
        TaskPriority priority,
        LocalDateTime dueDate,
        UserId assigneeId
) {
}
