package com.pdropalazn.taskforge.tasks.domain.port.in;

import com.pdropalazn.taskforge.tasks.application.usecase.port.dto.UpdateTaskCommand;
import com.pdropalazn.taskforge.tasks.domain.model.Task;

public interface UpdateTaskUseCase {

    Task update(UpdateTaskCommand command);
}
