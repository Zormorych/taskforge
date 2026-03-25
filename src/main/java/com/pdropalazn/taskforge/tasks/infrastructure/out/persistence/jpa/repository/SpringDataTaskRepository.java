package com.pdropalazn.taskforge.tasks.infrastructure.out.persistence.jpa.repository;

import com.pdropalazn.taskforge.tasks.infrastructure.out.persistence.jpa.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
//para que Spring genere automaticamente el repositorio real de bdd
public interface SpringDataTaskRepository extends JpaRepository<TaskEntity, UUID> {
}
