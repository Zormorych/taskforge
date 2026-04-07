package com.pdropalazn.taskforge.tasks.application.service;

import com.pdropalazn.taskforge.identity.domain.model.vo.UserId;
import com.pdropalazn.taskforge.tasks.application.usecase.port.dto.UpdateTaskCommand;
import com.pdropalazn.taskforge.tasks.domain.exception.ResourceNotFoundException;
import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.TaskPriority;
import com.pdropalazn.taskforge.tasks.domain.model.TaskStatus;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskDescription;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskTitle;
import com.pdropalazn.taskforge.tasks.domain.port.repository.TaskRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateTaskServiceTest {

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @InjectMocks
    private UpdateTaskService updateTaskService;

    @Test
    void shouldUpdateTaskWhenItExists() {

        // Arrange
        TaskId taskId = new TaskId(UUID.randomUUID());
        Task existingTask = Task.reconstructTask(
                taskId,
                UUID.randomUUID(),
                new TaskTitle("Título inicial de la tarea "),
                new TaskDescription("Descripción inicial de la tarea"),
                TaskStatus.TO_DO,
                TaskPriority.LOW,
                LocalDateTime.now().plusDays(1),
                new UserId(UUID.randomUUID())
        );

        UpdateTaskCommand command = new UpdateTaskCommand(
                taskId,
                new TaskTitle("Título actualizado"),
                new TaskDescription("Descripción actualizada"),
                TaskPriority.HIGH,
                LocalDateTime.now().plusDays(5),
                new UserId(UUID.randomUUID())
        );

        when(taskRepositoryPort.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepositoryPort.save(existingTask)).thenReturn(existingTask);

        // Act
        Task updatedTask = updateTaskService.update(command);

        // Assert
        assertEquals(command.title(), updatedTask.getTitle());
        assertEquals(command.description(), updatedTask.getDescription());
        assertEquals(command.priority(), updatedTask.getPriority());
        assertEquals(command.dueDate(), updatedTask.getDueDate());
        assertEquals(command.assigneeId(), updatedTask.getUserAssignedId());
        verify(taskRepositoryPort).findById(taskId);
        verify(taskRepositoryPort).save(existingTask);
    }

    @Test
    void shouldThrowExceptionWhenTaskDoesNotExist() {

        // Arrange
        TaskId taskId = new TaskId(UUID.randomUUID());
        UpdateTaskCommand command = new UpdateTaskCommand(
                taskId,
                new TaskTitle("Título actualizado"),
                new TaskDescription("Descripción actualizada"),
                TaskPriority.MEDIUM,
                LocalDateTime.now().plusDays(2),
                null
        );

        when(taskRepositoryPort.findById(taskId)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> updateTaskService.update(command));
        verify(taskRepositoryPort).findById(taskId);
    }
}
