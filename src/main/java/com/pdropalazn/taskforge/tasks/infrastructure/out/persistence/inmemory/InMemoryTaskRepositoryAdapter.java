package com.pdropalazn.taskforge.tasks.infrastructure.out.persistence.inmemory;


import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;
import com.pdropalazn.taskforge.tasks.domain.port.repository.TaskRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

//servirá para inyectar TaskRepositoryPort sin JPA aún
//ConcurrentHashMap mejor que HashMap por si entran varias petis a la vez
@Repository
public class InMemoryTaskRepositoryAdapter implements TaskRepositoryPort {

    private final Map<TaskId, Task> temporalStorage = new ConcurrentHashMap<>(); //esto hace que lo que guardo viva en memoria RAM, cuando la app la cierro se libera

    @Override
    public Task save (Task task){
        temporalStorage.put(task.getTaskId(),task);
        return task;
    }
    @Override
    public Optional<Task> findById(TaskId taskId) {
        return Optional.ofNullable(temporalStorage.get(taskId));
    }


}
