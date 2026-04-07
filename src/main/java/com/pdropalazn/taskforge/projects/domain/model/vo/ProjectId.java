package com.pdropalazn.taskforge.projects.domain.model.vo;

import java.util.UUID;

public record ProjectId(UUID value) {

    public ProjectId {
        if (value == null) {
            throw new IllegalArgumentException("ProjectId cannot be null");
        }
    }

    public static ProjectId generate() {
        return new ProjectId(UUID.randomUUID());
    }
}
