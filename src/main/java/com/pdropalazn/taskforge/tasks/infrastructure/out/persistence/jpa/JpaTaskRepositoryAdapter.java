package com.pdropalazn.taskforge.tasks.infrastructure.out.persistence.jpa;

import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;
import com.pdropalazn.taskforge.tasks.domain.port.repository.TaskRepositoryPort;
import com.pdropalazn.taskforge.tasks.infrastructure.out.persistence.jpa.entity.TaskEntity;
import com.pdropalazn.taskforge.tasks.infrastructure.out.persistence.jpa.mapper.TaskJpaMapper;
import com.pdropalazn.taskforge.tasks.infrastructure.out.persistence.jpa.repository.SpringDataTaskRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
 Convierte Task en taskEntity
 Llama a SpringDataTaskRepository
 convierte TaskEntity a Task
 */
@Repository
@Profile("jpa")
public class JpaTaskRepositoryAdapter implements TaskRepositoryPort {

    private final SpringDataTaskRepository springDataTaskRepository;
    private final TaskJpaMapper  taskJpaMapper;

    public JpaTaskRepositoryAdapter(SpringDataTaskRepository springDataTaskRepository,
                                    TaskJpaMapper taskJpaMapper) {
        this.springDataTaskRepository = springDataTaskRepository;
        this.taskJpaMapper = taskJpaMapper;
    }

    @Override
    public Task save(Task task) {
        TaskEntity entity = taskJpaMapper.toEntity(task);
        TaskEntity savedTaskEntity = springDataTaskRepository.save(entity);
        return taskJpaMapper.toDomain(savedTaskEntity);
    }

    @Override
    public List<Task> findAll() {
        // GetAllTasks: maps every persisted TaskEntity back to the domain model
        return springDataTaskRepository.findAll().stream()
                .map(taskJpaMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Task> findById(TaskId taskId) {
        return springDataTaskRepository.findById(taskId.value())
                .map(taskJpaMapper::toDomain);
    }

    @Override
    public void deleteById(TaskId taskId) {
        // hard delete de la columna en bdd
        springDataTaskRepository.deleteById(taskId.value());
    }
}
