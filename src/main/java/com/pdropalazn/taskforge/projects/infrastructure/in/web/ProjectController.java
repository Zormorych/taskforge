package com.pdropalazn.taskforge.projects.infrastructure.in.web;

import com.pdropalazn.taskforge.projects.application.usecase.port.dto.CreateProjectCommand;
import com.pdropalazn.taskforge.projects.domain.model.Project;
import com.pdropalazn.taskforge.projects.domain.port.in.CreateProjectUseCase;
import com.pdropalazn.taskforge.projects.infrastructure.in.web.dto.CreateProjectRequest;
import com.pdropalazn.taskforge.projects.infrastructure.in.web.dto.ProjectResponse;
import com.pdropalazn.taskforge.projects.infrastructure.in.web.mapper.ProjectWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//controlador REST que expone el endpoint para crear proyectos
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final ProjectWebMapper mapper;

    public ProjectController(CreateProjectUseCase createProjectUseCase, ProjectWebMapper mapper) {
        this.createProjectUseCase = createProjectUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody CreateProjectRequest request) {
        // Convertir el request DTO a command de aplicación
        CreateProjectCommand command = mapper.toCommand(request);
        Project savedProject = createProjectUseCase.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(savedProject));
    }
}
