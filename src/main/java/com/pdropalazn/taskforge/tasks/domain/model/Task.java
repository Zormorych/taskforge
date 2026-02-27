package com.pdropalazn.taskforge.tasks.domain.model;

import com.pdropalazn.taskforge.identity.domain.model.vo.UserId;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskDescription;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskTitle;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {

    private final UUID id;
    private final UUID projectId;
    private TaskTitle title;
    private TaskDescription description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dateTime;
    private UserId userAssignedId;

    public Task(UUID id, UUID projectId, TaskTitle title,
                TaskDescription description, TaskPriority priority,
                LocalDateTime dateTime, UserId userAssignedId){

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

    public TaskTitle getTitle() {
        return title;
    }

    public TaskDescription getDescription() {
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

    public UserId getUserAssignedId() {
        return userAssignedId;
    }


    public void changeTitle(TaskTitle newTitle){
        this.title = newTitle;
    }

    public void changeDescription(TaskDescription newDescription){
        this.description = newDescription;
    }

    public void changeStatus(TaskStatus newStatus){
        this.status = newStatus;
    }

    public void changePriority(TaskPriority newPriority){
        this.priority = newPriority;
    }

    public void assignToUser(UserId userAssignedId){
        this.userAssignedId = userAssignedId;
    }


}


