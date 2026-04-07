package com.pdropalazn.taskforge.projects.domain.model.vo;

// CreateProject: value object that validates the optional project description
public record ProjectDescription(String description) {

    public ProjectDescription {
        if (description != null) {
            description = description.trim();
            if (description.length() > 1000) {
                throw new IllegalArgumentException("Description cannot be longer than 1000 characters");
            }
        }
    }
}
