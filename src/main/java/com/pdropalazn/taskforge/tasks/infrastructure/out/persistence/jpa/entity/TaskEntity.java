package com.pdropalazn.taskforge.tasks.infrastructure.out.persistence.jpa.entity;

import com.pdropalazn.taskforge.tasks.domain.model.TaskPriority;
import com.pdropalazn.taskforge.tasks.domain.model.TaskStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    private UUID id;

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;


    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "assignee_id")
    private UUID assigneeId;

    protected TaskEntity() {
        //JPA necesit constructor vacio
    }

    public TaskEntity(UUID id, UUID projectId, String title, String description,
                      TaskStatus status, TaskPriority priority,
                      LocalDateTime dueDate, UUID assigneeId) {
        this.id = id;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.assigneeId = assigneeId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public UUID getAssigneeId() {
        return assigneeId;
    }

    // setters
}
