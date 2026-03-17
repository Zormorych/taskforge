package com.pdropalazn.taskforge.tasks.domain.model;

import com.pdropalazn.taskforge.identity.domain.model.vo.UserId;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskDescription;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskTitle;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.pdropalazn.taskforge.tasks.domain.model.TaskStatus.IN_PROGRESS;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TaskTest {

    @Test
    void shouldCreateTaskWithValidData(){
        //Arrange (preparacion)
        UUID projectId = UUID.randomUUID();
        TaskTitle title = new TaskTitle("First Happy Path Test");
        TaskDescription description = new TaskDescription("This is also to practice JUnit, this is prob my first test alone");
        TaskPriority priority = TaskPriority.HIGH;
        LocalDateTime dueDate = LocalDateTime.of(2026, 3, 10, 18, 0);
        UserId userAssignedId = new UserId(UUID.randomUUID());

        //Act (Ejecución)
         Task task = Task.create(projectId, title, description, priority, dueDate, userAssignedId);

        //Assert (Verificación)
        assertNotNull(task.getTaskId());
        assertNotNull(task);
        assertEquals(title, task.getTitle());
        assertEquals(TaskStatus.TO_DO, task.getStatus());
        assertEquals(projectId, task.getProjectId());

    }

    @Test
    void shouldThrowExceptionWhenProjectIdIsNull(){
    //Arrange (preparacion)
        UUID projectId = null;
        TaskTitle title = new TaskTitle("Not a happy path Test");
        TaskDescription description = new TaskDescription("This is also to practice JUnit,,,");
        TaskPriority priority = TaskPriority.MEDIUM;
        LocalDateTime dueDate = LocalDateTime.of(2026, 4, 11, 19, 15);
        UserId userAssignedId = new UserId(UUID.randomUUID());

    //Act (Ejecución) + Assert (Verificación)
        assertThrows(IllegalArgumentException.class,
                () -> {Task.create(projectId, title, description, priority, dueDate, userAssignedId);
        });
    }

    @Test
    void shouldThrowExceptionWhenPriorityIsNull(){
    //Arrange(preparación)
        UUID projectId = UUID.randomUUID();
        TaskTitle title = new TaskTitle("Clean my bathroom");
        TaskDescription description = new TaskDescription("also the corners and tidy,,,");
        TaskPriority priority = null;
        LocalDateTime dueDate = LocalDateTime.of(2026, 4, 11, 19, 15);
        UserId userAssignedId = new UserId(UUID.randomUUID());

    //Act(Ejecución) + //Assert (Verificación)
        assertThrows(IllegalArgumentException.class,
                () -> {Task.create(projectId, title, description, priority, dueDate, userAssignedId);
        });

    }

    @Test
    void shouldChangeStatus(){

     //Arrange
        UUID projectId = UUID.randomUUID();
        TaskTitle title = new TaskTitle("First Happy Path Test");
        TaskDescription description = new TaskDescription("This is also to practice JUnit, this is prob my first test alone");
        TaskPriority priority = TaskPriority.LOW;
        LocalDateTime dueDate = LocalDateTime.of(2026, 4, 12, 23, 59);
        UserId userAssignedId = new UserId(UUID.randomUUID());

     //Act
        Task task = Task.create(projectId, title, description, priority, dueDate, userAssignedId);
        task.changeStatus(IN_PROGRESS);
     //Assert
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }
}
