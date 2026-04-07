package com.pdropalazn.taskforge.tasks.application.service;

import com.pdropalazn.taskforge.tasks.domain.exception.ResourceNotFoundException;
import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.TaskPriority;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// DeleteTask: verifies delete behavior and not found handling
@ExtendWith(MockitoExtension.class)
class DeleteTaskServiceTest {

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @InjectMocks
    private DeleteTaskService deleteTaskService;

    @Test
    void shouldDeleteTaskWhenTaskExists() {
        TaskId taskId = TaskId.generate();
        Task task = Task.reconstructTask(
                taskId,
                UUID.randomUUID(),
                new TaskTitle("Task to delete"),
                new TaskDescription("Description of task to delete"),
                com.pdropalazn.taskforge.tasks.domain.model.TaskStatus.TO_DO,
                TaskPriority.MEDIUM,
                LocalDateTime.now().plusDays(1),
                null
        );

        when(taskRepositoryPort.findById(taskId)).thenReturn(Optional.of(task));

        deleteTaskService.delete(taskId);

        verify(taskRepositoryPort).findById(taskId);
        verify(taskRepositoryPort).deleteById(taskId);
    }

    @Test
    void shouldThrowExceptionWhenTaskDoesNotExist() {
        TaskId taskId = TaskId.generate();

        when(taskRepositoryPort.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> deleteTaskService.delete(taskId));

        verify(taskRepositoryPort).findById(taskId);
        verify(taskRepositoryPort, never()).deleteById(taskId);
    }
}
