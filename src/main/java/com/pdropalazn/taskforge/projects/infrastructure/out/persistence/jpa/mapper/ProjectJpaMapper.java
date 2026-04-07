package com.pdropalazn.taskforge.projects.infrastructure.out.persistence.jpa.mapper;

import com.pdropalazn.taskforge.projects.domain.model.Project;
import com.pdropalazn.taskforge.projects.domain.model.vo.ProjectDescription;
import com.pdropalazn.taskforge.projects.domain.model.vo.ProjectId;
import com.pdropalazn.taskforge.projects.domain.model.vo.ProjectName;
import com.pdropalazn.taskforge.projects.infrastructure.out.persistence.jpa.entity.ProjectEntity;
import org.springframework.stereotype.Component;

// CreateProject: mapper between the Project aggregate and ProjectEntity
@Component
public class ProjectJpaMapper {

    public ProjectEntity toEntity(Project project) {
        return new ProjectEntity(
                project.getProjectId().value(),
                project.getName().name(),
                project.getDescription() == null ? null : project.getDescription().description()
        );
    }

    public Project toDomain(ProjectEntity entity) {
        return Project.reconstructProject(
                new ProjectId(entity.getId()),
                new ProjectName(entity.getName()),
                entity.getDescription() == null ? null : new ProjectDescription(entity.getDescription())
        );
    }
}
