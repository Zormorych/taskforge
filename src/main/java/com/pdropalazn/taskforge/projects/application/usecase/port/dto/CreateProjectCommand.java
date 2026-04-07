package com.pdropalazn.taskforge.projects.application.usecase.port.dto;

import com.pdropalazn.taskforge.projects.domain.model.vo.ProjectDescription;
import com.pdropalazn.taskforge.projects.domain.model.vo.ProjectName;

// CreateProject: application command that carries input data to the use case
public record CreateProjectCommand(
        ProjectName name,
        ProjectDescription description
) { }
