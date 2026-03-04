package com.pdropalazn.taskforge.tasks.infrastructure.in.web.mapper;

import com.pdropalazn.taskforge.identity.domain.model.vo.UserId;
import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskDescription;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskTitle;
import com.pdropalazn.taskforge.tasks.domain.port.in.dto.CreateTaskCommand;
import com.pdropalazn.taskforge.tasks.infrastructure.in.web.dto.CreateTaskRequest;
import com.pdropalazn.taskforge.tasks.infrastructure.in.web.dto.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskWebMapper {

    public CreateTaskCommand toCommand(CreateTaskRequest request) {
        return new CreateTaskCommand(
                request.projectId(),
                new TaskTitle(request.title()),
                request.description() == null ? null : new TaskDescription(request.description()),
                request.priority(),
                request.dueDate(),
                request.assigneeId() == null ? null : new UserId(request.assigneeId())
        );
    }

    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getTaskId().value(),
                task.getProjectId(),
                task.getTitle().title(),
                task.getDescription() == null ? null : task.getDescription().description(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getUserAssignedId() == null ? null : task.getUserAssignedId().value()
        );
    }
}
