package com.pdropalazn.taskforge.tasks.application.service;

import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.port.in.GetAllTasksUseCase;
import com.pdropalazn.taskforge.tasks.domain.port.repository.TaskRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTasksService implements GetAllTasksUseCase {

    private final TaskRepositoryPort taskRepository;

    public GetAllTasksService(TaskRepositoryPort taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAll() {
        // GetAllTasks: delegates listing to the repository port and preserves empty-list behavior
        return taskRepository.findAll();
    }
}
