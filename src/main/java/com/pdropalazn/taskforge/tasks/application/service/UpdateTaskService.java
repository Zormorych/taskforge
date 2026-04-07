package com.pdropalazn.taskforge.tasks.application.service;

import com.pdropalazn.taskforge.tasks.application.usecase.port.dto.UpdateTaskCommand;
import com.pdropalazn.taskforge.tasks.domain.exception.ResourceNotFoundException;
import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.port.in.UpdateTaskUseCase;
import com.pdropalazn.taskforge.tasks.domain.port.repository.TaskRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UpdateTaskService implements UpdateTaskUseCase {

    private final TaskRepositoryPort taskRepository;

    public UpdateTaskService(TaskRepositoryPort taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional

    public Task update(UpdateTaskCommand command) {
        Task task = taskRepository.findById(command.taskId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found with id: " + command.taskId().asString()
                ));


        // Actualiza los campos de tareas editables antes de guardar
        task.updateDetails(
                command.title(),
                command.description(),
                command.priority(),
                command.dueDate(),
                command.assigneeId()
        );

        return taskRepository.save(task);
    }
}
