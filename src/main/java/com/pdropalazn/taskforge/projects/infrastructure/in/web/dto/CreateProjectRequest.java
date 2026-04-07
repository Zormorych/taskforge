package com.pdropalazn.taskforge.projects.infrastructure.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//request DTO para POST /api/projects
public record CreateProjectRequest(
        @NotBlank @Size(min = 3, max = 200) String name,
        @Size(max = 1000) String description
) {
}
