package com.pdropalazn.taskforge.projects.infrastructure.out.persistence.jpa;

import com.pdropalazn.taskforge.projects.domain.model.Project;
import com.pdropalazn.taskforge.projects.domain.port.repository.ProjectRepositoryPort;
import com.pdropalazn.taskforge.projects.infrastructure.out.persistence.jpa.entity.ProjectEntity;
import com.pdropalazn.taskforge.projects.infrastructure.out.persistence.jpa.mapper.ProjectJpaMapper;
import com.pdropalazn.taskforge.projects.infrastructure.out.persistence.jpa.repository.SpringDataProjectRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

// CreateProject: JPA adapter that persists the Project aggregate
@Repository
@Profile("jpa")
public class JpaProjectRepositoryAdapter implements ProjectRepositoryPort {

    private final SpringDataProjectRepository springDataProjectRepository;
    private final ProjectJpaMapper projectJpaMapper;

    public JpaProjectRepositoryAdapter(SpringDataProjectRepository springDataProjectRepository,
                                       ProjectJpaMapper projectJpaMapper) {
        this.springDataProjectRepository = springDataProjectRepository;
        this.projectJpaMapper = projectJpaMapper;
    }

    @Override
    public Project save(Project project) {
        // CreateProject: map the aggregate to JPA and back after saving
        ProjectEntity entity = projectJpaMapper.toEntity(project);
        ProjectEntity savedEntity = springDataProjectRepository.save(entity);
        return projectJpaMapper.toDomain(savedEntity);
    }
}
