package com.pdropalazn.taskforge.projects.domain.model;

import com.pdropalazn.taskforge.projects.domain.model.vo.ProjectDescription;
import com.pdropalazn.taskforge.projects.domain.model.vo.ProjectId;
import com.pdropalazn.taskforge.projects.domain.model.vo.ProjectName;

public class Project {

    private final ProjectId projectId;
    private final ProjectName name;
    private final ProjectDescription description;

    private Project(ProjectId projectId, ProjectName name, ProjectDescription description) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
    }

    public static Project create(ProjectName name, ProjectDescription description) {
        if (name == null) {
            throw new IllegalArgumentException("Project name cannot be null");
        }

        return new Project(ProjectId.generate(), name, description);
    }

    //para leer desde  base de datos y traer datos desde persistencia
    public static Project reconstructProject(ProjectId projectId, ProjectName name, ProjectDescription description) {
        return new Project(projectId, name, description);
    }

    public ProjectId getProjectId() {
        return projectId;
    }

    public ProjectName getName() {
        return name;
    }

    public ProjectDescription getDescription() {
        return description;
    }
}
