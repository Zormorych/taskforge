package com.pdropalazn.taskforge.projects.domain.port.in;

import com.pdropalazn.taskforge.projects.application.usecase.port.dto.CreateProjectCommand;
import com.pdropalazn.taskforge.projects.domain.model.Project;

// CreateProject: inbound use case for creating projects
public interface CreateProjectUseCase {

    Project create(CreateProjectCommand command);
}
