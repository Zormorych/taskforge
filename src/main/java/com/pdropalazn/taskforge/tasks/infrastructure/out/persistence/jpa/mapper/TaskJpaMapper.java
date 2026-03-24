package com.pdropalazn.taskforge.tasks.infrastructure.out.persistence.jpa.mapper;

import com.pdropalazn.taskforge.identity.domain.model.vo.UserId;
import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskDescription;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskTitle;
import com.pdropalazn.taskforge.tasks.infrastructure.out.persistence.jpa.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskJpaMapper {
    // Este mapper se encargará de convertir entre la entidad JPA al modelo de dominio Task
    public TaskEntity toEntity(Task task) {
        return new TaskEntity(
                task.getTaskId().value(),
                task.getProjectId(),
                task.getTitle().title(),
                task.getDescription() != null ? task.getDescription().description() : null,
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getUserAssignedId() != null ? task.getUserAssignedId().value() : null
        );
    }

    // Este mapper se encargará de convertir de mdelo de dominio a la entidad JPA
    public Task toDomain(TaskEntity entity) {
        return Task.reconstructTask(
                new TaskId(entity.getId()),
                entity.getProjectId(),
                new TaskTitle(entity.getTitle()),
                new TaskDescription(entity.getDescription()),
                entity.getStatus(),
                entity.getPriority(),
                entity.getDueDate(),
                new UserId(entity.getAssigneeId())
        );
    }
}
