package com.pdropalazn.taskforge.projects.application.service;

import com.pdropalazn.taskforge.projects.application.usecase.port.dto.CreateProjectCommand;
import com.pdropalazn.taskforge.projects.domain.model.Project;
import com.pdropalazn.taskforge.projects.domain.port.in.CreateProjectUseCase;
import com.pdropalazn.taskforge.projects.domain.port.repository.ProjectRepositoryPort;
import org.springframework.stereotype.Service;

// CreateProject: application service that creates and persists a project
@Service
public class CreateProjectService implements CreateProjectUseCase {

    private final ProjectRepositoryPort projectRepository;

    public CreateProjectService(ProjectRepositoryPort projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project create(CreateProjectCommand command) {
        // CreateProject: delegate aggregate creation to the domain model
        Project project = Project.create(command.name(), command.description());
        return projectRepository.save(project);
    }
}
