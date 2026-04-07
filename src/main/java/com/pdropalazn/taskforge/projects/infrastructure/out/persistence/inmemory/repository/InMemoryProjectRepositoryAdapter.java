package com.pdropalazn.taskforge.projects.infrastructure.out.persistence.inmemory.repository;

import com.pdropalazn.taskforge.projects.domain.model.Project;
import com.pdropalazn.taskforge.projects.domain.model.vo.ProjectId;
import com.pdropalazn.taskforge.projects.domain.port.repository.ProjectRepositoryPort;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// CreateProject: in-memory adapter to keep the profile wiring consistent
@Repository
@Profile("in-memory")
public class InMemoryProjectRepositoryAdapter implements ProjectRepositoryPort {

    private final Map<ProjectId, Project> temporalStorage = new ConcurrentHashMap<>();

    @Override
    public Project save(Project project) {
        // CreateProject: store the project in memory for the in-memory profile
        temporalStorage.put(project.getProjectId(), project);
        return project;
    }
}
