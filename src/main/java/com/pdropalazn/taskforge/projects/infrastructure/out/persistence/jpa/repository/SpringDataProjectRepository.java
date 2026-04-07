package com.pdropalazn.taskforge.projects.infrastructure.out.persistence.jpa.repository;

import com.pdropalazn.taskforge.projects.infrastructure.out.persistence.jpa.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// CreateProject: Spring Data repository for project persistence
public interface SpringDataProjectRepository extends JpaRepository<ProjectEntity, UUID> {
}
