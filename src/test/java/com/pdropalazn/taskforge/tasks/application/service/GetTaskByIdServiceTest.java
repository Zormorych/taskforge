package com.pdropalazn.taskforge.tasks.application.service;

import com.pdropalazn.taskforge.identity.domain.model.vo.UserId;
import com.pdropalazn.taskforge.tasks.application.usecase.port.dto.CreateTaskCommand;
import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.TaskPriority;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskDescription;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskTitle;
import com.pdropalazn.taskforge.tasks.domain.port.in.GetTaskByIdUseCase;
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
class GetTaskByIdServiceTest  {

    @Mock
    private TaskRepositoryPort taskRepositoryPort;
    @InjectMocks
    private GetTaskByIdService getTaskByIdService;

    @Test
    void shouldGetTaskByIdWhenExists(){

        // Arrange
        TaskId taskId = new TaskId(UUID.randomUUID());

        Task expectedTask = Task.create(
                UUID.randomUUID(),
                new TaskTitle("Test task"),
                new TaskDescription("Test description prueba"),
                TaskPriority.MEDIUM,
                LocalDateTime.now().plusDays(1),
                new UserId(UUID.randomUUID())
        );

        when(taskRepositoryPort.findById(taskId)).thenReturn(Optional.of(expectedTask));

        //ACT
        Task result = getTaskByIdService.getById(taskId);
        //ASSErT
        assertEquals(expectedTask, result);
        verify(taskRepositoryPort).findById(taskId);
    }

    @Test
    void shouldThrowExceptionWhenTaskDoesNotExist(){


        // Arrange
        TaskId taskId = new TaskId(UUID.randomUUID());

        when(taskRepositoryPort.findById(taskId))
                .thenReturn(Optional.empty());

        // Act y Assert
        assertThrows(RuntimeException.class,
                () -> getTaskByIdService.getById(taskId));

        verify(taskRepositoryPort).findById(taskId);
    }
}
