package com.pdropalazn.taskforge.tasks.application.service;


import com.pdropalazn.taskforge.identity.domain.model.vo.UserId;
import com.pdropalazn.taskforge.tasks.application.usecase.port.dto.CreateTaskCommand;
import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.TaskPriority;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskDescription;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskTitle;
import com.pdropalazn.taskforge.tasks.domain.port.repository.TaskRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

//test para validar colaboracion entre servicio y caso de uso
@ExtendWith(MockitoExtension.class)
class CreateTaskServiceTest {
    @Mock
    private TaskRepositoryPort taskRepositoryPort;
    @InjectMocks
    private CreateTaskService createTaskService;

    @Test
    void shouldSaveTaskWhenCommandIsValid_happyPath(){

        // Arrange
        CreateTaskCommand command = new CreateTaskCommand(
                UUID.randomUUID(),
                new TaskTitle("Test task"),
                new TaskDescription("Test description"),
                TaskPriority.LOW,
                LocalDateTime.now().plusDays(1),
                new UserId(UUID.randomUUID())
        );

        when(taskRepositoryPort.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Task result = createTaskService.create(command);

        // Assert
        assertNotNull(result);
        verify(taskRepositoryPort).save(any(Task.class));
    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() {

        // Arrange
        CreateTaskCommand command = new CreateTaskCommand(
                UUID.randomUUID(),
                new TaskTitle("Test task"),
                new TaskDescription("Test description"),
                TaskPriority.LOW,
                LocalDateTime.now().plusDays(1),
                new UserId(UUID.randomUUID())
        );

        when(taskRepositoryPort.save(any(Task.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Act + Assert
        assertThrows(RuntimeException.class, () -> createTaskService.create(command));

        verify(taskRepositoryPort).save(any(Task.class));
    }

}
