package com.pdropalazn.taskforge.tasks.application.service;

import com.pdropalazn.taskforge.tasks.domain.exception.ResourceNotFoundException;
import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;
import com.pdropalazn.taskforge.tasks.domain.port.in.GetTaskByIdUseCase;
import com.pdropalazn.taskforge.tasks.domain.port.repository.TaskRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetTaskByIdService implements GetTaskByIdUseCase {

    private final TaskRepositoryPort taskRepository;

    public GetTaskByIdService(TaskRepositoryPort taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task getById(TaskId taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId.asString()));
    }
}
