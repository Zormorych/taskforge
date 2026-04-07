package com.pdropalazn.taskforge.projects.infrastructure.in.web.mapper;

import com.pdropalazn.taskforge.projects.application.usecase.port.dto.CreateProjectCommand;
import com.pdropalazn.taskforge.projects.domain.model.Project;
import com.pdropalazn.taskforge.projects.domain.model.vo.ProjectDescription;
import com.pdropalazn.taskforge.projects.domain.model.vo.ProjectName;
import com.pdropalazn.taskforge.projects.infrastructure.in.web.dto.CreateProjectRequest;
import com.pdropalazn.taskforge.projects.infrastructure.in.web.dto.ProjectResponse;
import org.springframework.stereotype.Component;

// mapper de web DTO a capas dominio y app
@Component
public class ProjectWebMapper {

    public CreateProjectCommand toCommand(CreateProjectRequest request) {
        return new CreateProjectCommand(
                new ProjectName(request.name()),
                request.description() == null ? null : new ProjectDescription(request.description())
        );
    }

    public ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getProjectId().value(),
                project.getName().name(),
                project.getDescription() == null ? null : project.getDescription().description()
        );
    }
}
