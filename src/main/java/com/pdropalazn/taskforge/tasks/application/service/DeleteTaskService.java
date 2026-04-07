package com.pdropalazn.taskforge.tasks.application.service;

import com.pdropalazn.taskforge.tasks.domain.exception.ResourceNotFoundException;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;
import com.pdropalazn.taskforge.tasks.domain.port.in.DeleteTaskUseCase;
import com.pdropalazn.taskforge.tasks.domain.port.repository.TaskRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// DeleteTask: removes the task if it exists, otherwise throws not found
@Service
public class DeleteTaskService implements DeleteTaskUseCase {

    private final TaskRepositoryPort taskRepository;

    public DeleteTaskService(TaskRepositoryPort taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public void delete(TaskId taskId) {
        // se asegura de que existe la tarea antes de borrarla
        taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId.asString()));

        // realiza el hardelete repository port
        taskRepository.deleteById(taskId);
    }
}
