package com.pdropalazn.taskforge.tasks.infrastructure.out.persistence.jpa;

import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;
import com.pdropalazn.taskforge.tasks.domain.port.out.TaskRepositoryPort;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
  Placeholder de persistencia para una futura implementacion d Spring Data JPA
  Se activa unicamente con el perfil "jpa" para no interferir con el adaptador in-memory
 */
@Repository
@Profile("jpa")
public class JpaTaskRepositoryAdapter implements TaskRepositoryPort {

    @Override
    public Task save(Task task) {
        throw new UnsupportedOperationException("JPA adapter pending implementation");
    }

    @Override
    public Optional<Task> findById(TaskId taskId) {
        throw new UnsupportedOperationException("JPA adapter pending implementation");
    }
}
