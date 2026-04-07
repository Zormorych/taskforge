package com.pdropalazn.taskforge.projects.domain.port.repository;

import com.pdropalazn.taskforge.projects.domain.model.Project;

// CreateProject: outbound repository contract for persisting projects
public interface ProjectRepositoryPort {

    Project save(Project project);
}
