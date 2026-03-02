package com.pdropalazn.taskforge.tasks.application.service;

import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.port.in.CreateTaskUseCase;
import com.pdropalazn.taskforge.tasks.domain.port.in.dto.CreateTaskCommand;
import com.pdropalazn.taskforge.tasks.domain.port.out.TaskRepositoryPort;
import org.springframework.stereotype.Service;


//implementacion del caso de uso
@Service
public class CreateTaskService implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepository;

    public CreateTaskService (TaskRepositoryPort taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create (CreateTaskCommand command){
        Task task = Task.create(
                command.projectId(),
                command.title(),
                command.description(),
                command.priority(),
                command.dateTime(),
                command.assigneeId()
        );
    return taskRepository.save(task);
    }

}
