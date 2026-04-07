package com.pdropalazn.taskforge.projects.infrastructure.in.web.dto;

import java.util.UUID;

// response DTO web (respuesta de la API)
public record ProjectResponse(
        UUID projectId,
        String name,
        String description
) {
}
