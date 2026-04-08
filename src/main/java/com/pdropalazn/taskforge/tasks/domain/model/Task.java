package com.pdropalazn.taskforge.tasks.domain.model;

import com.pdropalazn.taskforge.identity.domain.model.vo.UserId;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskDescription;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskTitle;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {

    private final TaskId taskId;
    private final UUID projectId;
    private TaskTitle title;
    private TaskDescription description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dueDate;
    private UserId userAssignedId;


    private Task(TaskId taskId, UUID projectId, TaskTitle title,
                 TaskDescription description, TaskPriority priority,
                 LocalDateTime dueDate, UserId userAssignedId){

        this.taskId = taskId;
        this.projectId = projectId;
        this.title = title;
        this.description = description;

        this.status = TaskStatus.TO_DO; //las tareas empiezan de forma predeterminada en ese estado
        this.priority = priority;
        this.dueDate = dueDate;
        this.userAssignedId = userAssignedId;

    }

    //por ahora se aloja aqui la unica forma de crear una Tarea (y de generar un nuevo UUID con dicha tarea recien creada)
    public static Task create(UUID projectId, TaskTitle title,
                              TaskDescription description, TaskPriority priority,
                              LocalDateTime dueDate, UserId userAssignedId) {

        if (projectId == null) {
            throw new IllegalArgumentException("Project id cannot be null");
        }
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }

        return new Task(TaskId.generate(), projectId, title,
                description, priority, dueDate, userAssignedId);
    }

    public TaskId getTaskId() {
        return taskId;
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

    public LocalDateTime getDueDate() {
        return dueDate;
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

    public void changeDueDate(LocalDateTime newDueDate){
        this.dueDate = newDueDate;
    }

    public void assignToUser(UserId userAssignedId){
        this.userAssignedId = userAssignedId;
    }


    public void updateDetails(TaskTitle newTitle, TaskDescription newDescription,
                              TaskPriority newPriority, LocalDateTime newDueDate,
                              UserId newAssigneeId) {
        changeTitle(newTitle);
        changeDescription(newDescription);
        changePriority(newPriority);
        changeDueDate(newDueDate);
        assignToUser(newAssigneeId);
    }


    //Static Factory de reconstruccion de tarea para el mapper de persistencia

    public static Task reconstructTask (TaskId taskId, UUID projectId, TaskTitle title,
                                    TaskDescription description,TaskStatus status, TaskPriority priority,
                                    LocalDateTime dueDate, UserId userAssignedId) {
        Task task = new Task(taskId, projectId, title, description, priority, dueDate, userAssignedId);
        task.changeStatus(status);
        return task;
    }
}

