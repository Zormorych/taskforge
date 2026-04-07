package com.pdropalazn.taskforge.tasks.application.service;

import com.pdropalazn.taskforge.identity.domain.model.vo.UserId;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllTasksServiceTest {

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @InjectMocks
    private GetAllTasksService getAllTasksService;

    @Test
    void shouldReturnAllTasks() {
        Task firstTask = Task.create(
                UUID.randomUUID(),
                new TaskTitle("First task"),
                new TaskDescription("First description"),
                TaskPriority.HIGH,
                LocalDateTime.now().plusDays(1),
                new UserId(UUID.randomUUID())
        );
        Task secondTask = Task.create(
                UUID.randomUUID(),
                new TaskTitle("Second task"),
                new TaskDescription("Second description"),
                TaskPriority.LOW,
                LocalDateTime.now().plusDays(2),
                new UserId(UUID.randomUUID())
        );

        when(taskRepositoryPort.findAll()).thenReturn(List.of(firstTask, secondTask));

        List<Task> result = getAllTasksService.getAll();

        assertEquals(List.of(firstTask, secondTask), result);
        verify(taskRepositoryPort).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoTasks() {
        // GetAllTasks: no tasks is a valid state, so the use case returns an empty list
        when(taskRepositoryPort.findAll()).thenReturn(List.of());

        List<Task> result = getAllTasksService.getAll();

        assertEquals(List.of(), result);
        verify(taskRepositoryPort).findAll();
    }
}
