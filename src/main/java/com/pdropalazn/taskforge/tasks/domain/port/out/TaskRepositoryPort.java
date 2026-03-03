package com.pdropalazn.taskforge.tasks.domain.port.out;

import com.pdropalazn.taskforge.tasks.domain.model.Task;
import com.pdropalazn.taskforge.tasks.domain.model.vo.TaskId;

import java.util.Optional;


//TaskRepositoryPort es el contrato que luego implemntará la infraestructura
//para guardar en BDD
public interface TaskRepositoryPort {

    Task save (Task task);

    Optional<Task> findById (TaskId taskId);

}
