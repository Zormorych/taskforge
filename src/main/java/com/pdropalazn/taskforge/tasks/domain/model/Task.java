package com.pdropalazn.taskforge.tasks.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {

    private final UUID id;
    private final UUID projectId;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dateTime;
    private UUID userAssignedId;

    public Task(UUID id, UUID projectId, String title,
                String description,
                TaskStatus status, TaskPriority priority,
                LocalDateTime dateTime, UUID userAssignedId){

        this.id = id;
        this.projectId = projectId;
        this.title = title;
        this.description = description;

        this.status = TaskStatus.TO_DO;
        this.priority = priority;
        this.dateTime = dateTime;
        this.userAssignedId = userAssignedId;
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

    public TaskStatus getStatus() {
        return status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public UUID getUserAssignedId() {
        return userAssignedId;
    }


    public void changeTitle(String newTitle){
        this.title = newTitle;
    }

    public void changeDescription(String newDescription){
        this.description = newDescription;
    }

    public void changeStatus(TaskStatus newStatus){
        this.status = newStatus;
    }

    public void changePriority(TaskPriority newPriority){
        this.priority = newPriority;
    }

    public void assignToUser(UUID userAssignedId){
        this.userAssignedId = userAssignedId;
    }


}


